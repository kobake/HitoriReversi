package com.example.reversi;

import java.util.ArrayList;
import java.util.Timer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoardFragment extends Fragment {
	private Handler mHandler = new Handler();
	private Button[] mButtons;

	public BoardFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_board, container,
				false);
		return rootView;
	}
	
	private Button[] findButtons(ViewGroup group){
		ArrayList<Button> buttons = new ArrayList<Button>();
		_findButtons(group, buttons);
		return buttons.toArray(new Button[0]);
	}
	private void _findButtons(ViewGroup group, ArrayList<Button> buttons){
		for(int i = 0; i < group.getChildCount(); i++){
			View v = group.getChildAt(i);
			if(v instanceof Button){
				buttons.add((Button)v);				
			}
			else if(v instanceof ViewGroup){
				_findButtons((ViewGroup)v, buttons);
			}
		}
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// widgets
		mButtons = findButtons((ViewGroup)getView());
		// サイズ調整
		this.getView().post(new Runnable(){
			@Override
			public void run() {
				// 盤面を正方形にする
				View v = BoardFragment.this.getView();
				Log.i("test", "post w = " + v.getWidth());
				LayoutParams params = v.getLayoutParams();
				params.height = v.getWidth();
				v.setLayoutParams(params);
				// フォントサイズ調整
				for(Button button : mButtons){
					button.setTextSize(v.getWidth() / 4 / 2 * 0.8f);
				}
				
			}
		});
	}
	
}
