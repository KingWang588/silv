package com.yhy.hzzll.message;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MessageDetailChatAdapter;
import com.yhy.hzzll.entity.ChatMsgEntity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.entity.MessageListEntity;
import com.yhy.hzzll.entity.MessageListEntity.NewmsgBean;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MessageDetailChatActivity extends BaseActivity {

	private View view;
	@ViewInject(R.id.lv_message)
	private ListView lv_message;
	@ViewInject(R.id.et_sendmessage)
	EditText et_sendmessage;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	public static final String MESSAGE_INTENT = "MESSAGE_INTENT";
	private MessageDetailChatAdapter messageChatAdapter;
	private MessageListEntity entity;
	private List<NewmsgBean> msgList;
	private DataUserEntity userEntity;

	@Override
	protected void onCreate(Bundle arg0) {
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_message_detail_chat);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		viewInit();

	}

	private void msgUpdata(String uid) {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
		params.addQueryStringParameter("msg_type", "admin");
		params.addQueryStringParameter("person", uid);

		httpDataUtils.sendProgressGet(MyData.MSGUPDATE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				Log.e("wwwwwwwww", arg0.result);
			}
		});

		httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

			@Override
			public void fail(String msg) {
//				Log.e("wwwwwwwww fail", msg);

			}
		});

	}

	public void viewInit() {
		entity = (MessageListEntity) getIntent().getSerializableExtra(
				MESSAGE_INTENT);
		msgList = entity.getNewmsg();

		if (msgList == null) {
			msgList = new ArrayList<NewmsgBean>();
		}
		// Collections.reverse(msgList);
		userEntity = (DataUserEntity) HzApplication.getInstance()
				.getUserEntityCache().getAsObject(Constans.USER_INFO);
		if (userEntity == null) {
			startActivity(new Intent().putExtra("activity", MessageDetailChatActivity.class.toString()).setClass(MessageDetailChatActivity.this,
					LoginActivity.class));
			finish();
			return;
		}
		tv_title.setText(entity.getNickname());
		lv_message.setOnItemClickListener(new ListViewOnItemListener());
//		messageChatAdapter = new MessageDetailChatAdapter(
//				MessageDetailChatActivity.this, MessageDetailChatActivity.this,
//				entity, userEntity.getImgurl());
//		lv_message.setAdapter(messageChatAdapter);
//		refreshMsg();
	}

	class ListViewOnItemListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// Toast.makeText(MessageDetailChatActivity.this, arg2 + "",
			// Toast.LENGTH_SHORT).show();

		}

	}

	// private String[] msgArray = new String[] { "有大吗", "有！你呢？", "我也有", "那上吧",
	// "打啊！你放大啊", "你tm咋不放大呢？留大抢人头那！Cao的。你个菜b",
	// "2B不解释", "尼滚....", };
	// private String[] dateArray = new String[] { "2012-12-09 18:00",
	// "2012-12-09 18:10", "2012-12-09 18:11",
	// "2012-12-09 18:20", "2012-12-09 18:30", "2012-12-09 18:35",
	// "2012-12-09 18:40", "2012-12-09 18:50" };

	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

	// private void initData() {
	// for (int i = 0; i < 8; i++) {
	// ChatMsgEntity entity = new ChatMsgEntity();
	// entity.setDate(dateArray[i]);
	// if (i % 2 == 0) {
	// entity.setName("小黑");
	// entity.setMsgType(true);
	// } else {
	// entity.setName("人马");
	// entity.setMsgType(false);
	// }
	// entity.setText(msgArray[i]);
	// mDataArrays.add(entity);
	// }
	// messageChatAdapter = new MessageDetailChatAdapter(this, mDataArrays);
	// lv_message.setAdapter(messageChatAdapter);
	// }

	@OnClick({  R.id.ic_back, R.id.btn_send })
	public void Onclick(View view) {
		switch (view.getId()) {
//		case R.id.iv_more:
//			MessagePopupWindow window = new MessagePopupWindow(this);
//			window.showAsDropDown(view, 0, 0);
//			break;
		case R.id.ic_back:
			finish();
			break;
		case R.id.btn_send:
			String content = et_sendmessage.getText().toString();
			if (content.length() > 0) {
				sendMsg(entity.getUserid(), content);
				// NewmsgBean entity = new NewmsgBean();
				// entity.setCtime(System.currentTimeMillis() + "");
				// entity.set("人马");
				// entity.setMsgType(false);
				// entity.setText(content);
				// msgList.add(entity);
				// mDataArrays.add(entity);
				// 更新listview
				et_sendmessage.setText("");
				// page_select.setVisibility(page_select.GONE);
				messageChatAdapter.notifyDataSetChanged();
				lv_message.setSelection(lv_message.getCount() - 1);
			} else {
				Toast.makeText(this, "不能发送空消息", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}

	/** 华债消息添加 */
	private void sendMsg(final String person, String msg) {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization",
				PrefsUtils.getString(this, PrefsUtils.ACCESSKEY));
		params.addBodyParameter("person", person);
		params.addBodyParameter("msg", msg);
		httpDataUtils.sendPost(MyData.MSG_ADD, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals(Constans.SUCCESS_CODE)) {
						et_sendmessage.setText("");
						refreshMsg();
					} else {
						ToastUtils.getUtils(MessageDetailChatActivity.this)
								.show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** 华债消息刷新 */
	private void refreshMsg() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization",
				PrefsUtils.getString(this, PrefsUtils.ACCESSKEY));
		params.addQueryStringParameter("msg_type", "cooperation");
		params.addQueryStringParameter("person", entity.getUserid());
		httpDataUtils.sendGet(MyData.GET_MSG_LIST, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals(Constans.SUCCESS_CODE)) {
						JSONArray data = JSONTool.getJsonArray(object, "data");
						Type type = new TypeToken<ArrayList<NewmsgBean>>() {
						}.getType();
						msgList.clear();
						msgList.addAll((ArrayList<NewmsgBean>) gson.fromJson(
								data.toString(), type));
						entity.setNewmsg(msgList);
						messageChatAdapter.notifyDataSetChanged();
						lv_message.setSelection(lv_message.getCount() - 1);

						msgUpdata(entity.getUserid());
					} else {
						ToastUtils.getUtils(MessageDetailChatActivity.this)
								.show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// private String getDate() {
	// Calendar c = Calendar.getInstance();
	// String year = String.valueOf(c.get(Calendar.YEAR));
	// String month = String.valueOf(c.get(Calendar.MONTH) + 1);
	// String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
	// String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
	// String mins = String.valueOf(c.get(Calendar.MINUTE));
	//
	// StringBuffer sbBuffer = new StringBuffer();
	// sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" +
	// mins);
	// return sbBuffer.toString();
	// }
}
