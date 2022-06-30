package com.yhy.hzzll.home.activity.consult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.utils.TimeMethod;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class VoiceReplyActivity extends BaseActivity {


    private LinearLayout mLayoutRecord;
    private LinearLayout linear_voice_reply;
    private RelativeLayout mLayoutPlay;

    private TextView mTvPlay;
    private TextView mTvRec;
    private TextView mTvSent;

    private VoiceManager voiceManager;
    private String mRecPath = "";

    private long mTime;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_reply);
        id = getIntent().getStringExtra("id");

        ImageView iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLayoutRecord = (LinearLayout) findViewById(R.id.layout_record);
        linear_voice_reply = (LinearLayout) findViewById(R.id.linear_voice_reply);
        mLayoutPlay = (RelativeLayout) findViewById(R.id.layout_play);

        mTvPlay = (TextView) findViewById(R.id.tv_play);
        mTvRec = (TextView) findViewById(R.id.tv_rec);
        mTvSent = (TextView) findViewById(R.id.tv_sent);

        mTvSent.setEnabled(false);
        mTvPlay.setEnabled(false);

        voiceManager = new VoiceManager(VoiceReplyActivity.this, "/com.yhy.hzzll/audio");

        voiceManager.setVoiceListener(new VoiceCallBack() {
            @Override
            public void voicePath(String path, long time) {
                mRecPath = path;
                mTime = time;
            }

            @Override
            public void recFinish() {

                    linear_voice_reply.setVisibility(View.VISIBLE);
                    mLayoutRecord.setVisibility(View.GONE);
                    mTvPlay.setVisibility(View.VISIBLE);
                    mTvRec.setEnabled(true);
                    mTvRec.setText("重新录音");
                    mTvPlay.setEnabled(true);
                    mTvSent.setEnabled(true);
            }

        });

        mTvRec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayoutRecord.setVisibility(View.VISIBLE);
                mLayoutPlay.setVisibility(View.GONE);
                linear_voice_reply.setVisibility(View.GONE);
                mTvRec.setText("重新录音");
                voiceManager.sessionRecord(true);
            }
        });

        mTvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTvPlay.setVisibility(View.INVISIBLE);
                mTvPlay.setEnabled(false);
                mLayoutRecord.setVisibility(View.GONE);
                mLayoutPlay.setVisibility(View.VISIBLE);
                voiceManager.sessionPlay(true, mRecPath);
            }
        });

        mTvSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                voiceManager.stopMedia(voiceManager.mMediaPlayer,true);
                sendVoice();
            }
        });

    }

    /**
     * 语音回复
     */
    private void sendVoice() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("consult_id", id);
        params.addHeader("Authorization", PrefsUtils.getString(VoiceReplyActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("file", new File(mRecPath));

        LogUtils.logE(mRecPath);

        long min = TimeMethod.timeSpanSecond(mTime).mSpanMinute;
        long s = TimeMethod.timeSpanSecond(mTime).mSpanSecond;

        s = min * 60 + s;

        params.addBodyParameter("speech_length", s + "");
        LogUtils.logE(mTime + "  *****  ");

        httpDataUtils.sendProgressPost(MyData.PUBLISHANSWER, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);

                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");

                    if (httpDataUtils.code(arg0.result)) {

                        JSONObject data = object.getJSONObject("data");
                        String sort = data.getString("sort");
                        String is_timeout = data.getString("is_timeout");
                        try {
                            File file = new File(mRecPath);
                            file.delete();

                        } catch (Exception e) {

                        }

                        startActivity(new Intent(VoiceReplyActivity.this, ReplySuccessActivity.class).putExtra("num", sort).putExtra("is_timeout",is_timeout));
                        finish();
                    } else {
                        httpDataUtils.showMsg(arg0.result);
//                    mHandler.sendEmptyMessage(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {

            }
        });
    }


}
