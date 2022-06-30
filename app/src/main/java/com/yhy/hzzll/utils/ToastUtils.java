package com.yhy.hzzll.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.dovar.dtoast.DToast;
import com.dovar.dtoast.inner.IToast;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.CBPageAdapter;

/**
 * Toast 辅助�?
 * <p>
 * 单例模式
 * <p>
 * 只会显示一个toast 提示
 *
 * @author wangyang
 */
public class ToastUtils {

//    private Context context;
//    /**
//     * toast 对象
//     */
//    private Toast toast;
//    /**
//     * 是否在显�?
//     */
//    private boolean isshow = false;
//
//    public ToastUtils(Context context) {
//        this.context = context;
//
//    }
//
//    private static ToastUtils toastUtils;
//
//    public static ToastUtils getUtils(Context context) {
//        if (toastUtils == null) {
//            toastUtils = new ToastUtils(context);
//        }
//        return toastUtils;
//    }
//
//    /**
//     * 判断如果toast是显示状态，就取消掉，显示下�?��
//     */
//    public void show(String str) {
//        if (isshow == true) {
//            toast.cancel();
//            isshow = !isshow;
//        }
//        toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
//        toast.show();
//        isshow = !isshow;
//    }


    private Context context;
    /**
     * toast 对象
     */
    private IToast toast;

    public ToastUtils(Context context) {
        this.context = context;
    }

    private static ToastUtils toastUtils;

    public static ToastUtils getUtils(Context context) {
        if (toastUtils == null) {
            toastUtils = new ToastUtils(context);
        }
        return toastUtils;
    }

    /**
     * 判断如果toast是显示状态，就取消掉，显示下�?��
     */
    public void show(String str) {

        toast =DToast.make(context);
        TextView tv_text = (TextView) toast.getView().findViewById(R.id.tv_content);
        if (tv_text != null) {
            tv_text.setText(str);
        }
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,100).show();
    }


}
