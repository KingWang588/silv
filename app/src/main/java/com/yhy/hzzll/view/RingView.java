package com.yhy.hzzll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RingView extends View {

	private final Paint paint;
	private final Context context;

	public RingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.paint = new Paint();
		this.paint.setAntiAlias(true); // 消除锯齿
		this.paint.setStyle(Paint.Style.STROKE); // 绘制空心圆
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// TODO Auto-generated method stub
		int innerCircle = dip2px(context, 50); // 设置内圆半径
		int ringWidth = dip2px(context, 5); // 设置圆环宽度
		int width = getWidth();
		int height = getHeight();
		int radius = getHeight() / 3;
		int r = radius * 2 / 7;
		canvas.drawCircle(width / 2, height / 2, radius, paint);
		// Bitmap original = BitmapFactory.decodeResource(getResources(),
		// R.drawable.home_wdys);
		// canvas.drawBitmap(original, width / 2 + r, r, paint);
		// 绘制内圆
		this.paint.setARGB(255, 255, 255, 255);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(innerCircle, ringWidth, innerCircle, this.paint);

		this.paint.setARGB(155, 0, 161, 255);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(innerCircle, ringWidth, innerCircle, this.paint);

		// // 绘制圆环
		// this.paint.setARGB(255, 212, 225, 233);
		// this.paint.setStrokeWidth(ringWidth);
		// canvas.drawCircle(innerCircle, ringWidth, innerCircle + 1 + ringWidth
		// / 2, this.paint);
		//
		// // 绘制外圆
		// this.paint.setARGB(155, 167, 190, 206);
		// this.paint.setStrokeWidth(2);
		// canvas.drawCircle(innerCircle, ringWidth, innerCircle + ringWidth,
		// this.paint);

	}

	public void serProgress(int progrees) {

	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
