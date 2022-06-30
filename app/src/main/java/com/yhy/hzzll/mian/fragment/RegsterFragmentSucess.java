package com.yhy.hzzll.mian.fragment;

/**
 * 注册--注册成功
 * 
 * @author wangyang
 * 
 */
public class RegsterFragmentSucess extends BaseFragment {

//	private HttpDataUtils httpDataUtils;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		httpDataUtils = new HttpDataUtils(getActivity());
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater,
//			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_register_sucess, null);
//		ViewUtils.inject(this, view);
//		logininfo();
//		return view;
//	}
//
//	@OnClick({ R.id.tv_complete_quote })
//	public void onclick(View view) {
//		switch (view.getId()) {
//		case R.id.tv_complete_quote:
//			Intent intent = new Intent();
//			intent.setClass(getActivity(), ServiceAndQuoteActivity.class)
//					.putExtra("office","0")
//					.putExtra("price","");
//			startActivityForResult(intent,1216);
//			break;
//		}
//	}
//
//	/**
//	 * 获取登录信息
//	 */
//	private void logininfo() {
//
//		RequestParams params = new RequestParams();
//		params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
//		httpDataUtils.sendGet(MyData.CHECK_OUT_USER_INFO, params);
//		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//			@Override
//			public void sucess(ResponseInfo<String> arg0) {
//				try {
//					JSONObject object = new JSONObject(arg0.result);
//					String code = object.optString("code");
//					String msg = object.optString("msg");
//					if (code.equals("0")) {
//						Gson gson = new Gson();
//						try {
//							CacheUtils.dataUserEntity = gson.fromJson(arg0.result, DataUserEntity.class);
//							HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity);
//							DataUserEntity userEntity = gson.fromJson(arg0.result, DataUserEntity.class);
//
//							PrefsUtils.saveString(getActivity(), PrefsUtils.ACCESSKEY, userEntity.getData().getInfo().getOld_user_info().getHash());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id()+"");
//
//							PrefsUtils.saveString(getActivity(), PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());
//
//							PrefsUtils.saveString(getActivity(), PrefsUtils.UID, userEntity.getData().getInfo().getId()+"");
//							PrefsUtils.saveString(getActivity(), PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());
//							PrefsUtils.saveString(getActivity(), PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());
//
//
//						} catch (Exception e) {
//
//
//						}
//					} else {
//						// ToastUtils.getUtils(getActivity()).show(msg);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
//
//			@Override
//			public void fail(String msg) {
//			}
//		});
//	}

}
