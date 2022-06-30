package com.yhy.hzzll.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class CustomListview extends ListView {

	public CustomListview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomListview(Context context) {
		super(context);
	}

	public CustomListview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}


	// @Override
	// public boolean dispatchTouchEvent(MotionEvent ev) {
	// // TODO Auto-generated method stub
	// switch (ev.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// getParent().requestDisallowInterceptTouchEvent(false);
	// break;
	// case MotionEvent.ACTION_MOVE:
	// getParent().requestDisallowInterceptTouchEvent(false);
	// break;
	// }
	// return super.dispatchTouchEvent(ev);
	// }

}
