package com.yhy.hzzll.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.MessageListEntity;
import com.yhy.hzzll.entity.MessageListEntity.NewmsgBean;
import com.yhy.hzzll.entity.OtherUserEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.utils.DateUtils;
import com.yhy.hzzll.utils.ExpressionUtil;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.Tools;
import com.yhy.hzzll.view.DialogLoading;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MessageDetailChatAdapter extends BaseAdapter {
    private static final String TAG = MessageDetailChatAdapter.class.getSimpleName();
    private List<NewmsgBean> coll;
    MessageListEntity mEntity;
    private Context ctx;
    private LayoutInflater mInflater;

    private HttpDataUtils httpDataUtils;

    private OtherUserEntity otherUserEntity;

    private DialogLoading loading;

    private String imgtrue;

    private Activity activity;

    public interface IMsgViewType {
        int IMVT_COM_MSG = 0;
        int IMVT_TO_MSG = 1;
    }

    public MessageDetailChatAdapter() {
        httpDataUtils = new HttpDataUtils(ctx);
        otherUserEntity = new OtherUserEntity();
        loading = new DialogLoading();
    }

    public MessageDetailChatAdapter(Activity activity, Context context,
                                    MessageListEntity entity, String imgtrue) {
        this.activity = activity;
        this.ctx = context;
        this.mEntity = entity;
        this.coll = entity.getNewmsg();
        this.imgtrue = imgtrue;
        mInflater = LayoutInflater.from(context);
        httpDataUtils = new HttpDataUtils(ctx);
        otherUserEntity = new OtherUserEntity();
        loading = new DialogLoading();
    }

    @Override
    public int getCount() {
        return coll.size();
    }

    @Override
    public Object getItem(int position) {
        return coll.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        NewmsgBean entity = coll.get(position);
        if ("0".equals(entity.getIs_mine())) {
            return IMsgViewType.IMVT_COM_MSG;
        } else {
            return IMsgViewType.IMVT_TO_MSG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewmsgBean entity = coll.get(position);
        String isComMsg = entity.getIs_mine();
        ViewHolder viewHolder = null;
        if (convertView == null) {
            if ("0".equals(isComMsg)) {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);
            } else {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.iv_userhead = (ImageView) convertView.findViewById(R.id.iv_userhead);
            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.isComMsg = isComMsg;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvSendTime.setText(DateUtils.timestampToString(Tools.stringToLong(entity.getCtime()) * 1000, "yyyy-MM-dd HH:mm"));
        if ("0".equals(isComMsg)) {
            Glide.with(ctx).load(MyData.IP + mEntity.getImgurl()).into(viewHolder.iv_userhead);
        } else {
            Glide.with(ctx).load(MyData.IP + imgtrue).into(viewHolder.iv_userhead);
        }
        viewHolder.tvUserName.setText(mEntity.getNickname());
        String str = entity.getMsg(); // 消息具体内容
        String zhengze = "f0[0-9]{2}|f10[0-7]"; // 正则表达式，用来判断消息内是否有表情
        try {
            SpannableString spannableString = ExpressionUtil.getExpressionString(ctx, str, zhengze);
            viewHolder.tvContent.setText(spannableString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHolder.iv_userhead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Logininfo(mEntity.getUserid());
            }
        });

        return convertView;
    }

    /**
     * 获取用户信息
     */
    public void Logininfo(String uid) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("uid", uid);
        loading.showDialog(activity);
        httpDataUtils.sendGet(MyData.GET_INFOBYID, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                loading.dismissDialog();
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("000000")) {
                        Gson gson = new Gson();
                        otherUserEntity = gson.fromJson(httpDataUtils.data(arg0.result), OtherUserEntity.class);
                        if (otherUserEntity.getUtype().equals("1")) {
                            Intent intent = new Intent(ctx, LawyerOfficeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra(LawyerOfficeActivity.USER_ID_INTENT, otherUserEntity.getUserid()).putExtra("title", otherUserEntity.getTruename()).putExtra("from", "chatDetialactivity");
                            ctx.startActivity(intent);
                        }
                    } else {
                        ToastUtils.getUtils(ctx).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        httpDataUtils.setFailBack(new FailBack() {
            @Override
            public void fail(String msg) {
                loading.dismissDialog();
            }
        });
    }

    static class ViewHolder {
        public ImageView iv_userhead;
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public String isComMsg;
    }

}
