package com.yhy.hzzll.session.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.session.extension.CardAttachment;

/**
 * Created by chengying on 2017/8/24.
 */

public class MsgViewHolderCard extends MsgViewHolderBase {

//    private View view;

    private ImageView iv_avatar;
    private TextView tv_name;
    private TextView tv_address;
    private TextView tv_special;

    private CardAttachment msgAttachment;


    public MsgViewHolderCard(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return R.layout.business_card_item;
    }

    @Override
    protected void inflateContentView() {
//        view = View.inflate(context, R.layout.business_card_item,null);
        iv_avatar = findViewById(R.id.business_card_item_iv_avatar);
        tv_name = findViewById(R.id.business_card_item_tv_name);
        tv_address = findViewById(R.id.business_card_item_tv_address);
        tv_special = findViewById(R.id.business_card_item_tv_special);

    }

    @Override
    protected void bindContentView() {

        layoutDirection();

        msgAttachment = (CardAttachment) message.getAttachment();

        if (!msgAttachment.getAvatar().equals("")){
            Glide.with(context).load(msgAttachment.getAvatar()).into(iv_avatar);
        }

        tv_name.setText(msgAttachment.getName());
        tv_address.setText(msgAttachment.getAddress());
        tv_special.setText(msgAttachment.getSpecial());

    }


    private void layoutDirection() {

        if (isReceivedMessage()) {

            tv_name.setTextColor(Color.BLACK);
            tv_address.setTextColor(Color.BLACK);
            tv_special.setTextColor(Color.BLACK);

        } else {

            tv_name.setTextColor(Color.WHITE);
            tv_address.setTextColor(Color.WHITE);
            tv_special.setTextColor(Color.WHITE);

        }
    }


    @Override
    protected void onItemClick() {
        context.startActivity(new Intent().putExtra("user_id", msgAttachment.getId()).putExtra("title", msgAttachment.getName()).putExtra("from", "IM").setClass(context, LawyerOfficeActivity.class));
        super.onItemClick();
    }
}
