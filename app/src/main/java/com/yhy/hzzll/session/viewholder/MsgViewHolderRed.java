package com.yhy.hzzll.session.viewholder;

import android.content.Intent;

import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;

import com.yhy.hzzll.R;
import com.yhy.hzzll.message.CheckRedPacketActivity;
import com.yhy.hzzll.session.extension.RedPacketAttachment;

/**
 * Created by chengying on 2017/9/1.
 */

public class MsgViewHolderRed extends MsgViewHolderBase {

    private RedPacketAttachment msgAttachment;
    String account;
    public MsgViewHolderRed(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return  R.layout.red_packet_item;
    }

    @Override
    protected void inflateContentView() {

    }

    @Override
    protected void bindContentView() {
        msgAttachment = (RedPacketAttachment) message.getAttachment();
        account = message.getFromAccount();
    }

    @Override
    protected void onItemClick() {
        context.startActivity(new Intent().putExtra("money", msgAttachment.getMoney()).putExtra("account", account).setClass(context, CheckRedPacketActivity.class));
        super.onItemClick();
    }

    @Override
    protected int leftBackground() {
        return 0;
    }

}
