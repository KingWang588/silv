package com.yhy.hzzll.view;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.yhy.hzzll.R;

/**
 * title 初始化
 * 
 * @author wangayng
 * 
 */
public class TitleInit {

	private Click click;

	public interface Click {
		void clickBack();

		void clickRight();
	}

	public TitleInit() {
	}

	public TitleInit(Activity activity, Click click) {

		this.click = click;
		try {
			activity.findViewById(R.id.ic_back).setOnClickListener(listener);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.ic_back:
				if (click != null)
					click.clickBack();
				break;
			}
		}
	};

}
