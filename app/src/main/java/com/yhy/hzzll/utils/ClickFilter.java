package com.yhy.hzzll.utils;

import com.yhy.hzzll.framework.DemoCache;

/**
 * Created by chengying on 2017/10/12.
 */

public class ClickFilter {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }else{
//            ToastUtils.getUtils(DemoCache.getContext()).show("请勿连续点击按钮！");
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static boolean isRefresh() {
        boolean flag = false;
       if(PrefsUtils.getString(DemoCache.getContext(),PrefsUtils.REFRESHTIME).length()!=0) {
           long lasttime = Long.parseLong(PrefsUtils.getString(DemoCache.getContext(),PrefsUtils.REFRESHTIME));
           long curClickTime = System.currentTimeMillis();
//           if ((curClickTime - lasttime) >= 24*60*60) {
           if ((curClickTime - lasttime) >= 12*60*60*1000) {
               flag = true;
               lastClickTime = curClickTime;
               PrefsUtils.saveString(DemoCache.getContext(),PrefsUtils.REFRESHTIME,curClickTime+"");
           }else{

           }

       }
        return flag;
    }



}
