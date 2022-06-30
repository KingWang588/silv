package com.yhy.hzzll.mian.activity.showPop;

import android.app.Activity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.yhy.hzzll.R;

import java.util.regex.Pattern;

public class ShowMsgDialog {

    public void showMsg(Activity activity,String title,String msg, String parttern , ShuoMClickableSpan.Callback call , ShowDialogCallback callback){
        View popView = LayoutInflater.from(activity).inflate(R.layout.pop_show_msg,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog dialog = builder
                .setView(popView)
                .create();
        Window window =  dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.pop_show_msg_bg);
        WindowManager.LayoutParams attr = window.getAttributes();
        if (attr != null) {
            attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            attr.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            attr.gravity = Gravity.CENTER;//设置dialog 在布局中的位置
        }
        TextView mTitle = popView.findViewById(R.id.title);
        TextView mMsg = popView.findViewById(R.id.msg);
        TextView mCancel = popView.findViewById(R.id.cancel);
        TextView mOk = popView.findViewById(R.id.ok);

        mTitle.setText(title);

        mMsg.setClickable(true);
        mMsg.setMovementMethod(LinkMovementMethod.getInstance());
        TextUtil textUtil = new TextUtil();
        SpannableString spannableString = new SpannableString(msg);
        SpannableString sr = textUtil.hightLight(activity, Pattern.compile(parttern), msg, "", spannableString, R.color.red, call);
        mMsg.setText(sr);

        mCancel.setOnClickListener(v -> {
            if (callback!=null)
                callback.cancel();
            dialog.dismiss();
        });
        mOk.setOnClickListener(v -> {
            if (callback!=null)
                callback.ok();
            dialog.dismiss();

        });
        dialog.show();
    }


}
