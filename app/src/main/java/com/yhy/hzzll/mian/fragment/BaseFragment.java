package com.yhy.hzzll.mian.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

public class BaseFragment extends Fragment {

	public Gson gson;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		gson = new Gson();
		init();
	}

	public void init() {
	}

	public void viewInit() {
	}

	public void dataInit() {

	}
}
