package com.yhy.hzzll.home.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.yhy.hzzll.R;

/**
 * Created by chengying on 2017/8/7.
 */

public class DialogTipRule {

    private AlertDialog alertDialog;

    private ImageView tv_cancel;


    public AlertDialog showDialog(Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        // 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = alertDialog.getWindow();
        View view = LayoutInflater.from(context).inflate(R.layout.linear_rule, null);
        tv_cancel= (ImageView) view.findViewById(R.id.iv_close);

        window.setContentView(view);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismissDialog();
            }
        });

        return alertDialog;
    }

    public boolean isShow() {
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                return true;
            }
        }
        return false;
    }

    public void dismissDialog() {
        if (alertDialog != null)
            alertDialog.dismiss();
    }


}
