package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.my.adapter.FansListAdapter;
import com.yhy.hzzll.entity.FocusListEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的华粉
 *
 * @author Yang
 */
public class FansActivity extends BaseActivity {

    /**
     * 会员数据
     */
    private static List<FocusListEntity.DataBean.ListBean> focusVipList;


    @ViewInject(R.id.lv_focus)
    private ListView lv_focus;
    @ViewInject(R.id.tv_no_data)
    TextView tv_no_data;
    private FansListAdapter fansAdapter;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_fans);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        viewInit();
    }

    private void viewInit() {
        focusVipList = new ArrayList<>();
        fansAdapter = new FansListAdapter(FansActivity.this,focusVipList);
        lv_focus.setAdapter(fansAdapter);
        lv_focus.setEmptyView(tv_no_data);

        lv_focus.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if ("1".equals(focusVipList.get(position).getUser_type())) {
                    startActivity(new Intent().putExtra("user_id", focusVipList.get(position).getUser_id()+"").putExtra("title", focusVipList.get(position).getNickname()+"").putExtra("from", "laynation").setClass(FansActivity.this, LawyerOfficeActivity.class));
                }

            }
        });

        getList();
    }

    /**
     * 进页面自动刷新
     */
//    private void publishViewInit() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lv_focus.setRefreshing(true);
//            }
//        }, 300);
//    }
//
//    class ListViewOnRefreshListener implements OnRefreshListener<ListView> {
//
//        @Override
//        public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//            isPullRefresh = true;
//            getList();
//
//        }
//    }

    /**
     * 我的华粉列表
     */
    private void getList() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(FansActivity.this, PrefsUtils.AUTHORIZATION));
//        params.addQueryStringParameter("page_num", "200");
        httpDataUtils.sendProgressGet(MyData.MY_FOLLOWER, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        Gson gson =new Gson();
                        FocusListEntity entity = gson.fromJson(object.toString(),FocusListEntity.class);
                        focusVipList.addAll(entity.getData().getList());
                        fansAdapter.notifyDataSetChanged();

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        httpDataUtils.setFailBack(new FailBack() {

            @Override
            public void fail(String msg) {
//                lv_focus.onRefreshComplete();
            }
        });
    }


//    class SortListUtils implements Comparator<Object> {
//
//        @Override
//        public int compare(Object lhs, Object rhs) {
//            FocusListEntity l1 = (FocusListEntity) lhs;
//            FocusListEntity l2 = (FocusListEntity) rhs;
//            String name1 = l1.getNickname().toString();
//            String name2 = l2.getNickname().toString();
//            for (int i = 0; i < name1.length() && i < name2.length(); i++) {
//                int codePoint1 = name1.charAt(i);
//                int codePoint2 = name2.charAt(i);
//                if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
//                    i++;
//                }
//                if (codePoint1 != codePoint2) {
//                    if (Character.isSupplementaryCodePoint(codePoint1)
//                            || Character.isSupplementaryCodePoint(codePoint2)) {
//                        return codePoint1 - codePoint2;
//                    }
//                    String pinyin1 = pinyin((char) codePoint1);
//                    String pinyin2 = pinyin((char) codePoint2);
//                    if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
//                        if (!pinyin1.equals(pinyin2)) {
//                            return pinyin1.compareTo(pinyin2);
//                        }
//                    } else {
//                        return codePoint1 - codePoint2;
//                    }
//                }
//            }
//            return name1.length() - name2.length();
//        }
//
//        /**
//         * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
//         */
//        private String pinyin(char c) {
//            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
//            if (pinyins == null) {
//                return null;
//            }
//            return pinyins[0];
//        }
//    }
}
