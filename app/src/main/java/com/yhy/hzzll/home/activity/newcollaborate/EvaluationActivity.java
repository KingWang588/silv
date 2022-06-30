package com.yhy.hzzll.home.activity.newcollaborate;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class EvaluationActivity extends BaseActivity {


    @ViewInject(R.id.et_evaluation)
    EditText et_evaluation;
    @ViewInject(R.id.evelate_rateingbar)
    RatingBar evelate_rateingbar;

    int application_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        ViewUtils.inject(this);

        application_id = getIntent().getIntExtra("application_id",0);


        evelate_rateingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });


    }


    @OnClick({R.id.tv_submit_evaluation, R.id.ic_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;

            case R.id.tv_submit_evaluation:

                evaluation();
                break;
        }
    }

    private void evaluation() {

        String summary = et_evaluation.getText().toString();

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("application_id", application_id+"");
        params.addBodyParameter("start_rate",evelate_rateingbar.getRating()+"");
        params.addBodyParameter("content", summary);
        httpDataUtils.sendProgressPost(MyData.EVALUATE_ORDER_EVALUATE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    ToastUtils.getUtils(getApplicationContext()).show(msg);
                    if (code.equals("0")) {
                        ToastUtils.getUtils(EvaluationActivity.this).show("评价成功！");
                        finish();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
