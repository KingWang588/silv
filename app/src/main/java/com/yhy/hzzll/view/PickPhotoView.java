package com.yhy.hzzll.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

public class PickPhotoView extends LinearLayout {

	private final int SIZE = 6;

	private int groupwidth;
	private int groupheight;

	private Context context;
	BitmapUtils bitmapUtils;
	private BitmapDisplayConfig bigPicDisplayConfig = new BitmapDisplayConfig();

	public PickPhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public PickPhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		bitmapUtils = new BitmapUtils(context);
		bigPicDisplayConfig.setAutoRotation(true);

	}

	public PickPhotoView(Context context) {
		super(context);
	}

	public int PXtoDP(int px) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
				context.getResources().getDisplayMetrics());
	}

	List<String> list = new ArrayList<String>();

	public void addViewed(List<String> fileList) {
		if (null == fileList) {
			return;
		}
		removeAllViews();
		list.clear();
		list.addAll(fileList);
		for (int i = 0; i <= list.size(); i++) {
			ImageView imageView = new ImageView(context);
			if (i == list.size()) {
				imageView.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), com.yhy.hzzll.R.drawable.album_add));
			} else {
				bitmapUtils
						.display(imageView, list.get(i), bigPicDisplayConfig);
			}
			imageView.setTag(i);
			imageView.setPadding(5, 5, 5, 5);
			imageView.setAdjustViewBounds(true);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (null != clickedLisener) {
						if ((Integer) v.getTag() == list.size()) {
							if (list.size() <= SIZE) {
								clickedLisener.onAddClick((Integer) v.getTag(),
										v);
							}
						} else {
							clickedLisener.onItemClick((Integer) v.getTag(), v);
						}
					}
				}
			});

			addView(imageView);
		}

	}

	private OnItemClickedLisener clickedLisener;

	public interface OnItemClickedLisener {
		void onItemClick(int position, View view);

		void onAddClick(int position, View view);

	}

	public void setOnItemClickedLisener(OnItemClickedLisener clickedLisener) {
		this.clickedLisener = clickedLisener;
	}

	@Override
	public void setOrientation(int orientation) {
		if (orientation != HORIZONTAL) {
			throw new IllegalArgumentException(String.format(
					"orientation= %s unsupported.", orientation));
		}
		super.setOrientation(orientation);
	}

	@Override
	public int getOrientation() {
		return HORIZONTAL;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		groupwidth = MeasureSpec.getSize(widthMeasureSpec); // 获取ViewGroup宽度
		groupheight = MeasureSpec.getSize(heightMeasureSpec); // 获取ViewGroup高度
		setMeasuredDimension(groupwidth, groupwidth / SIZE); // 设置ViewGroup的宽高
		int childCount = getChildCount(); // 获得子View的个数，下面遍历这些子View设置宽高
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			child.measure(groupwidth / SIZE, groupwidth / SIZE); // 设置子View宽高
		}

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		int mTotalWidth = 0;
		// 当然，也是遍历子View，每个都要告诉ViewGroup
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			// 获取在onMeasure中计算的视图尺寸
			childView.layout(mTotalWidth, 0, ((groupwidth - 18) / SIZE)
					+ mTotalWidth, ((groupwidth - 20) / SIZE));
			mTotalWidth += (groupwidth / SIZE);

		}
	}

}
