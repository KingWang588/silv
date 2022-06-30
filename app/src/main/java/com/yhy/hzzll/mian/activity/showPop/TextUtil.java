package com.yhy.hzzll.mian.activity.showPop;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    //TextView 代码要加下面这一行，否则点击无效
//    title.setMovementMethod(LinkMovementMethod.getInstance());

    public SpannableString hightLight(Context context, Pattern pattern, String str, String regx1, SpannableString spannableString,int color, ShuoMClickableSpan.Callback callback) {
        List<HashMap<String, String>> lst  = this.getStartAndEnd(pattern,str);  //获取查找到的文字字段
        for (HashMap<String, String> map : lst){
            ShuoMClickableSpan span = new ShuoMClickableSpan(regx1, context,callback,color);
            spannableString.setSpan(span, Integer.parseInt(map.get(START)), Integer.parseInt(map.get(END)), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    private static String START="start";
    private static String END="end";
    public List<HashMap<String, String>> getStartAndEnd(Pattern pattern,String str) {
        List<HashMap<String, String>> lst = new ArrayList<HashMap<String,String>>();
        Matcher matcher = pattern.matcher(str);                   //用正则表达式匹配在文本中是否有查找的字段，如果有就讲其存储在list中
        while(matcher.find()){
            HashMap<String, String> map = new HashMap<String, String>();
            //       map.put(PHRASE, matcher.group());
            map.put(START, matcher.start()+"");                  // start函数返回匹配到的子字符串的开始位置,没有匹配到的话,应该是-1,小于0
            map.put(END, matcher.end()+"");
            lst.add(map);
        }
        return lst;
    }
}
