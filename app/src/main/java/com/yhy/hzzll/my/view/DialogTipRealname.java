package com.yhy.hzzll.my.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;

/**
 * Created by chengying on 2017/7/18.
 */

public class DialogTipRealname {


    private AlertDialog alertDialog;

    private TextView tv_cancel, tv_ok;

    public interface Click {
        void buy();
    }

    public AlertDialog showDialog(Context context, final Click click) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        // 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = alertDialog.getWindow();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_tips_realname,
                null);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        window.setContentView(view);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismissDialog();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.buy();
                }
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
