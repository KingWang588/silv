package com.yhy.hzzll.my.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.fragment.BaseFragment;

/**
 * 我的关注 -- 文章
 * 
 * @author Yang
 * 
 */
public class FocusNewsFragment extends BaseFragment {

	private View view;
	@ViewInject(R.id.lv_focus)
	private PullToRefreshListView lv_focus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_focus_list, null);
		return view;
	}
}
