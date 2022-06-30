package com.yhy.hzzll.message;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.PhotoAlbumAdapter;
import com.yhy.hzzll.adapter.PhotoAlbumSingleAdapter;
import com.yhy.hzzll.entity.PhotoModel;
//import com.yhy.hzzll.home.activity.evidence.EvidenceFileChangeActivity;
import com.yhy.hzzll.home.activity.collaborate.PublishCollaborationActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.my.activity.PersonDataLawyerActivity;
//import com.yhy.hzzll.office.activity.AddCaseProgressingActivity;
import com.yhy.hzzll.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 打开相册选择图片
 * 
 * @author WangYang
 * 
 */
public class PhotoAlbumActivity extends BaseActivity implements OnClickListener {

	public static final String PHOTOLIST = "photolist";

	private GridView mGridView;
	private PhotoAlbumAdapter adapter;
	private PhotoAlbumSingleAdapter adapter2;

	private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
	private List<PhotoModel> list = new ArrayList<PhotoModel>();
	private final static int SCAN_OK = 1;
	private ProgressDialog mProgressDialog;

	/** 返回路径的code */
	private int code = 0;

	/** 单选or多选 0 - 单选 1- 多选 */
	private int single = 1;

	private List<String> childList;// 存储图片文件夹的临时集合

	private List<String> allDataList = new ArrayList<String>();// 存储所有图片的集合

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SCAN_OK:
				// 关闭进度条
				mProgressDialog.dismiss();
				list = subGroupOfImage(mGruopMap);
				for (int i = 0; i < mGruopMap.size(); i++) {
					childList = mGruopMap.get(list.get(i).getFolderName());
					for (int j = 0; j < childList.size(); j++) {
						if (!allDataList.contains(childList.get(j))) {
							allDataList.add(childList.get(j));
						}
					}
				}
				if (single == 0) {
					adapter2 = new PhotoAlbumSingleAdapter(
							PhotoAlbumActivity.this, allDataList);
					mGridView.setAdapter(adapter2);
				} else {
					adapter = new PhotoAlbumAdapter(PhotoAlbumActivity.this,
							allDataList);
					mGridView.setAdapter(adapter);
				}
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_photo_album);
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		viewInit();
		if (null != getIntent()) {
			code = getIntent().getIntExtra("code", 0);
			single = getIntent().getIntExtra("single", 0);
		}
	}

	private void viewInit() {
		mGridView = (GridView) findViewById(R.id.child_grid);
		getImages();
	}

	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
	 */
	private void getImages() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			ToastUtils.getUtils(getApplicationContext()).show("暂无外部存储");
			return;
		}
		// 显示进度条
		mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

		new Thread(new Runnable() {
			@Override
			public void run() {
				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = PhotoAlbumActivity.this
						.getContentResolver();

				// 只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				while (mCursor.moveToNext()) {
					// 获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					// 获取该图片的父路径名
					String parentName = new File(path).getParentFile()
							.getName();

					// 根据父路径名将图片放入到mGruopMap中
					if (!mGruopMap.containsKey(parentName)) {
						List<String> chileList = new ArrayList<String>();
						chileList.add(path);
						mGruopMap.put(parentName, chileList);
					} else {
						mGruopMap.get(parentName).add(path);
					}
				}
				mCursor.close();
				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(SCAN_OK);
			}
		}).start();
	}

	/**
	 * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中 所以需要遍历HashMap将数据组装成List
	 * 
	 * @param mGruopMap
	 * @return
	 */
	private List<PhotoModel> subGroupOfImage(
			HashMap<String, List<String>> mGruopMap) {
		if (mGruopMap.size() == 0) {
			return null;
		}
		List<PhotoModel> list = new ArrayList<PhotoModel>();

		Iterator<Map.Entry<String, List<String>>> it = mGruopMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<String>> entry = it.next();
			PhotoModel mImageBean = new PhotoModel();
			String key = entry.getKey();
			List<String> value = entry.getValue();
			mImageBean.setFolderName(key);
			mImageBean.setImageCounts(value.size());
			mImageBean.setTopImagePath(value.get(0));// 获取该组的第一张图片
			list.add(mImageBean);
		}

		return list;

	}

	@OnClick({ R.id.tv_add })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_add:
			switch (code) {

			case PersonDataLawyerActivity.PHOTOALBUMCODE:
				PersonDataLawyerActivity.photolist.clear();
				PersonDataLawyerActivity.photolist.addAll(adapter2
						.getSelectItems());
				if (PersonDataLawyerActivity.photolist.size() <= 1) {
					setResult(902);
					finish();
				} else {
					ToastUtils.getUtils(getApplicationContext()).show(
							"最多上传1张照片");
				}
				break;

//			case EvidenceFileChangeActivity.PHOTOALBUMCODE:
//				EvidenceFileChangeActivity.photolist.clear();
//				EvidenceFileChangeActivity.photolist.addAll(adapter.getSelectItems());
//				EvidenceFileChangeActivity.photolist = adapter.getSelectItems();
//				if (EvidenceFileChangeActivity.photolist.size() <= 5) {
//					setResult(109);
//					finish();
//				} else {
//					ToastUtils.getUtils(getApplicationContext()).show(
//							"最多上传5张照片");
//				}
//
//				break;

			case PublishCollaborationActivity.CARD_PHOTO_ALBUM_CODE:
				PublishCollaborationActivity.photolist.clear();
				PublishCollaborationActivity.photolist.addAll(adapter
						.getSelectItems());
				if (PublishCollaborationActivity.photolist.size() <= 5) {
					setResult(PublishCollaborationActivity.CARD_PHOTO_ALBUM_CODE);
					finish();
				} else {
					ToastUtils.getUtils(getApplicationContext()).show(
							"最多上传5张照片");
				}

				break;

//			case AddCaseProgressingActivity.FEEDBACK_CODE:
//				AddCaseProgressingActivity.photo.clear();
//				AddCaseProgressingActivity.photo.addAll(adapter
//						.getSelectItems());
//				if (AddCaseProgressingActivity.photo.size() <= 5) {
//					setResult(AddCaseProgressingActivity.FEEDBACK_CODE);
//					finish();
//				} else {
//					ToastUtils.getUtils(getApplicationContext()).show(
//							"最多上传5张照片");
//				}
//				break;

			case PersonDataLawyerActivity.FEEDBACK_PHOTO_ALBUM_CODE:
				PersonDataLawyerActivity.photolist.clear();
				PersonDataLawyerActivity.photolist.addAll(adapter
						.getSelectItems());
				if (PersonDataLawyerActivity.photolist.size() <= 5) {
					setResult(PersonDataLawyerActivity.PHOTOALBUMCODE);
					finish();
				} else {
					ToastUtils.getUtils(getApplicationContext()).show(
							"最多上传1张照片");
				}
				break;
			}
		}
	}
}
