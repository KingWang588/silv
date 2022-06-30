package com.yhy.hzzll.message;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MessageDetailHuaZhaiAdapter;
import com.yhy.hzzll.entity.MessageOfSystemEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.activity.consult.PursueAskActivity;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.my.activity.QuickConsultingOrderDetailsActivity;
import com.yhy.hzzll.my.activity.TreasureDetailsActivity;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessageDetailHuaZhaiActivity extends BaseActivity {

	private View view;
	@ViewInject(R.id.lv_message)
	private PullToRefreshListView lv_message;


	private MessageDetailHuaZhaiAdapter messageDetailAdapter;
	/** 数据分页 */
	private int index = 1;
	/**
	 * 标记是刷新还是加载更多
	 */
	private boolean isPullRefresh;


	private List<MessageOfSystemEntity.DataBean.ListBean> msgList;


	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_message_detail_hua_zhai);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		viewInit();
	}

	public void viewInit() {
		msgList = new ArrayList<>();
		messageDetailAdapter = new MessageDetailHuaZhaiAdapter(this, msgList);
		lv_message.setAdapter(messageDetailAdapter);
		lv_message.setMode(Mode.BOTH);
		lv_message.setOnRefreshListener(new ListViewOnRefreshListener());
		lv_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String title = msgList.get(position-1).getTitle();

				if (title.equals("点赞")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, ConsultDetailsActivity.class).putExtra("id", msgList.get(position-1).getParams().getParams_id() + "")
							.putExtra("title","").putExtra("insert",false));
				}else if (title.equals("回复追问")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, PursueAskActivity.class)
							.putExtra("reply_id", msgList.get(position-1).getParams().getReply_id() + "").putExtra("id", msgList.get(position-1).getParams().getParams_id() + "").putExtra("message_id",msgList.get(position-1).getParams().getMessage_id()+""));
				}else if (title.equals("专属咨询评价")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, QuickConsultingOrderDetailsActivity.class).putExtra("order_no", msgList.get(position-1).getParams().getOrder_no()+""));
				}else if (title.equals("快速咨询打赏")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, ConsultDetailsActivity.class).putExtra("id", msgList.get(position-1).getParams().getParams_id() + "")
							.putExtra("title","").putExtra("insert",false));
				}else if(title.equals("追问红包")){
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();

					c.setTime(new Date());
					c.add(Calendar.MONTH, -1);
					Date m = c.getTime();
					String mon = format.format(m);

					Date d = new Date();
					String dateNowStr = format.format(d);

					Intent intent = new Intent().setClass(MessageDetailHuaZhaiActivity.this, TreasureDetailsActivity.class);
					intent.putExtra(TreasureDetailsActivity.START_DATE_INTENT, mon);
					intent.putExtra(TreasureDetailsActivity.END_DATE_INTENT, dateNowStr);
					startActivity(intent);

				}else if(title.equals("采纳")){

					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, ConsultDetailsActivity.class).putExtra("id", msgList.get(position-1).getParams().getParams_id() + "")
							.putExtra("title","").putExtra("insert",false));

				}else if(title.equals("专属咨询")){

					Intent intent = new Intent();
					intent.putExtra(Extras.EXTRA_ACCOUNT, msgList.get(position-1).getParams().getAccid_id()+"");
					intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
					intent.setClass(MessageDetailHuaZhaiActivity.this, MyNewMessageActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(intent);

				}else if(title.equals("快速咨询评价")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, ConsultDetailsActivity.class).putExtra("id", msgList.get(position-1).getParams().getParams_id() + "")
							.putExtra("title","").putExtra("insert",false));
				}else if(title.equals("专属咨询打赏")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, ConsultDetailsActivity.class).putExtra("id", msgList.get(position-1).getParams().getParams_id() + "")
							.putExtra("title","").putExtra("insert",false));
				}else if(title.equals("快速咨询")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this, ConsultDetailsActivity.class).putExtra("id", msgList.get(position-1).getParams().getParams_id() + "")
							.putExtra("title","").putExtra("insert",false));
				}else if (title.equals("债权保")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this,PickUpCaseActivity.class)
							.putExtra("order_no",msgList.get(position-1).getParams().getOrder_no()));
				}else if (title.equals("婚姻保")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this,PickUpCaseActivity.class)
							.putExtra("order_no",msgList.get(position-1).getParams().getOrder_no()));
				}else if (title.equals("聘请律师")){
					startActivity(new Intent(MessageDetailHuaZhaiActivity.this,PickUpCaseActivity.class)
							.putExtra("order_no",msgList.get(position-1).getParams().getOrder_no()));
				}

			}
		});
		publishViewInit();
	}

	/** 进页面自动刷新 */
	private void publishViewInit() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				lv_message.setRefreshing(true);
			}
		}, 500);

//		refreshMsg();
	}

	private void resetPageIndex() {
		index = 1;
	}

	private void addPageIndex() {
		index++;
	}

	class ListViewOnRefreshListener implements OnRefreshListener2<ListView> {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			resetPageIndex();
			isPullRefresh = true;
			refreshMsg();

		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			addPageIndex();
			isPullRefresh = false;
			refreshMsg();

		}
	}

	/** 华债消息刷新 */
	private void refreshMsg() {
		RequestParams params = new RequestParams();
		String url = MyData.MESSAGE_SYSTEM+index+"-30";
		params.addHeader("Authorization", PrefsUtils.getString(this, PrefsUtils.AUTHORIZATION));
		httpDataUtils.sendGet(url, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				lv_message.onRefreshComplete();
				if (isPullRefresh) {
					msgList.clear();
				}
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("message");
					if (code.equals(Constans.SUCCESS_CODE)) {

						MessageOfSystemEntity messageOfSystemEntity = gson.fromJson(arg0.result,MessageOfSystemEntity.class);

						msgList.addAll(messageOfSystemEntity.getData().getList());

						messageDetailAdapter.notifyDataSetChanged();

					} else {
						ToastUtils.getUtils(MessageDetailHuaZhaiActivity.this).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		httpDataUtils.setFailBack(new FailBack() {

			@Override
			public void fail(String msg) {
				lv_message.onRefreshComplete();
			}
		});
	}

	@OnClick({  R.id.ic_back })
	public void Onclick(View view) {
		switch (view.getId()) {
		case R.id.ic_back:
			finish();
			break;
		}
	}

}
