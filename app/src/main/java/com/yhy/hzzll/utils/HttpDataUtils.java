package com.yhy.hzzll.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.view.DialogLoading;

/**
 * http 网络请求 帮助类
 *
 * @author Yang
 */
public class HttpDataUtils {

    private Context context;

    private HttpHandler<?> handler;

    public HttpDataUtils(Context context) {
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    private SucessBack sucessBack;
    private FailBack failBack;

    public interface SucessBack {

        void sucess(ResponseInfo<String> arg0);

    }

    public interface FailBack {

//        void fialmessage(ResponseInfo<String> arg0);
        void fail(String msg);
    }

    public void setSucessBack(SucessBack sucessBack) {
        this.sucessBack = sucessBack;
    }

    public void setFailBack(FailBack failBack) {
        this.failBack = failBack;
    }

    /**
     * 带 dialog post 请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendProgressPost(String url, RequestParams params) {

        final DialogLoading loading = new DialogLoading();
        if (NetUtills.checkNet(context)) {
            if (loading != null)
                loading.showDialog(context);
            HttpUtils utils = new HttpUtils();
            if (params != null)
                params.addHeader("Accept",
                        "application/json, text/javascript, */*;");
            else {
                params = new RequestParams();
            }

            LogUtils.logE(params.toString());

            handler = utils.send(HttpMethod.POST, url, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onFailure(HttpException arg0, String arg1) {
                            if (failBack != null)
                                failBack.fail(arg1);
                            LogUtils.logE(arg1);
                            loading.dismissDialog();
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            if (sucessBack != null)
                                sucessBack.sucess(arg0);
                            LogUtils.logE(arg0.result);
                            loading.dismissDialog();
                        }

                        @Override
                        public void onCancelled() {
                            loading.dismissDialog();
                            super.onCancelled();
                        }

                    });
            utils.configSoTimeout(15000);
            // 设置当前请求的缓存时间
            utils.configCurrentHttpCacheExpiry(0);
            // 设置默认请求的缓存时间
            utils.configDefaultHttpCacheExpiry(0);
            // 设置线程数
            utils.configRequestThreadPoolSize(10);
        } else {
            NetUtills.AlertNetError(context);
        }
    }

    /**
     * 带 dialog post 请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendProgressPut(String url, RequestParams params) {
        final DialogLoading loading = new DialogLoading();
        if (NetUtills.checkNet(context)) {
            if (loading != null)
                loading.showDialog(context);
            HttpUtils utils = new HttpUtils();
            if (params != null)
                params.addHeader("Accept",
                        "application/json, text/javascript, */*;");
            else {
                params = new RequestParams();
            }
            handler = utils.send(HttpMethod.PUT, url, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onFailure(HttpException arg0, String arg1) {
                            if (failBack != null)
                                failBack.fail(arg1);
                            loading.dismissDialog();
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            if (sucessBack != null)
                                sucessBack.sucess(arg0);
                            LogUtils.logE(arg0.result);
                            loading.dismissDialog();
                        }

                        @Override
                        public void onCancelled() {
                            loading.dismissDialog();
                            super.onCancelled();
                        }

                    });
            utils.configSoTimeout(15000);
            // 设置当前请求的缓存时间
            utils.configCurrentHttpCacheExpiry(0);
            // 设置默认请求的缓存时间
            utils.configDefaultHttpCacheExpiry(0);
            // 设置线程数
            utils.configRequestThreadPoolSize(10);
        } else {
            NetUtills.AlertNetError(context);
        }
    }

    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendPost(String url, RequestParams params) {
        if (NetUtills.checkNet(context)) {
            HttpUtils utils = new HttpUtils();
            if (params != null)
                params.addHeader("Accept", "application/json, text/javascript, */*;");
            else {
                params = new RequestParams();
            }
            handler = utils.send(HttpMethod.POST, url, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onFailure(HttpException arg0, String arg1) {

                            if (failBack != null) {
                                failBack.fail(arg1);
                            }

                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            LogUtils.logE(arg0.result);
                            if (sucessBack != null) sucessBack.sucess(arg0);
                        }

                        @Override
                        public void onCancelled() {
                            super.onCancelled();
                        }
                    });
            // 链接超时
            utils.configSoTimeout(15000);
            // 设置当前请求的缓存时间
            utils.configCurrentHttpCacheExpiry(0);
            // 设置默认请求的缓存时间
            utils.configDefaultHttpCacheExpiry(0);
            // 设置线程数
            utils.configRequestThreadPoolSize(10);
        } else {
            NetUtills.AlertNetError(context);
        }
    } /**
     * post 请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendDELETE(String url, RequestParams params) {
        if (NetUtills.checkNet(context)) {
            HttpUtils utils = new HttpUtils();
            if (params != null)
                params.addHeader("Accept", "application/json, text/javascript, */*;");
            else {
                params = new RequestParams();
            }
            handler = utils.send(HttpMethod.DELETE, url, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onFailure(HttpException arg0, String arg1) {

                            if (failBack != null) {
                                failBack.fail(arg1);
                            }

                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            LogUtils.logE(arg0.result);
                            if (sucessBack != null) sucessBack.sucess(arg0);
                        }

                        @Override
                        public void onCancelled() {
                            super.onCancelled();
                        }
                    });
            // 链接超时
            utils.configSoTimeout(15000);
            // 设置当前请求的缓存时间
            utils.configCurrentHttpCacheExpiry(0);
            // 设置默认请求的缓存时间
            utils.configDefaultHttpCacheExpiry(0);
            // 设置线程数
            utils.configRequestThreadPoolSize(10);
        } else {
            NetUtills.AlertNetError(context);
        }
    }

    /**
     * 带 dialog get 请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendProgressGet(String url, RequestParams params) {
        final DialogLoading loading = new DialogLoading();
        if (NetUtills.checkNet(context)) {
            if (loading != null)
                loading.showDialog(context);

            HttpUtils utils = new HttpUtils();
            if (params != null)
                params.addHeader("Accept",
                        "application/json, text/javascript, */*;");
            else {
                params = new RequestParams();
            }
            handler = utils.send(HttpMethod.GET, url, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onFailure(HttpException arg0, String arg1) {

                            if (loading != null)
                                loading.dismissDialog();

                            if (failBack != null)
                                failBack.fail(arg1);
//							Log.e("fail", arg1);
//							ToastUtils.getUtils(context).show(arg1);
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            if (sucessBack != null)
                                sucessBack.sucess(arg0);
                            LogUtils.logE(arg0.result);
                            if (loading != null)
                                loading.dismissDialog();
                        }

                        @Override
                        public void onCancelled() {
                            loading.dismissDialog();
                            super.onCancelled();
                        }
                    });
            // 链接超时
            utils.configSoTimeout(15000);
            // 设置当前请求的缓存时间
            utils.configCurrentHttpCacheExpiry(0);
            // 设置默认请求的缓存时间
            utils.configDefaultHttpCacheExpiry(0);
            // 设置线程数
            utils.configRequestThreadPoolSize(10);
        } else {
            NetUtills.AlertNetError(context);
        }
    }

    /**
     * 带 dialog get 请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendGet(String url, RequestParams params) {
        if (NetUtills.checkNet(context)) {
            HttpUtils utils = new HttpUtils();
            if (params != null)
                params.addHeader("Accept",
                        "application/json, text/javascript, */*;");
            else {
                params = new RequestParams();
            }
            handler = utils.send(HttpMethod.GET, url, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onFailure(HttpException arg0, String arg1) {
                            if (failBack != null)
                                failBack.fail(arg1);
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            if (sucessBack != null)
                                sucessBack.sucess(arg0);
                            LogUtils.logE(arg0.result);
                        }

                        @Override
                        public void onCancelled() {
                            super.onCancelled();
                        }
                    });
            // 链接超时
            utils.configSoTimeout(15000);
            // 设置当前请求的缓存时间
            utils.configCurrentHttpCacheExpiry(0);
            // 设置默认请求的缓存时间
            utils.configDefaultHttpCacheExpiry(0);
            // 设置线程数
            utils.configRequestThreadPoolSize(10);
        } else {
            NetUtills.AlertNetError(context);
        }
    }

    public void showMsg(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.optString("msg");
//            String msg = object.optString("message");
            if (msg.equals("您还未登录，请先登录！")) {
                context.startActivity(new Intent().setClass(context, LoginActivity.class));
            } else {
                ToastUtils.getUtils(context).show(msg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showMsgNew(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String msg = object.optString("message");
//            String msg = object.optString("message");
            if (msg.equals("您还未登录，请先登录！")) {
                context.startActivity(new Intent().setClass(context, LoginActivity.class));
            } else {
                ToastUtils.getUtils(context).show(msg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public boolean code(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String code = object.optString("code");

            if (code.equals("000000")) {
                return true;
            } else if (code.equals("0")) {
                return true;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public String data(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String data = object.optString("data");
//			Log.e(context.getClass().toString(), data);
            return data;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public String getJson(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            String string = object.optString(key);
            return string;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public int list(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String data = object.optString("data");
            JSONObject object2 = new JSONObject(data);
            JSONArray array = object2.optJSONArray("list");
            return array.length();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public void httpclientCancel() {
        // 调用cancel()方法停止连接
        if (handler != null && !handler.isCancelled()) {
            handler.cancel();
        }
    }
}
