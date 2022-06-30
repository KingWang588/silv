package com.yhy.hzzll.view;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEdittext extends EditText {
	private static final int ID_PASTE = android.R.id.paste;

	public CustomEdittext(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomEdittext(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTextContextMenuItem(int id) {
		if (id == ID_PASTE) {
			ClipboardManager clip = (ClipboardManager) getContext()
					.getSystemService(Context.CLIPBOARD_SERVICE);
			clip.setText(clip.getText());
		}
		return super.onTextContextMenuItem(id);
	}

}
