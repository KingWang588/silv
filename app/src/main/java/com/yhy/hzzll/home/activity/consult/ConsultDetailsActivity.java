package com.yhy.hzzll.home.activity.consult;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ConsultReplysEntity;
import com.yhy.hzzll.entity.Footprint;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.adapter.ConsultDetailsAdapter;
import com.yhy.hzzll.home.entity.ConsultDetial;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.ClickFilter;
import com.yhy.hzzll.utils.DateUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.CustomDialog;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.DialogVoice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 咨询详情===
 *
 * @author Yang
 */
public class ConsultDetailsActivity extends BaseActivity {

     ConsultDetailsActivity consultDetailsActivity;

    @ViewInject(R.id.lv_consult)
    private PullToRefreshListView lv_consult;

    private ConsultDetailsAdapter detailsAdapter;

    private CustomDialog customDialog;

    @ViewInject(R.id.iv_input)
    private ImageView iv_input;

    int clloctionnum;

    /**
     * 回复输入的内容
     */
    @ViewInject(R.id.ed_input)
    private EditText ed_input;

    @ViewInject(R.id.ll_edit_layout)
    private LinearLayout ll_edit_layout;

    @ViewInject(R.id.tv_sendvoice)
    private TextView tv_sendvoice;


    @ViewInject(R.id.tv_send)
    private TextView tv_send;
    private DialogVoice dialogVoice;
    private boolean isVoice;
    private static final int CAMERA_REQUEST_CODE = 1;

    // 语音相关
    private String mSoundData = "";// 语音数据
    private String mSoundName = "";// 语音文件名
    private String dataPath;
    private boolean isStop; // 录音是否结束的标志 超过两分钟停止
    private boolean isCanceled = false; // 是否取消录音
    private float downY;
    private MediaRecorder mRecorder;
    private long mStartTime;
    private long mEndTime;
    private int mTime;

    private int pisition = 0;

    @ViewInject(R.id.tv_comple)
    private TextView tv_comple;

    @ViewInject(R.id.rl_input)
    private RelativeLayout rl_input;
    @ViewInject(R.id.tv_focus)
    private TextView tv_focus;


    @ViewInject(R.id.head_iv_head)
    private ImageView head_iv_head;

    @ViewInject(R.id.head_iv_name)
    private TextView head_iv_name;

    @ViewInject(R.id.head_iv_adress_date)
    private TextView head_iv_adress_date;

    @ViewInject(R.id.head_iv_type)
    private TextView head_iv_type;

    @ViewInject(R.id.head_tv_content)
    private TextView head_tv_content;

    @ViewInject(R.id.head_tv_checkall)
    private TextView head_tv_checkall;

    @ViewInject(R.id.head_tv_view_num)
    private TextView head_tv_view_num;

    @ViewInject(R.id.head_tv_add_ask_num)
    private TextView head_tv_add_ask_num;

    @ViewInject(R.id.tv_explain)
    private TextView tv_explain;

    @ViewInject(R.id.head_tv_replynum)
    private TextView head_tv_replynum;

    @ViewInject(R.id.linear_no_reply)
    private LinearLayout linear_no_reply;

    @ViewInject(R.id.linear_voice)
    private LinearLayout linear_voice;

    @ViewInject(R.id.tv_content_lenth)
    private TextView tv_content_lenth;

    @ViewInject(R.id.iv_voice)
    private ImageView iv_voice;

    @ViewInject(R.id.tv_loading)
    private TextView tv_loading;

    @ViewInject(R.id.tv_collect)
    private TextView tv_collect;
    @ViewInject(R.id.tv_collect_num)
    private TextView tv_collect_num;

    public static int userType = 0;

    private boolean checkALL;

    private String id;

    private String title;

    private int index = 1;

    private int maxPages;

    private boolean isPullRefresh = true;

    ConsultDetial entity;

    private List<ConsultReplysEntity.DataBean.ListBean> replyList;
    private DialogLoading loading;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissDialog();
                            // 加载完成后停止刷新
                            lv_consult.onRefreshComplete();
                        }
                    }, 500);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle arg0) {
        consultDetailsActivity = this;
        setContentView(R.layout.activity_consult_details);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        viewInit();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onRestart() {
        getDetails();
        isPullRefresh = true;
        resetPageIndex();
        super.onRestart();
    }


    private void viewInit() {
        customDialog = new CustomDialog();
        dialogVoice = new DialogVoice();
        loading = new DialogLoading();
        replyList = new ArrayList<>();

        if (null != getIntent()) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            boolean b = getIntent().getBooleanExtra("insert", false);
            if (b) {
                //String nid, String addtime, String title, String cate_name
                Footprint footprint = new Footprint(id, DateUtils.getCurrentDate(), title, "用户咨询");
                LogUtils.logE(title);
                HzApplication.getDaoInstant().getFootprintDao().insert(footprint);
            }

        }

        lv_consult.setMode(Mode.BOTH);

        lv_consult.setScrollingWhileRefreshingEnabled(false);

//        View headview = View.inflate(context, R.layout.header_myconsult_details, null);
//        ViewUtils.inject(this, headview);
//
//        lv_consult.getRefreshableView().addHeaderView(headview);

        lv_consult.setEmptyView(linear_no_reply);
        detailsAdapter = new ConsultDetailsAdapter(id, replyList, context, comment_and_lik, pursueListen,consultDetailsActivity);
        lv_consult.setAdapter(detailsAdapter);
        lv_consult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        lv_consult.setMode(Mode.BOTH);
        // 刷新
        lv_consult.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                resetPageIndex();
                isPullRefresh = true;
                getReplyData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                addPageIndex();
                isPullRefresh = false;
                getReplyData();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        lv_consult.onRefreshComplete();
                    }
                }, 1000);
            }
        });

        getDetails();
    }

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }

    private void getDetails() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CONSULT_DETAILS + id, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        entity = gson.fromJson(arg0.result, ConsultDetial.class);
                        initViews(entity);
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
                ToastUtils.getUtils(context).show(msg);
            }
        });
    }

    private void initViews(final ConsultDetial consultDetial) {
        final ConsultDetial.DataBean entity = consultDetial.getData();

        Glide.with(ConsultDetailsActivity.this).load(entity.getAvatar()).into(head_iv_head);

        //咨询详情页取消点击用户头像 显示大图
//        head_iv_head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ConsultDetailsActivity.this, BigImageActivity.class);
//                intent.putExtra("url", entity.getAvatar());
//                startActivity(intent);
//            }
//        });

        head_iv_name.setText(entity.getNickname());

        try {
            head_iv_adress_date.setText(entity.getWhole_name().getWhole_name() + "   " + entity.getDate_time() + "   ");
        } catch (Exception e) {

        }

        head_iv_type.setText(entity.getLegal_name());

        if (entity.getContent().length() == 0) {

            head_tv_content.setVisibility(View.GONE);
            linear_voice.setVisibility(View.VISIBLE);

            tv_content_lenth.setText(entity.getSpeech_length() + "s");

            linear_voice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();

                        if (animationDrawable != null && animationDrawable.isRunning()) {
                            animationDrawable.stop();
                        }

                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                        }

                    } else {
                        player(iv_voice, tv_content_lenth, Integer.parseInt(entity.getSpeech_length()), entity.getFileurl());
                    }
                }
            });

        } else {

            head_tv_content.setText(entity.getContent());

            if (head_tv_content.getLineCount() > 4 || head_tv_content.getLineCount() == 4) {
                head_tv_checkall.setVisibility(View.VISIBLE);
                head_tv_checkall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        if (checkALL) {// 收起
                            head_tv_content.setMaxLines(4);
                            head_tv_checkall.setText("查看全部");
                        } else {// 查看全部
                            head_tv_content.setMaxLines(Integer.MAX_VALUE);
                            head_tv_checkall.setText("收起");
                        }
                        checkALL = !checkALL;
                    }
                });
            } else {
                head_tv_checkall.setVisibility(View.GONE);
            }

        }

        head_tv_view_num.setText("查看 " + entity.getCount_view() + "");
        head_tv_add_ask_num.setText("追问次数 " + entity.getCount_pursue() + "");

        if (entity.getCount_reply() == 0) {
            head_tv_replynum.setVisibility(View.GONE);
            linear_no_reply.setVisibility(View.VISIBLE);
        } else {
            head_tv_replynum.setVisibility(View.VISIBLE);
            head_tv_replynum.setText("律师回复（" + entity.getCount_reply() + ")");
            linear_no_reply.setVisibility(View.GONE);
        }

        if (PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.UID) != null && PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.UID).length() != 0) {

            if (entity.getCount() != 0) {
                tv_comple.setVisibility(View.VISIBLE);
                rl_input.setVisibility(View.GONE);
            }


            if (entity.getConsult_follower() != null) {
                if (entity.getConsult_follower().equals("0")) {
                    tv_focus.setText("已关注");
                } else {
                    tv_focus.setText("关注");
                }
            }
        }

        if (entity.getIs_closed() == 1) {
            tv_comple.setVisibility(View.VISIBLE);
            tv_comple.setText("该问题已经关闭不能回复！");
            rl_input.setVisibility(View.GONE);
        }

        getReplyData();
    }

    private MediaPlayer mMediaPlayer = null;
    private AnimationDrawable animationDrawable;
    CountDownTimer countDownTimer;

    private void player(final ImageView iv_voice, final TextView tv, final int recLen, String downloadUrl) {

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        detailsAdapter.killMediaPlayer();

        killMediaPlayer();// 播放前，先kill原来的mediaPlayer

        if (downloadUrl.equals("")) {
            ToastUtils.getUtils(context).show("该文件无法播放");
            return;
        }

        mMediaPlayer = new MediaPlayer();

        try {
            mMediaPlayer.setDataSource(downloadUrl);
            mMediaPlayer.prepareAsync();
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if (percent < 100) {
                    tv_loading.setVisibility(View.VISIBLE);
                } else {
                    tv_loading.setVisibility(View.GONE);
                }
            }
        });

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                countDownTimer = new CountDownTimer(recLen * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        tv.setText(millisUntilFinished / 1000 + "秒");
                    }

                    public void onFinish() {
                        tv.setText(recLen + "秒");
                    }
                }.start();

                iv_voice.setBackgroundResource(R.drawable.voice_animation);
                animationDrawable = (AnimationDrawable) iv_voice.getBackground();
                // 动画是否正在运行
                if (animationDrawable.isRunning()) {
                    // 停止动画播放
                    animationDrawable.stop();
                } else {
                    // 开始或者继续动画播放
                    animationDrawable.start();
                }

            }
        });

        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ToastUtils.getUtils(context).show("播放失败！");
                return true;
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (animationDrawable != null && animationDrawable.isRunning()) {
                    animationDrawable.stop();
                    iv_voice.setBackgroundResource(R.drawable.voice4);
                }
            }
        });

    }

    public void killMediaPlayer() {

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
            LogUtils.logE("执行了1。。。。。。。。。。。。。");
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
            LogUtils.logE("执行了2。。。。。。。。。。。。。");
        }

        if (null != mMediaPlayer) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            LogUtils.logE("执行了3。。。。。。。。。。。。。");
        }
    }

    /**
     * 获取本页回复列表的数据
     */
    private void getReplyData() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("issue_id", id);

        if (isPullRefresh) {
            params.addQueryStringParameter("page", index + "");
        } else {
            if (index > maxPages) {
                ToastUtils.getUtils(ConsultDetailsActivity.this).show("没有更多");
                return;
            } else {
                params.addQueryStringParameter("page", index + "");
            }
        }

        httpDataUtils.sendGet(MyData.ANSWERLIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                lv_consult.onRefreshComplete();
                if (httpDataUtils.code(arg0.result)) {
                    try {
                        LogUtils.logE(arg0.result);
                        JSONObject object = new JSONObject(arg0.result);
                        String code = JSONTool.getString(object, "code");
//                        String msg = JSONTool.getString(object, "msg");
                        if (code.equals("0")) {
                            ConsultReplysEntity consultReplysEntity = gson.fromJson(object.toString(), ConsultReplysEntity.class);

                            if (isPullRefresh) {
                                replyList.clear();
                                maxPages = consultReplysEntity.getData().getPages();
                            }

                            replyList.addAll(consultReplysEntity.getData().getList());

                            if (replyList.size() == 0) {
                                head_tv_replynum.setVisibility(View.VISIBLE);
                                linear_no_reply.setVisibility(View.VISIBLE);
                            } else {
                                head_tv_replynum.setVisibility(View.VISIBLE);
                                head_tv_replynum.setText("律师回复（" + consultReplysEntity.getData().getTotal() + ")");
                                linear_no_reply.setVisibility(View.GONE);
                            }

                            for (int i = 0; i < replyList.size(); i++) {
                                ConsultReplysEntity.DataBean.ListBean listBean = replyList.get(i);
                                if (PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.UID).equals(listBean.getUsers_id() + "")) {
                                    rl_input.setVisibility(View.GONE);
                                    replyList.remove(listBean);
                                    replyList.add(0, listBean);
                                }
                            }
                            detailsAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                } else {

                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                ToastUtils.getUtils(context).show(msg);
                mHandler.sendEmptyMessage(1);
            }
        });
    }

    /**
     * 文字回复
     */
    private void sendText(String input) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("consult_id", id);
        params.addHeader("Authorization", PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("content", input);
        params.addBodyParameter("speech_length", input.length() + "");

        if (input.length() < 20) {
            ToastUtils.getUtils(ConsultDetailsActivity.this).show("回答问题字数不能少于20个字");
            return;
        }

        if (input.length() > 1000) {
            ToastUtils.getUtils(ConsultDetailsActivity.this).show("回答问题字数不能超过1000个字");
            return;
        }

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
                        ed_input.getText().clear();

                        startActivity(new Intent(ConsultDetailsActivity.this, ReplySuccessActivity.class).putExtra("num", sort).putExtra("is_timeout",is_timeout));
                    } else {
                        httpDataUtils.showMsgNew(arg0.result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                if (msg.equals("Not Found")) {

                }
                mHandler.sendEmptyMessage(1);
            }
        });
    }


    private void jurisdiction() {

        AndPermission.with(ConsultDetailsActivity.this)
                .requestCode(102)
                .permission(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(permissionListener)
                .start();

    }

    private static final int REQUEST_CODE_SETTING = 300;

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
                case 102: {
                    if (AndPermission.hasPermission(ConsultDetailsActivity.this, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        startActivity(new Intent(ConsultDetailsActivity.this, VoiceReplyActivity.class).putExtra("id", id));
                    } else {
                        Toast.makeText(ConsultDetailsActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 102: {
                    Toast.makeText(ConsultDetailsActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };


    @OnClick({R.id.ic_back, R.id.iv_input, R.id.tv_send, R.id.ed_input, R.id.tv_focus, R.id.tv_explain, R.id.tv_sendvoice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sendvoice:

                detailsAdapter.killMediaPlayer();
                killMediaPlayer();

                if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) != null && PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).length() != 0) {
                    if (PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTH_STATUS).equals("已认证")) {
                        jurisdiction();
                    } else {
                        ToastUtils.getUtils(ConsultDetailsActivity.this).show("您未完成认证！不能回复问题");
                    }
                } else {
                    startActivity(new Intent(ConsultDetailsActivity.this, LoginActivity.class).putExtra("activity", ConsultDetailsActivity.class.toString()));
                    return;
                }

                break;
            case R.id.tv_explain:
                View view1 = View.inflate(ConsultDetailsActivity.this, R.layout.linear_tip, null);
                ImageView iv_close = (ImageView) view1.findViewById(R.id.iv_close);

                final Dialog dialog = new AlertDialog.Builder(ConsultDetailsActivity.this)
                        .setView(view1)
                        .create();

                dialog.show();


                iv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                break;

            case R.id.ic_back:
                killMediaPlayer();
                detailsAdapter.killMediaPlayer();
                finish();
                break;
            case R.id.tv_focus:

                if (PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTHORIZATION).equals("")) {

                    killMediaPlayer();
                    detailsAdapter.killMediaPlayer();
                    startActivity(new Intent(ConsultDetailsActivity.this, LoginActivity.class).putExtra("activity", ConsultDetailsActivity.class.toString()));

                    return;
                }


                if (ClickFilter.isFastClick()) {
                    if (tv_focus.getText().toString().equals("关注")) {
                        follow("true");
                    } else {
                        follow("false");
                    }
                }

                break;
            case R.id.iv_input:// 切换回复类型

                if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) != null && PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).length() != 0) {
                    if (PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTH_STATUS).equals("已认证")) {

                    } else {
                        ToastUtils.getUtils(ConsultDetailsActivity.this).show("您未完成认证！不能回复问题");
                        return;
                    }
                } else {
//                    ToastUtils.getUtils(ConsultDetailsActivity.this).show("您未登录！不能回复问题");
                    startActivity(new Intent(ConsultDetailsActivity.this, LoginActivity.class).putExtra("activity", ConsultDetailsActivity.class.toString()));

                    return;
                }

                if (isVoice) {
                    iv_input.setImageResource(R.drawable.icon_table_input);
                    tv_sendvoice.setVisibility(View.VISIBLE);
                    ll_edit_layout.setVisibility(View.GONE);
                } else {
                    iv_input.setImageResource(R.drawable.icon_voice_input);
                    tv_sendvoice.setVisibility(View.GONE);
                    ll_edit_layout.setVisibility(View.VISIBLE);
                }
                isVoice = !isVoice;
                break;
            case R.id.tv_send:// 发送

                if (ed_input.getText().toString().trim().isEmpty()) {
                    return;
                }
                sendText(ed_input.getText().toString().trim());
                break;
        }
    }

    private void follow(String s) {

        RequestParams params = new RequestParams();

        if (PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTHORIZATION) != null && !PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTHORIZATION).equals("")) {
            params.addHeader("Authorization", PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.AUTHORIZATION));
        } else {
            ToastUtils.getUtils(ConsultDetailsActivity.this).show("您还未登录！");
            return;
        }

        params.addBodyParameter("consult_id", id);
        params.addBodyParameter("follower_id", PrefsUtils.getString(ConsultDetailsActivity.this, PrefsUtils.UID));
        params.addBodyParameter("action", s);

        httpDataUtils.sendProgressPost(MyData.CONSULT_FOLLOW, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");

                    if (httpDataUtils.code(arg0.result)) {

                        httpDataUtils.showMsgNew(arg0.result);

                        if (tv_focus.getText().equals("关注")) {
                            tv_focus.setText("已关注");
                        } else {
                            tv_focus.setText("关注");
                        }

                    } else {
                        httpDataUtils.showMsgNew(arg0.result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                if (msg.equals("Not Found")) {

                }
                mHandler.sendEmptyMessage(1);
            }
        });
    }


    /**
     * 点赞和评论
     */
    private View.OnClickListener comment_and_lik = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            startActivity(new Intent(ConsultDetailsActivity.this, CommentsAndLikesActivity.class)
                    .putExtra("reply_id", replyList.get(position).getId() + "")
                    .putExtra("lawyer_id", replyList.get(position).getUsers_id() + "")
                    .putExtra("is_like", replyList.get(position).getIs_sns_like() + ""));
        }
    };


    /**
     * 追问
     */
    private View.OnClickListener pursueListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            startActivity(new Intent(ConsultDetailsActivity.this, PursueAskActivity.class)
                    .putExtra("reply_id", replyList.get(position).getId() + "").putExtra("id", entity.getData().getId() + "")
                    .putExtra("message_id", replyList.get(position).getMessage_id() + ""));
        }
    };


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DEL) {
            return true;
        } else {
            killMediaPlayer();
            detailsAdapter.killMediaPlayer();
            finish();
            return true;
        }

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            return true;
        } else return super.onKeyUp(keyCode, event);
    }


}
