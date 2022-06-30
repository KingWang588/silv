package com.yhy.hzzll.session.action;


import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengying on 2017/8/23.
 */

public class EvaluateAction extends BaseAction {

    public EvaluateAction() {
        super(R.drawable.messageview_icon_evaluate, R.string.input_panel_evaluate);
    }

    @Override
    public void onClick() {
        IMMessage msg = MessageBuilder.createTipMessage(getAccount(), getSessionType());
        msg.setContent("您已邀请用户对您的咨询进行评价。");

        Map<String, Object> data = new HashMap<>();

        if (PrefsUtils.getString(getActivity(),PrefsUtils.IS_HAS_REPLY).equals("1")){
            if (PrefsUtils.getString(getActivity(),PrefsUtils.ORDER_NO).equals("")){
                ToastUtils.getUtils(getActivity()).show("没有可评价订单");
                return;
            }else{
                data.put("order_no", PrefsUtils.getString(getActivity(),PrefsUtils.ORDER_NO));
                msg.setRemoteExtension(data);
            }
        }else  if (PrefsUtils.getString(getActivity(),PrefsUtils.IS_HAS_REPLY).equals("2")) {
            ToastUtils.getUtils(getActivity()).show("不能邀请评价！");
            return;
        }else {
            ToastUtils.getUtils(getActivity()).show("您还未回复该咨询，不能邀请评价！");
            return;
        }




        CustomMessageConfig config = new CustomMessageConfig();
        config.enablePush = false; // 不推送
        msg.setConfig(config);

        sendMessage(msg);
    }

}
