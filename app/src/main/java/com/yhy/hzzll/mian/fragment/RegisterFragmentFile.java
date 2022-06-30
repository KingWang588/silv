package com.yhy.hzzll.mian.fragment;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragmentFile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragmentFile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragmentFile extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public RegisterFragmentFile() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RegisterFragmentFile.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RegisterFragmentFile newInstance(String param1, String param2) {
//        RegisterFragmentFile fragment = new RegisterFragmentFile();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//        dataUtils = new HttpDataUtils(getActivity());
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_register_fragment_file, container, false);
//        ViewUtils.inject(this, view);
//
//        init2();
//
//        return view;
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//
//    public static List<String> photolist = new ArrayList<>();
//    // 请求截图
//    private static final int REQUEST_CROP_PHOTO = 102;
//    private static final int REQUEST_CODE_SETTING = 300;
//
//    @ViewInject(R.id.iv_headimg)
//    private ImageView iv_headimg;
//
//    @ViewInject(R.id.gridview)
//    GridView gridview;
//    private GridViewAdapter gridViewAdapter;
//
//    List<PhotoEntity> photoEntities = new ArrayList<>();
//    ArrayList<String> imgList = new ArrayList<String>();
//    private HttpDataUtils dataUtils;
//
//    String cropImagePath;
//
//    private Handler handler;
//
//    public void setHandler(Handler handler) {
//        this.handler = handler;
//    }
//
//
//    @OnClick({R.id.tv_next, R.id.iv_headimg})
//    public void onclick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_next:
//
//
//                if (cropImagePath == null) {
//                    ToastUtils.getUtils(getActivity()).show("请设置头像");
//                    return;
//                }
//
//                if (imgList.size() == 0) {
//                    ToastUtils.getUtils(getActivity()).show("请上传执业证附件");
//                    return;
//                }
//
//                uploadAuthFile();
//
//                break;
//
//            case R.id.iv_headimg:
//
//                AndPermission.with(getActivity())
//                        .requestCode(101)
//                        .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .callback(permissionListener)
//                        .start();
//
//                break;
//        }
//    }
//
//    private void uploadAuthFile() {
//        RequestParams params = new RequestParams();
//
//        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
//
//        params.addBodyParameter("avatar", new File(cropImagePath));
//
//        for (int i = 0; i < imgList.size(); i++) {
//            params.addBodyParameter("lawyer_license_photo[]", new File(imgList.get(i)));
//        }
//
////        File file = new File(upFileName);
////        List<KeyValue> list = new ArrayList<KeyValue>();
////        list.add(new KeyValue("file",file));
////        MultipartBody body=new MultipartBody(list,"UTF-8");
////        params.setRequestBody(body);
////        params.setMultipart(true);
//
////        params.
//
//        dataUtils.sendProgressPost(MyData.REALNAME, params);
//        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//            @Override
//            public void sucess(ResponseInfo<String> arg0) {
//
//                if (dataUtils.code(arg0.result)) {
//                    handler.sendEmptyMessage(3);
//                } else {
//                    dataUtils.showMsg(arg0.result);
//                }
//            }
//        });
//    }
//
//
//    private void init2() {
//
//        gridViewAdapter = new GridViewAdapter(getActivity(), photoEntities, delete);
//        gridview.setAdapter(gridViewAdapter);
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == parent.getChildCount() - 1) {
//
//                    AndPermission.with(getActivity())
//                            .requestCode(102)
//                            .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            .callback(permissionListener)
//                            .start();
//
//                } else {
//                    ArrayList<String> imgList2 = new ArrayList<String>();
//
//                    for (int i = 0; i < photoEntities.size(); i++) {
//                        imgList2.add(photoEntities.get(i).getImgpath());
//                    }
//
//                    Intent intent = new Intent(getActivity(), ViewPagerExampleActivity.class);
//                    intent.putStringArrayListExtra(ViewPagerExampleActivity.PHOTO_URL_LIST_INTENT, imgList2).putExtra("position", position);
//                    startActivity(intent);
//                }
//            }
//        });
//
//    }
//
//    private View.OnClickListener delete = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            final int position = (int) v.getTag();
//
//            String url = photoEntities.get(position).getImgpath();
//
//            if (url.contains("http")) {
//
////                showDiolog(position);
//
//            } else {
//                photoEntities.remove(position);
////                gridViewAdapter.notifyDataSetChanged();
//                gridViewAdapter = new GridViewAdapter(getActivity(), photoEntities, delete);
//                gridview.setAdapter(gridViewAdapter);
//
//            }
//        }
//    };
//
//
//    /**
//     * 回调监听。
//     */
//    private PermissionListener permissionListener = new PermissionListener() {
//        @Override
//        public void onSucceed(int requestCode, List<String> grantPermissions) {
//            switch (requestCode) {
//                case 101: {
////					Toast.makeText(EvidenceSavaActivity.this,"获取权限成功", Toast.LENGTH_SHORT).show();
//
//                    if (AndPermission.hasPermission(getActivity(), Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                        pickImage();
//                    } else {
//                        //专为小米 设置的
//                        Toast.makeText(getActivity(), "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
////                        AndPermission.defaultSettingDialog(EvidenceSavaActivity.this,101);
//                    }
//
//                    break;
//                }
//                case 102: {
//                    if (AndPermission.hasPermission(getActivity(), Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                        pickImage2();
//                    } else {
//                        //专为小米 设置的
//                        Toast.makeText(getActivity(), "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
////                        AndPermission.defaultSettingDialog(EvidenceSavaActivity.this,101);
//                    }
//                    break;
//                }
//            }
//        }
//
//        @Override
//        public void onFailed(int requestCode, List<String> deniedPermissions) {
//            switch (requestCode) {
//                case 101: {
//                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                case 102: {
//                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//            }
//
//            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
//            if (AndPermission.hasAlwaysDeniedPermission(getActivity(), deniedPermissions)) {
//                // 第一种：用默认的提示语。
//                AndPermission.defaultSettingDialog(getActivity(), REQUEST_CODE_SETTING).show();
//
//            }
//        }
//    };
//
//    private void pickImage2() {
//        MultiImageSelector selector = MultiImageSelector.create();
//        selector.count(5);
//        selector.showCamera(true);
//        selector.multi();
//        selector.origin(imgList);
//        selector.start(RegisterFragmentFile.this, 2);
//    }
//
//
//    private void pickImage() {
//        MultiImageSelector selector = MultiImageSelector.create();
//        selector.showCamera(true);
//        selector.single();
//        selector.start(RegisterFragmentFile.this, 1);
//    }
//
//
//    public void gotoClipActivity(Uri uri) {
//        if (uri == null) {
//            return;
//        }
//        Intent intent = new Intent();
//        intent.setClass(getActivity(), ClipImageActivity.class);
//        intent.putExtra("type", 2);
//        intent.setData(uri);
//        startActivityForResult(intent, REQUEST_CROP_PHOTO);
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == 1) {
//            if (data != null) {
//                photolist.clear();
//                photolist.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));
//                Uri uri = Uri.fromFile(new File(photolist.get(0)));
//                gotoClipActivity(uri);
//            }
//        } else if (requestCode == REQUEST_CROP_PHOTO) {
//
//            final Uri uri = data.getData();
//            if (uri == null) {
//                return;
//            }
//            cropImagePath = getRealFilePathFromUri(getActivity(), uri);
//
//            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//            iv_headimg.setImageBitmap(bitMap);
//        } else if (requestCode == 2) {
//            if (data != null) {
//                imgList.clear();
//                imgList = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
//                photoEntities.clear();
//
//                for (int i = 0; i < imgList.size(); i++) {
//                    String imgPath = imgList.get(i);
//                    PhotoEntity photoEntity = new PhotoEntity("", imgPath);
//                    photoEntities.add(photoEntity);
//                }
//                gridViewAdapter = new GridViewAdapter(getActivity(), photoEntities, delete);
//                gridview.setAdapter(gridViewAdapter);
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    /**
//     * Try to return the absolute file path from the given Uri 兼容了file:///开头的 和
//     * content://开头的情况
//     *
//     * @param context
//     * @param uri
//     * @return the file path or null
//     */
//    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
//        if (null == uri)
//            return null;
//        final String scheme = uri.getScheme();
//        String data = null;
//        if (scheme == null)
//            data = uri.getPath();
//        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
//            data = uri.getPath();
//        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
//            Cursor cursor = context.getContentResolver().query(uri,
//                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
//            if (null != cursor) {
//                if (cursor.moveToFirst()) {
//                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//                    if (index > -1) {
//                        data = cursor.getString(index);
//                    }
//                }
//                cursor.close();
//            }
//        }
//        return data;
//    }

}
