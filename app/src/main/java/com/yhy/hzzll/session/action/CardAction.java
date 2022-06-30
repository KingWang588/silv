package com.yhy.hzzll.session.action;


import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.session.extension.CardAttachment;
import com.yhy.hzzll.utils.PrefsUtils;

/**
 * Created by chengying on 2017/8/23.
 */

public class CardAction extends BaseAction {


    public CardAction() {
        super(R.drawable.messageview_icon_card, R.string.input_panel_card);
    }

    @Override
    public void onClick() {
        CardAttachment cardAttachment = new CardAttachment();

        cardAttachment.setName(PrefsUtils.getString(getActivity(),PrefsUtils.UNAME));
        cardAttachment.setAddress(PrefsUtils.getString(getActivity(),PrefsUtils.ADDRESS));
        cardAttachment.setSpecial(PrefsUtils.getString(getActivity(),PrefsUtils.SPECIAL));
        cardAttachment.setAvatar(PrefsUtils.getString(getActivity(),PrefsUtils.AVATAR));
        cardAttachment.setId(PrefsUtils.getString(getActivity(),PrefsUtils.UID));


        CustomMessageConfig config = new CustomMessageConfig();
        config.enableHistory = true;
        config.enableRoaming = false;
        config.enableSelfSync = false;
        IMMessage cardMessage = MessageBuilder.createCustomMessage(getAccount(), getSessionType(), "", cardAttachment, config);
        sendMessage(cardMessage);
    }

}
