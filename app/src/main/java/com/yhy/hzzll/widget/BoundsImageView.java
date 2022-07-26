package com.yhy.hzzll.widget;

import com.yhy.hzzll.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BoundsImageView extends ImageView {
	private int co;
	private int borderwidth;
	Paint paint;

	public BoundsImageView(Context context) {
		super(context);
		initRes();
	}

	public BoundsImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initRes();
	}

	public BoundsImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initRes();
	}

	// 设置颜色
	public void setColour(int color) {
		co = color;
	}

	// 设置边框宽度
	public void setBorderWidth(int width) {

		borderwidth = width;
	}

	private void initRes() {
		paint = new Paint();
		// 设置边框颜色
		// paint.setColor(co);
		paint.setColor(getResources().getColor(R.color.discoupon_txt));
		paint.setStyle(Paint.Style.STROKE);
		// 设置边框宽度
		paint.setStrokeWidth(4);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画边框
		Rect rec = canvas.getClipBounds();
//		rec.bottom--;
//		rec.right--;
		canvas.drawRect(rec, paint);
	}
}
