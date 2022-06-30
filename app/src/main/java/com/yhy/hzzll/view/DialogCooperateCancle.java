package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;

public class DialogCooperateCancle {


    private AlertDialog alertDialog;

    private View view;

    public interface Click {
        void click();
    }

    public AlertDialog showDialog(Context context, final Click click) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        // 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = alertDialog.getWindow();
        view = LayoutInflater.from(context).inflate(
                R.layout.dialog_cooperate_cancle_order, null);
        TextView tv_left = (TextView) view.findViewById(R.id.tv_left);
        TextView tv_right = (TextView) view.findViewById(R.id.tv_right);

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (click != null)
                    click.click();
                alertDialog.dismiss();
            }
        });
        window.setContentView(view);
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
