package com.yhy.hzzll.mian.activity;

import androidx.fragment.app.FragmentActivity;

/**
 * 注册
 *
 * @author wangyang
 */
public class RegisterActivity extends FragmentActivity {
//
//    @ViewInject(R.id.register_viewpager)
//    private MyViewPager register_viewpager;
//
//    private FragmentAdapter adapter;
//
//    /**
//     * 碎片集合
//     */
//    private List<Fragment> list;
//
//    // private RegsterFragmentType regsterFragmentType;
//    private RegsterFragmentInfo regsterFragmentInfo;
//    private RegisterFragmentAuth registerFragmentAuth;
//    private RegisterFragmentFile registerFragmentFile;
//    private RegsterFragmentSucess regsterFragmentSucess;
//
//    @ViewInject(R.id.tv_login_or_home)
//    private TextView tv_login_or_home;
//
//    @ViewInject(R.id.ic_back)
//    private TextView ic_back;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        setContentView(R.layout.activity_register);
//        super.onCreate(savedInstanceState);
//        // 打开一个新activity 就压入集合 存储起来
//        MyActivityManager.getInstance().pushOneActivity(this);
//        ViewUtils.inject(this);
//        viewInit();
//
//    }
//
//    @OnClick({R.id.tv_login_or_home, R.id.ic_back})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_login_or_home:
//                if (tv_login_or_home.getText().toString().equals("首页")) {
//
//                    startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));
//                    finish();
//
//                } else {
//                    startActivity(new Intent().setClass(this, LoginActivity.class));
//                    finish();
//                }
//                break;
//            case R.id.ic_back:
//                if (register_viewpager.getCurrentItem() == 1) {
//                    register_viewpager.setCurrentItem(0);
//                } else {
//                    startActivity(new Intent(this, LoginActivity.class));
//                    finish();
//                }
//                break;
//        }
//    }
//
//    public void viewInit() {
//
//        register_viewpager = (MyViewPager) findViewById(R.id.register_viewpager);
//        list = new ArrayList<Fragment>();
//        regsterFragmentInfo = new RegsterFragmentInfo();
//        regsterFragmentInfo.setHandler(handler);
//        registerFragmentAuth = new RegisterFragmentAuth();
//        registerFragmentAuth.setHandler(handler);
//        registerFragmentFile = new RegisterFragmentFile();
//        registerFragmentFile.setHandler(handler);
//        regsterFragmentSucess = new RegsterFragmentSucess();
//
//        list.add(regsterFragmentInfo);
//        list.add(registerFragmentAuth);
//        list.add(registerFragmentFile);
//        list.add(regsterFragmentSucess);
//
//
//        adapter = new FragmentAdapter(getSupportFragmentManager(), list);
//        register_viewpager.setAdapter(adapter);
//        register_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int arg0) {
//
//            }
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });
//
//
//        int tab = getIntent().getIntExtra("tab", 0);
//
//        if (tab==0){
//
//        }else if (tab == 2){
//            handler.sendEmptyMessage(1);
//        }else if (tab == 3){
//            handler.sendEmptyMessage(2);
//        }
//
//    }
//
//
//
//    private Handler handler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    tv_login_or_home.setVisibility(View.GONE);
//                    register_viewpager.setCurrentItem(1, true);
//                    break;
//                case 2:
//                    tv_login_or_home.setVisibility(View.GONE);
//                    register_viewpager.setCurrentItem(2, true);
//                    break;
//                case 3:
//                    ic_back.setVisibility(View.GONE);
//                    register_viewpager.setCurrentItem(3, true);
//                    tv_login_or_home.setText("首页");
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    };
//
//    @Override
//    public void onBackPressed() {
//        if (register_viewpager.getCurrentItem() == 1) {
//            register_viewpager.setCurrentItem(0);
//        } else {
//            finish();
//        }
//        return;
//    }





}
