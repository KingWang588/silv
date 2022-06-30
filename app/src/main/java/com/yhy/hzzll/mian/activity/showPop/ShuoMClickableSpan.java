package com.yhy.hzzll.mian.activity.showPop;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;


public class ShuoMClickableSpan extends ClickableSpan {

    String string;
    Context context;
    Callback callback;
    int color;

    public ShuoMClickableSpan(String str, Context context, Callback callback, int mColor){
        super();
        this.string = str;
        this.context = context;
        this.callback = callback;
        this.color = mColor ;
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(context.getColor(color));
    }

    @Override
    public void onClick(View widget) {
        Log.i("ShuoMClickableSpan", "onClick: "+string );
        callback.callback(string);

    }

    public interface Callback{
        public void callback(String s);
    }
}
