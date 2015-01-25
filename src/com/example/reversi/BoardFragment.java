package com.example.reversi;

import java.util.Timer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoardFragment extends Fragment {

	public BoardFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_board, container,
				false);
		return rootView;
	}
	
	Handler mHandler = new Handler();
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// サイズ調整
		this.getView().post(new Runnable(){
			@Override
			public void run() {
				View v = BoardFragment.this.getView();
				Log.i("test", "post w = " + v.getWidth());
				LayoutParams params = v.getLayoutParams();
				params.height = v.getWidth();
				v.setLayoutParams(params);
			}
		});
	}
	
}
