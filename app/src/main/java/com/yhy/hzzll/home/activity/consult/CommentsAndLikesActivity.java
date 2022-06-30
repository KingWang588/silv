package com.yhy.hzzll.home.activity.consult;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.entity.CommentsAndLikes;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.ClickFilter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CommentsAndLikesActivity extends BaseActivity {

    @ViewInject(R.id.tv_num_like)
    private TextView tv_num_like;
    @ViewInject(R.id.tv_like)
    private TextView tv_like;
    @ViewInject(R.id.tv_num_comment)
    private TextView tv_num_comment;

    @ViewInject(R.id.linear_head_img)
    private LinearLayout linear_head_img;
    @ViewInject(R.id.linear_comment)
    private LinearLayout linear_comment;

    String reply_id;
    String lawyer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_and_likes);
        ViewUtils.inject(this);

        reply_id=getIntent().getStringExtra("reply_id");
        lawyer_id=getIntent().getStringExtra("lawyer_id");


       if( getIntent().getStringExtra("is_like").equals("true")){
           Resources resources = context.getResources();
           Drawable drawable = resources.getDrawable(R.drawable.zanhei);
           drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
           tv_like.setCompoundDrawables(drawable, null, null, null);
           tv_like.setText("已点赞");
       }else{
           tv_like.setText("点赞");
       }


        tv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PrefsUtils.getString(CommentsAndLikesActivity.this,PrefsUtils.AUTHORIZATION).equals("")){

                    startActivity(new Intent(CommentsAndLikesActivity.this, LoginActivity.class).putExtra("activity",CommentsAndLikesActivity.class.toString()));

                    return;
                }

                if(ClickFilter.isFastClick()){
                    if (tv_like.getText().toString().equals("点赞")){
                        likes("true");
                    }else{
                        likes("false");
                    }
                }else{

                }

            }
        });

        getCommentsALikes();

    }

    private void likes( String s) {
        RequestParams params = new RequestParams();

        if (PrefsUtils.getString(CommentsAndLikesActivity.this, PrefsUtils.AUTHORIZATION)!=null&&!PrefsUtils.getString(CommentsAndLikesActivity.this, PrefsUtils.AUTHORIZATION).equals("")){
            params.addHeader("Authorization", PrefsUtils.getString(CommentsAndLikesActivity.this, PrefsUtils.AUTHORIZATION));
        }else{
            ToastUtils.getUtils(CommentsAndLikesActivity.this).show("您还未登录！");
            return;
        }
        params.addBodyParameter("reply_id", reply_id);
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

                        if (msg.contains("取消")){

                            Resources resources = context.getResources();
                            Drawable drawable = resources.getDrawable(R.drawable.like);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            tv_like.setCompoundDrawables(drawable, null, null, null);

                            tv_like.setText("点赞");
                        }else{
                            Resources resources = context.getResources();
                            Drawable drawable = resources.getDrawable(R.drawable.zanhei);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            tv_like.setCompoundDrawables(drawable, null, null, null);

                            tv_like.setText("已点赞");
                        }

                        getCommentsALikes();

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




    private void getCommentsALikes() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CommentsAndLikesActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("reply_id", reply_id);
        params.addQueryStringParameter("lawyer_id", lawyer_id);
        httpDataUtils.sendProgressGet(MyData.COMMENTS_AND_LIKES, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        CommentsAndLikes entity = gson.fromJson(arg0.result, CommentsAndLikes.class);
                        loadheadImg(entity);
                    } else {
//						ToastUtils.getUtils(getApplicationContext()).show(msg);
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

    private void loadheadImg( CommentsAndLikes entity) {

        List<String> headimgs = entity.getData().getSns_like_head_img_list();

        tv_num_like.setText(headimgs.size()+"个赞");
        linear_head_img.removeAllViews();

        for (int i = 0; i <headimgs.size(); i++) {

            View view = View.inflate(CommentsAndLikesActivity.this,R.layout.item_like_pic,null);
            RoundedImageView imageView = (RoundedImageView) view.findViewById(R.id.iv_head);

            Glide.with(CommentsAndLikesActivity.this).load(headimgs.get(i)).into(imageView);
				linear_head_img.addView(view);
        }

        loadComments(entity.getData().getLawyer_evaluate_list().getList());
    }

    private void loadComments(List<CommentsAndLikes.DataBean.LawyerEvaluateListBean.ListBean> listBeens) {

        tv_num_comment.setText(listBeens.size()+"条评论");

        linear_comment.removeAllViews();

        for (int i = 0; i < listBeens.size() ; i++) {
            View view = View.inflate(CommentsAndLikesActivity.this,R.layout.item_member_comment,null);
            ImageView iv_avatar = view.findViewById(R.id.iv_avatar);
            TextView tv_username =  view.findViewById(R.id.tv_username);
            TextView tv_date =  view.findViewById(R.id.tv_date);
            RatingBar ratingbar_reply = view.findViewById(R.id.ratingbar_reply);
            TextView tv_content =  view.findViewById(R.id.tv_content);
            TextView tv_type =  view.findViewById(R.id.tv_type);

            tv_type.setVisibility(View.INVISIBLE);
            Glide.with(CommentsAndLikesActivity.this).load(listBeens.get(i).getAvatar()+"").into(iv_avatar);
            ratingbar_reply.setRating(listBeens.get(i).getStart_rate()/2);
            tv_username.setText(listBeens.get(i).getNickname());

            if (listBeens.get(i).getContent().length()==0){
                tv_content.setText("该用户未填写评价内容");
            }else{
                tv_content.setText(listBeens.get(i).getContent());
            }

            tv_date.setText(listBeens.get(i).getUpdated_at());

            linear_comment.addView(view);
        }

    }

    @OnClick({R.id.ic_back})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
        }

    }





}
