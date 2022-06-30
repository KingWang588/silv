package com.yhy.hzzll.my.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.business.session.constant.Extras;

import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.my.entity.OrderDetialsEntity;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.widget.TimeTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QuickConsultingOrderDetailsActivity extends BaseActivity {

    @ViewInject(R.id.tv_order_no)
    TextView tv_order_no;
    @ViewInject(R.id.tv_create_time)
    TextView tv_create_time;
    @ViewInject(R.id.tv_order_type)
    TextView tv_order_type;
    @ViewInject(R.id.tv_payed_user)
    TextView tv_payed_user;
    @ViewInject(R.id.tv_phone)
    TextView tv_phone;
    @ViewInject(R.id.tv_status)
    TextView tv_status;

    @ViewInject(R.id.tv_price)
    TextView tv_price;
    @ViewInject(R.id.tv_problem)
    TextView tv_problem;
    @ViewInject(R.id.tv_answer)
    TextView tv_answer;
    @ViewInject(R.id.evelate_content)
    TextView evelate_content;

    @ViewInject(R.id.tv_finish)
    TextView tv_finish;

    @ViewInject(R.id.iv_tip)
    ImageView iv_tip;


    @ViewInject(R.id.linear_answer)
    LinearLayout linear_answer;

    @ViewInject(R.id.linear_wait_reply)
    LinearLayout linear_wait_reply;

    @ViewInject(R.id.linear_wait_evleant)
    LinearLayout linear_wait_evleant;

    @ViewInject(R.id.linear_cancel)
    LinearLayout linear_cancel;

    @ViewInject(R.id.linear_evelate)
    LinearLayout linear_evelate;

    @ViewInject(R.id.linear_income_info)
    LinearLayout linear_income_info;

    @ViewInject(R.id.evelate_rateingbar)
    RatingBar evelate_rateingbar;

    @ViewInject(R.id.linear_voice)
    private LinearLayout linear_voice;

    @ViewInject(R.id.tv_content_lenth)
    private TextView tv_content_lenth;

    @ViewInject(R.id.linear_voice_p)
    private LinearLayout linear_voice_p;

    @ViewInject(R.id.tv_content_lenth_p)
    private TextView tv_content_lenth_p;

    @ViewInject(R.id.tv_loading)
    private TextView tv_loading;


    @ViewInject(R.id.tv_cancle_text)
    private TextView tv_cancle_text;

    @ViewInject(R.id.tv_all_price)
    private TextView tv_all_price;

    @ViewInject(R.id.tv_complaint_result)
    private TextView tv_complaint_result;

    @ViewInject(R.id.tv_money_to)
    private TextView tv_money_to;


    @ViewInject(R.id.linear_account_date)
    private LinearLayout linear_account_date;

    @ViewInject(R.id.linear_complaint)
    private LinearLayout linear_complaint;

    @ViewInject(R.id.linear_complaint_result)
    private LinearLayout linear_complaint_result;

    @ViewInject(R.id.linear_wait_sure)
    private LinearLayout linear_wait_sure;

    @ViewInject(R.id.linear_no_sure)
    LinearLayout linear_no_sure;


    @ViewInject(R.id.linear_income)
    LinearLayout linear_income;

    @ViewInject(R.id.tv_date_time)
    private TimeTextView tv_date_time;

    @ViewInject(R.id.tv_date_time_2)
    private TimeTextView tv_date_time_2;

    String order_no;
    String accid;
    String consult_id;
    String type;
    String reply_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_consulting_order_details);
        ViewUtils.inject(this);

        order_no = getIntent().getStringExtra("order_no");

        getDetials();

    }

    private void getDetials() {

        String url = MyData.ORDER_DETAIL + order_no;
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(QuickConsultingOrderDetailsActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    OrderDetialsEntity orderDetialsEntity = gson.fromJson(arg0.result, OrderDetialsEntity.class);
                    initView(orderDetialsEntity);
                }
            }

        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {

            }
        });
    }

    private void initView(final OrderDetialsEntity orderDetialsEntity) {
        tv_order_no.setText(orderDetialsEntity.getData().getOrder_info().getOrder_no());
        tv_create_time.setText(orderDetialsEntity.getData().getOrder_info().getData_time());
        tv_order_type.setText(orderDetialsEntity.getData().getOrder_info().getType_name());
        tv_payed_user.setText(orderDetialsEntity.getData().getOrder_info().getNickname());
        tv_phone.setText(orderDetialsEntity.getData().getOrder_info().getMobile());
        tv_status.setText(orderDetialsEntity.getData().getOrder_info().getOrder_status());
        tv_price.setText(orderDetialsEntity.getData().getOrder_info().getAmount());


        if (orderDetialsEntity.getData().getConsult().getContent() != null && orderDetialsEntity.getData().getConsult().getContent().length() != 0) {
            tv_problem.setText(orderDetialsEntity.getData().getConsult().getContent());
        } else {

            tv_problem.setVisibility(View.GONE);
            linear_voice_p.setVisibility(View.VISIBLE);
            tv_content_lenth_p.setText(orderDetialsEntity.getData().getConsult().getSpeech_length() + "s");

            linear_voice_p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player(tv_content_lenth, orderDetialsEntity.getData().getConsult().getSpeech_length(), orderDetialsEntity.getData().getConsult().getFile_attachment_id()+"");
                }
            });

        }

        if (orderDetialsEntity.getData().getOrder_info().getType_name().equals("快速咨询")) {

            consult_id = orderDetialsEntity.getData().getConsult().getId() + "";

            linear_evelate.setVisibility(View.GONE);
            tv_finish.setText("查看问题详情");
            type = "1";

            if (orderDetialsEntity.getData().getReply().getContent() != null && orderDetialsEntity.getData().getReply().getContent().length() != 0) {

                tv_answer.setText(orderDetialsEntity.getData().getReply().getContent());
            } else {

                tv_answer.setVisibility(View.GONE);
                linear_voice.setVisibility(View.VISIBLE);
                tv_content_lenth.setText(orderDetialsEntity.getData().getReply().getSpeech_length() + "s");

                linear_voice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player(tv_content_lenth, orderDetialsEntity.getData().getReply().getSpeech_length(), orderDetialsEntity.getData().getReply().getFile_attachment_id());
                    }
                });
            }

        } else {
            accid = orderDetialsEntity.getData().getOrder_info().getAccid();
            linear_answer.setVisibility(View.GONE);

            if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("待回复")){
                linear_wait_reply.setVisibility(View.VISIBLE);
                Integer integer = orderDetialsEntity.getData().getOrder_info().getSuccess_pay_time();
                integer = integer + 24*60*60;
                timecountdown(integer);
                tv_finish.setText("去回复");
            }else if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("进行中")){
                linear_wait_evleant.setVisibility(View.VISIBLE);

                if(orderDetialsEntity.getData().getReply()!=null){
                    reply_id = orderDetialsEntity.getData().getReply().getReply_id();
                    Integer integer = orderDetialsEntity.getData().getReply().getReply_time();
                    integer = integer + 48*60*60;
                    timecountdown2(integer);
                }

                tv_finish.setText("去回复");

            }else if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("已取消")){
                linear_cancel.setVisibility(View.VISIBLE);

                if (orderDetialsEntity.getData().getOrder_info().getOrder_status_id().equals("4")){
                    tv_cancle_text.setText("用户已取消付款");
                }

                tv_finish.setVisibility(View.GONE);

            }else if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("投诉中")){
                linear_complaint.setVisibility(View.VISIBLE);

                tv_finish.setText("去回复");

            }else if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("待确认")){
               linear_wait_sure.setVisibility(View.VISIBLE);

                tv_finish.setText("查看回复");

            }else if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("已完成")){

                if (orderDetialsEntity.getData().getComplaint()!=null){
                    linear_complaint_result.setVisibility(View.VISIBLE);
                    //3 打钱给律师
                    if (orderDetialsEntity.getData().getComplaint().getHandle_result().equals("3")){
                        tv_money_to.setVisibility(View.VISIBLE);
                        tv_money_to.setText("已将款项打入您的余额");
                    }else  if (orderDetialsEntity.getData().getComplaint().getHandle_result().equals("2")) {
//                        tv_money_to.setVisibility(View.GONE);
                        tv_money_to.setVisibility(View.VISIBLE);
                        tv_money_to.setText("咨询费用已经退给了用户");
                    }

                    tv_complaint_result.setText("处理意见:"+orderDetialsEntity.getData().getComplaint().getHandle_opinion());

                }else {
                   linear_no_sure.setVisibility(View.VISIBLE);
                }

                tv_finish.setText("查看对话");
            }

           if(orderDetialsEntity.getData().getEvaluate().size()!=0){

                linear_no_sure.setVisibility(View.GONE);

               linear_evelate.setVisibility(View.VISIBLE);
               evelate_rateingbar.setRating((float)(orderDetialsEntity.getData().getEvaluate().get(0).getStart_rate()/2));
               evelate_content.setText(orderDetialsEntity.getData().getEvaluate().get(0).getContent());
           }else{
               linear_evelate.setVisibility(View.GONE);
           }


            type = "2";
        }


        if (orderDetialsEntity.getData().getOrder_info().getOrder_status().equals("已完成")){

            linear_income.setVisibility(View.VISIBLE);

            if (orderDetialsEntity.getData().getIncome_lists().size()!=0){

                for (int i = 0; i < orderDetialsEntity.getData().getIncome_lists().size(); i++) {
                    View view = View.inflate(QuickConsultingOrderDetailsActivity.this,R.layout.item_income_account_date,null);
                    TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                    TextView tv_amount = (TextView) view.findViewById(R.id.tv_amount);
                    tv_title.setText(orderDetialsEntity.getData().getIncome_lists().get(i).getTitle());

                    String m1 = orderDetialsEntity.getData().getIncome_lists().get(i).getAmount();
//                m1 = m1.substring(0,m1.length()-6);

                    tv_amount.setText(m1+"元");

                    linear_account_date.addView(view);
                }
            }

            tv_all_price.setText(orderDetialsEntity.getData().getIncome_total()+"元");




        }else {
            linear_income.setVisibility(View.GONE);

        }

    }


    private MediaPlayer mMediaPlayer = null;
    private AnimationDrawable animationDrawable;
    CountDownTimer countDownTimer;

    private void player(final TextView tv, final int recLen, String downloadUrl) {

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

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

//                iv_voice.setBackgroundResource(R.drawable.voice_animation);
//                animationDrawable = (AnimationDrawable) iv_voice.getBackground();
                // 动画是否正在运行
//                if (animationDrawable.isRunning()) {
//                    // 停止动画播放
//                    animationDrawable.stop();
//                } else {
//                    // 开始或者继续动画播放
//                    animationDrawable.start();
//                }
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
//                    iv_voice.setBackgroundResource(R.drawable.voice4);
                }
            }
        });

    }


    public void killMediaPlayer() {
        // TODO Auto-generated method stub
        if (null != mMediaPlayer) {
            mMediaPlayer.release();
        }
    }

    @OnClick({R.id.ic_back, R.id.tv_finish,R.id.iv_tip,R.id.tv_home,R.id.tv_invite_evleant})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_invite_evleant:
                    sendReplyBuy();
                break;
            case R.id.tv_home:
                startActivity(new Intent().putExtra("HOME", true).setClass(QuickConsultingOrderDetailsActivity.this, MainActivity.class));
                break;

            case R.id.iv_tip:

                if (type.equals("1")){
                    new AlertDialog.Builder(QuickConsultingOrderDetailsActivity.this)
//                        .setTitle("收益信息")
//                        .setMessage("收益信息里的信息随着你的回复被采纳、追问、打赏、旁听等用户的操作而变化。其中追问红包咨询人的前两位及时回复追问的律师才有机会领取到红包哦！请及时回应用户的追问。")
                            .setView(R.layout.dialog_tip)
                            .setPositiveButton("确定", null)
                            .show();
                }else {
                    new AlertDialog.Builder(QuickConsultingOrderDetailsActivity.this)
//                        .setTitle("收益信息")
//                        .setMessage("收益信息里的信息随着你的回复被采纳、追问、打赏、旁听等用户的操作而变化。其中追问红包咨询人的前两位及时回复追问的律师才有机会领取到红包哦！请及时回应用户的追问。")
                            .setView(R.layout.dialog_tip2)
                            .setPositiveButton("确定", null)
                            .show();
                }



                break;
            case R.id.ic_back:

                killMediaPlayer();

                finish();
                break;
            case R.id.tv_finish:

                if (type.equals("1")) {

                    startActivity(new Intent(QuickConsultingOrderDetailsActivity.this, ConsultDetailsActivity.class).putExtra("id", consult_id));

                } else {

                    Intent intent = new Intent();
                    intent.putExtra(Extras.EXTRA_ACCOUNT, accid);
                    intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                    intent.setClass(QuickConsultingOrderDetailsActivity.this, MyNewMessageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                }

                break;
        }
    }


    private void sendReplyBuy() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(QuickConsultingOrderDetailsActivity.this, PrefsUtils.AUTHORIZATION));
        String url = MyData.INVITE_BUY+reply_id+"-28";

        httpDataUtils.sendProgressGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    if (code.equals("0")) {

                        ToastUtils.getUtils(QuickConsultingOrderDetailsActivity.this).show("邀请评价成功！");

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

            }
        });
    }

    private int day;
    private int hour;
    private int minute;
    private int second;

    private void timecountdown(int time) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());// 格式化时间
        String nowtime = date.format(new Date());// 按以上格式 将当前时间转换成字符串
        String date1 = date.format(new Date(new Long(time)));
        daysBetween(nowtime, date.format(transForDate(time)));
        int[] times = {day, hour, minute, second};
        tv_date_time.setTimes(times);
        if (!tv_date_time.isRun()) {
            tv_date_time.run();
        }
    }

    private void timecountdown2(int time) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());// 格式化时间
        String nowtime = date.format(new Date());// 按以上格式 将当前时间转换成字符串
        String date1 = date.format(new Date(new Long(time)));
        daysBetween(nowtime, date.format(transForDate(time)));
        int[] times = {day, hour, minute, second};
        tv_date_time_2.setTimes(times);
        if (!tv_date_time_2.isRun()) {
            tv_date_time_2.run();
        }
    }


    /**
     * 字符串转日期，格式为："yyyy-MM-dd HH:mm:ss"
     * @param dateStr
     * @return
     */
    public static Date formatDate(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result=null;
        try {
            result = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public static Integer transForMilliSecond(Date date){
        if(date==null) return null;
        return (int)(date.getTime()/1000);
    }

    public static Date transForDate(Integer ms) {
        if (ms == null) {
            ms = 0;
        }
        long msl = (long) ms * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = null;
        if (ms != null) {
            try {
                String str = sdf.format(msl);
                temp = sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }


    /**
     * 字符串的日期格式的计算相差多少天
     */
    private boolean daysBetween(String nowdate, String time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(time);
            Date d2 = df.parse(nowdate);

            long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别

            int ss = 1000;
            int mi = ss * 60;
            int hh = mi * 60;
            int dd = hh * 24;

            long day = diff / dd;
            long hour = (diff - day * dd) / hh;
            long minute = (diff - day * dd - hour * hh) / mi;
            long second = (diff - day * dd - hour * hh - minute * mi) / ss;

            String strDay = day < 10 ? "0" + day : "" + day; // 天
            String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
            String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
            String strSecond = second < 10 ? "0" + second : "" + second;// 秒

            this.day = Integer.valueOf(strDay);
            this.hour = Integer.valueOf(strHour);
            this.minute = Integer.valueOf(strMinute);
            this.second = Integer.valueOf(strSecond);

            return d1.getTime() == d2.getTime() || d1.getTime() < d2.getTime();

        } catch (Exception e) {
        }
        return false;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){

            return true;
        } else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP) {

            return true;
        } else if(keyCode==KeyEvent.KEYCODE_DEL){
            return true;
        }else {
            killMediaPlayer();
            finish();
            return true;
        }
    }




}
