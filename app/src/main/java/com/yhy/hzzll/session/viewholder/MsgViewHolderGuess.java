package com.yhy.hzzll.session.viewholder;

import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderText;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;


/**
 * Created by zhoujianghua on 2015/8/4.
 */
public class MsgViewHolderGuess extends MsgViewHolderText {

    public MsgViewHolderGuess(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }
//
//    @Override
//    protected String getDisplayText() {
//        GuessAttachment attachment = (GuessAttachment) message.getAttachment();
//
//        return attachment.getValue().getDesc() + "!";
//    }
}
