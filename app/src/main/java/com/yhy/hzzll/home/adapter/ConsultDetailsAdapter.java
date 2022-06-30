package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ConsultReplysEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;

/**
 * 咨询详情---发布方
 *
 * @author Yang
 */
public class ConsultDetailsAdapter extends BaseAdapter {

    private Context context;
    private int type;
    private List<ConsultReplysEntity.DataBean.ListBean> replyList;

    Timer timer = new Timer();

    /**
     * 用于语音播放
     */
    private MediaPlayer aMediaPlayer = null;
    private HttpDataUtils httpDataUtils;
    private OnClickListener payClickListener;// 支付
    private OnClickListener adoptListen;// 采纳
    private OnClickListener replyListen;// 评价
    private OnClickListener deleteListen;// 删除
    private OnClickListener pursueListen;// 打赏
    private OnClickListener thumbupListen;// 点赞
    private OnClickListener comment_and_lik;// 点赞和评论

    private Gson gson;
    // public static String id1, id2;
    public static String head;
    public static String name;
    private AnimationDrawable animationDrawable;
    CountDownTimer countDownTimer;
    private String id;

    private boolean closed = true;// 查看或收起点赞和评论

    private static final int TYPE1 = 0;//文字
    private static final int TYPE2 = 1;//语音

    int Current_play_position = -1;
    ConsultDetailsActivity mConsultDetailsActivity;

    public ConsultDetailsAdapter(String id, List<ConsultReplysEntity.DataBean.ListBean> replyList, Context context, OnClickListener comment_and_lik, OnClickListener pursueListen, ConsultDetailsActivity consultDetailsActivity) {

        mConsultDetailsActivity = consultDetailsActivity;
        this.id = id;
        this.replyList = replyList;
        this.context = context;
        this.payClickListener = payClickListener;
        this.adoptListen = adoptListen;
        this.replyListen = replyListen;
        this.deleteListen = deleteListen;
        this.pursueListen = pursueListen;
        this.comment_and_lik = comment_and_lik;
//        aMediaPlayer = new MediaPlayer();
        httpDataUtils = new HttpDataUtils(context);
        gson = new Gson();
    }

    private void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public int getItemViewType(int arg0) {
        if (replyList.get(arg0).getContent().length() == 0) {
            return TYPE2;
        } else {
            return TYPE1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return replyList.size();
    }

    @Override
    public Object getItem(int position) {
        return replyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        int type = getItemViewType(position);
        if (type == TYPE2) {
            ViewHolder2 holder2 = null;
            if (view == null) {
                holder2 = new ViewHolder2();
                view = LayoutInflater.from(context).inflate(R.layout.item_consult_voice_layout, null);
                holder2.iv_head = (ImageView) view.findViewById(R.id.iv_head);
                holder2.tv_name = (TextView) view.findViewById(R.id.tv_name);
                holder2.tv_adrees_date = (TextView) view.findViewById(R.id.tv_adrees_date);
                holder2.iv_adopt = (ImageView) view.findViewById(R.id.iv_adopt);
                holder2.tv_content = (TextView) view.findViewById(R.id.tv_content);
                holder2.tv_content_lenth = (TextView) view.findViewById(R.id.tv_content_lenth);
                holder2.tv_listen = (TextView) view.findViewById(R.id.tv_listen);
                holder2.tv_exceptional = (TextView) view.findViewById(R.id.tv_exceptional);
                holder2.iv_voice = (ImageView) view.findViewById(R.id.iv_voice);
                holder2.ll_voice_layout = (LinearLayout) view.findViewById(R.id.ll_voice_layout);
                holder2.tv_like = (TextView) view.findViewById(R.id.tv_like);
                holder2.tv_pursue = (TextView) view.findViewById(R.id.tv_pursue);
                holder2.tv_check_like = (TextView) view.findViewById(R.id.tv_check_like);
                holder2.tv_loading = (TextView) view.findViewById(R.id.tv_loading);
                holder2.linear__no_login_or_no_auth = (LinearLayout) view.findViewById(R.id.linear__no_login_or_no_auth);
                holder2.linear_content = (LinearLayout) view.findViewById(R.id.linear_content);
                view.setTag(holder2);
            } else {
                holder2 = (ViewHolder2) view.getTag();
            }

            ConsultReplysEntity.DataBean.ListBean listBean = replyList.get(position);

            Glide.with(context).load(listBean.getHead_img()).into(holder2.iv_head);

            holder2.tv_name.setText(listBean.getUsername() + "    " + listBean.getLawyer_type());

            holder2.tv_adrees_date.setText(listBean.getBase_region_id().getWhole_name() + "   " + "  " + listBean.getTime());

            holder2.iv_head.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LawyerOfficeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("user_id", replyList.get(position).getUsers_id() + "");
                    intent.putExtra("title", replyList.get(position).getUsername()).putExtra("insert", true);
//                        startActivity(new Intent().putExtra("user_id", mList.get(arg2 - 1).getUsers_id() + "").putExtra("title", mList.get(arg2 - 1).getTruename()).putExtra("from", "laynation").setClass(context, LawyerOfficeActivity.class));
                    context.startActivity(intent);
                }
            });

            final ViewHolder2 viewHolder2 = holder2;

            final String downloadUrl;
            downloadUrl = listBean.getFile_attachment_id();
            holder2.tv_content.setText("语音   ");
            holder2.iv_voice.setVisibility(View.VISIBLE);
            holder2.ll_voice_layout.setBackgroundResource(R.drawable.green);
            holder2.tv_content_lenth.setText(listBean.getSpeech_length() + "秒");

            final int recLen = Integer.valueOf(listBean.getSpeech_length() + "");
            holder2.iv_voice.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {

                    try {

                        mConsultDetailsActivity.killMediaPlayer();

                        if (aMediaPlayer!=null && aMediaPlayer.isPlaying()){
                            if (Current_play_position == position){
                                aMediaPlayer.pause();

                                if (animationDrawable!=null&&animationDrawable.isRunning()){
                                    animationDrawable.stop();
                                }

                                if (countDownTimer!=null){
                                    countDownTimer.cancel();
                                }

                            }else {
                                player((ImageView) v, viewHolder2.tv_content_lenth, recLen, downloadUrl, viewHolder2.tv_loading);
                                Current_play_position = position;
                            }

                        }else {
                            player((ImageView) v, viewHolder2.tv_content_lenth, recLen, downloadUrl, viewHolder2.tv_loading);
                            Current_play_position = position;
                        }


                    }catch (Exception e){
                        player((ImageView) v, viewHolder2.tv_content_lenth, recLen, downloadUrl, viewHolder2.tv_loading);
                        Current_play_position = position;
                    }



                }
            });

            holder2.ll_voice_layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        mConsultDetailsActivity.killMediaPlayer();

                        if (aMediaPlayer != null && aMediaPlayer.isPlaying()) {
                            if (Current_play_position == position) {
                                aMediaPlayer.pause();

                                if (animationDrawable != null && animationDrawable.isRunning()) {
                                    animationDrawable.stop();
                                }

                                if (countDownTimer != null) {
                                    countDownTimer.cancel();
                                }

                            }

                            else {
                                player(viewHolder2.iv_voice, viewHolder2.tv_content_lenth, recLen, downloadUrl, viewHolder2.tv_loading);
                                Current_play_position = position;
                            }

                        } else {
                            player(viewHolder2.iv_voice, viewHolder2.tv_content_lenth, recLen, downloadUrl, viewHolder2.tv_loading);
                            Current_play_position = position;
                        }
                    }catch (Exception e){
                        player(viewHolder2.iv_voice, viewHolder2.tv_content_lenth, recLen, downloadUrl, viewHolder2.tv_loading);
                        Current_play_position = position;
                    }
                }
            });

            holder2.tv_listen.setText("旁听 " + replyList.get(position).getCount_attend());
            holder2.tv_exceptional.setText("打赏 " + replyList.get(position).getCount_reward());
            holder2.tv_like.setText("点赞 " + replyList.get(position).getCount_like());

            holder2.tv_check_like.setTag(position);
            holder2.tv_check_like.setOnClickListener(comment_and_lik);

            if (replyList.get(position).getIs_adopt() == 0) {
                holder2.iv_adopt.setVisibility(View.GONE);
            } else {
                holder2.iv_adopt.setVisibility(View.VISIBLE);
            }

            if (PrefsUtils.getString(context, PrefsUtils.UID) != null) {
                if (PrefsUtils.getString(context, PrefsUtils.UID).equals(replyList.get(position).getUsers_id() + "")) {

                    holder2.tv_pursue.setVisibility(View.VISIBLE);

//                    if (replyList.get(position).getCount_pursue() == 0) {
//                        Resources resources = context.getResources();
//                        Drawable drawable = resources.getDrawable(R.drawable.meal_bg_grey);
//                        holder2.tv_pursue.setBackgroundDrawable(drawable);
//                        holder2.tv_pursue.setTextColor(Color.GRAY);
//                    } else {
                        holder2.tv_pursue.setTag(position);
                        holder2.tv_pursue.setOnClickListener(pursueListen);
//                    }
                } else {
                    holder2.tv_pursue.setVisibility(View.GONE);
                }
            } else {
                holder2.tv_pursue.setVisibility(View.GONE);
            }

            if (PrefsUtils.getString(context, PrefsUtils.UID) != null) {
                if (replyList.get(position).getIs_sns_like().equals("false")) {

                    Resources resources = context.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.like);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder2.tv_like.setCompoundDrawables(drawable, null, null, null);
                    holder2.tv_like.setText("点赞 " + replyList.get(position).getCount_like());
                    holder2.tv_like.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                } else {
                    Resources resources = context.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.zanhei);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder2.tv_like.setCompoundDrawables(drawable, null, null, null);
                    holder2.tv_like.setText("已点赞 " + replyList.get(position).getCount_like());

                }

            } else {
                Resources resources = context.getResources();
                Drawable drawable = resources.getDrawable(R.drawable.like);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                holder2.tv_like.setCompoundDrawables(drawable, null, null, null);
                holder2.tv_like.setText("点赞 " + replyList.get(position).getCount_like());
            }

//            if (PrefsUtils.getString(context, PrefsUtils.AUTH_STATUS).equals("已认证") && PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) != null) {
//
//            } else {
//                holder2.linear__no_login_or_no_auth.setVisibility(View.VISIBLE);
//                holder2.linear_content.setVisibility(View.GONE);
//            }

        } else {

            ViewHolder holder = null;

            if (view == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.item_consult_text_layout, null);
                holder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
                holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
                holder.tv_adrees_date = (TextView) view.findViewById(R.id.tv_adrees_date);
                holder.iv_adopt = (ImageView) view.findViewById(R.id.iv_adopt);
                holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
                holder.tv_listen = (TextView) view.findViewById(R.id.tv_listen);
                holder.tv_exceptional = (TextView) view.findViewById(R.id.tv_exceptional);
                holder.tv_like = (TextView) view.findViewById(R.id.tv_like);
                holder.tv_check_like = (TextView) view.findViewById(R.id.tv_check_like);
                holder.tv_pursue = (TextView) view.findViewById(R.id.tv_pursue);
                holder.linear__no_login_or_no_auth = (LinearLayout) view.findViewById(R.id.linear__no_login_or_no_auth);
                holder.linear_content = (LinearLayout) view.findViewById(R.id.linear_content);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            Glide.with(context).load(replyList.get(position).getHead_img()).into(holder.iv_head);

            holder.tv_name.setText(replyList.get(position).getUsername() + "    " + replyList.get(position).getLawyer_type());

            holder.tv_adrees_date.setText(
                    replyList.get(position).getBase_region_id().getWhole_name() + "   " +
//                    replyList.get(position).getUpdated_at() +
                            "  " + replyList.get(position).getTime());

            holder.iv_head.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LawyerOfficeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("user_id", replyList.get(position).getUsers_id() + "");
                    intent.putExtra("title", replyList.get(position).getUsername()).putExtra("insert", true);
//                        startActivity(new Intent().putExtra("user_id", mList.get(arg2 - 1).getUsers_id() + "").putExtra("title", mList.get(arg2 - 1).getTruename()).putExtra("from", "laynation").setClass(context, LawyerOfficeActivity.class));
                    context.startActivity(intent);
                }
            });

            holder.tv_content.setBackgroundResource(0);
            holder.tv_content.setTextColor(context.getResources().getColor(R.color.cgrey));
            holder.tv_content.setText(replyList.get(position).getContent() + "");

            holder.tv_listen.setText("旁听 " + replyList.get(position).getCount_attend());
            holder.tv_exceptional.setText("打赏 " + replyList.get(position).getCount_reward());
            holder.tv_like.setText("点赞 " + replyList.get(position).getCount_like());

            if (replyList.get(position).getIs_adopt() == 0) {
                holder.iv_adopt.setVisibility(View.GONE);
            } else {
                holder.iv_adopt.setVisibility(View.VISIBLE);
            }

            if (PrefsUtils.getString(context, PrefsUtils.UID) != null) {
                if (PrefsUtils.getString(context, PrefsUtils.UID).equals(replyList.get(position).getUsers_id() + "")) {

                    holder.tv_pursue.setVisibility(View.VISIBLE);
//                    if (replyList.get(position).getCount_pursue() == 0) {
//                        Resources resources = context.getResources();
//                        Drawable drawable = resources.getDrawable(R.drawable.meal_bg_grey);
//                        holder.tv_pursue.setBackgroundDrawable(drawable);
//                        holder.tv_pursue.setTextColor(Color.GRAY);
//
////                        holder.tv_pursue.setOnClickListener(pursueListen);
//
//                    } else {

                        holder.tv_pursue.setTag(position);
                        holder.tv_pursue.setOnClickListener(pursueListen);
//                    }
                } else {
                    holder.tv_pursue.setVisibility(View.GONE);
                }
            } else {
                holder.tv_pursue.setVisibility(View.GONE);
            }

            holder.tv_check_like.setTag(position);
            holder.tv_check_like.setOnClickListener(comment_and_lik);

            if (PrefsUtils.getString(context, PrefsUtils.UID) != null) {
                if (replyList.get(position).getIs_sns_like().equals("false")) {

                    Resources resources = context.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.like);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder.tv_like.setCompoundDrawables(drawable, null, null, null);

                    holder.tv_like.setText("点赞 " + replyList.get(position).getCount_like());

                    holder.tv_like.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                } else {
                    Resources resources = context.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.zanhei);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder.tv_like.setCompoundDrawables(drawable, null, null, null);
                    holder.tv_like.setText("已点赞 " + replyList.get(position).getCount_like());
                }

                final int a = replyList.get(position).getCount_like();

                final ViewHolder holdern = holder;

                holder.tv_like.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

            } else {
                Resources resources = context.getResources();
                Drawable drawable = resources.getDrawable(R.drawable.like);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                holder.tv_like.setCompoundDrawables(drawable, null, null, null);
                holder.tv_like.setText("点赞 " + replyList.get(position).getCount_like());
            }

//            if (PrefsUtils.getString(context, PrefsUtils.AUTH_STATUS).equals("已认证") && PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) != null) {
//
//            } else {
//                holder.linear__no_login_or_no_auth.setVisibility(View.VISIBLE);
//                holder.linear_content.setVisibility(View.GONE);
//            }

        }

        return view;
    }

    private void likes(String lawyer_id, String s, final TextView tv, final int a) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("consult_id", id);
        params.addBodyParameter("lawyer_id", lawyer_id);
        params.addBodyParameter("action", s);

//        LogUtils.logE("Authorization" + PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) + "......+consult_id" + id + "........lawyer_id" + lawyer_id);

        httpDataUtils.sendPost(MyData.CONSULT_LIKE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");

                    if (httpDataUtils.code(arg0.result)) {

                        ToastUtils.getUtils(context).show(msg);

                        if (msg.contains("取消")) {

                            tv.setText("点赞" + (a - 1));

                        } else {

                            tv.setText("已点赞" + (a + 1));

                        }
                    } else {

                        httpDataUtils.showMsg(arg0.result);

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


    private void player(final ImageView iv_voice, final TextView tv, final int recLen, String downloadUrl,
                        final TextView tv_loading) {

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
        aMediaPlayer = new MediaPlayer();
        try {
            aMediaPlayer.setDataSource(downloadUrl);
            aMediaPlayer.prepareAsync();
            tv_loading.setVisibility(View.VISIBLE);
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (SecurityException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        aMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if (percent < 100) {
                    tv_loading.setVisibility(View.VISIBLE);
                } else {
                    tv_loading.setVisibility(View.GONE);
                }
            }
        });

        aMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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

        aMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ToastUtils.getUtils(context).show("播放失败！");
                return true;
            }
        });

        aMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        if (null != aMediaPlayer) {
            aMediaPlayer.release();
        }

    }

    /**
     * 判断文件是否存在
     */
    public boolean fileIsExists(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    private class ViewHolder {
        private ImageView iv_head;
        private TextView tv_name;
        private TextView tv_adrees_date;
        private ImageView iv_adopt;
        private TextView tv_check_like;
        private TextView tv_content;
        private TextView tv_content_lenth;
        private TextView tv_listen;// 旁听
        private TextView tv_like;// 点赞
        private TextView tv_exceptional;// 打赏
        private TextView tv_pursue;//查看追问linear_content
        //        private TextView tv_no_login_or_no_auth;
        private LinearLayout linear_content;
        private LinearLayout linear__no_login_or_no_auth;

    }

    private class ViewHolder2 {
        private ImageView iv_head;// 头像
        private TextView tv_name;// 昵称
        private TextView tv_adrees_date;// 地址和日期
        private ImageView iv_adopt;// 采纳图标
        private LinearLayout ll_voice_layout;// 语音布局
        private TextView tv_content;// 回复内容
        private TextView tv_content_lenth;// 回复内容长度
        private ImageView iv_voice;// 语音播放动画
        private TextView tv_listen;// 旁听
        private TextView tv_like;// 点赞;
        private TextView tv_exceptional;// 打赏;
        private TextView tv_check_like;// 查看点赞和评论
        private TextView tv_pursue;//查看追问
        //        private TextView tv_no_login_or_no_auth;
        private LinearLayout linear_content;
        private LinearLayout linear__no_login_or_no_auth;
        private TextView tv_loading;// 正在加载...
    }
}