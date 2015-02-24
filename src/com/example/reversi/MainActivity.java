package com.example.reversi;

import com.example.reversi.logic.ChipTable;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	private BoardFragment mFragment;
	private ChipTable mTable = new ChipTable();
	private int mCurrentValue = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new BoardFragment()).commit();
		}
		*/
		mTable.clear();
		mFragment = (BoardFragment)getFragmentManager().findFragmentById(R.id.fragment1);

		// 見た目作成
		mFragment.initButtons(new BoardClickListener() {
			@Override
			public void onClick(int x, int y) {
				if(mTable.onClick(x, y, mCurrentValue)){
					mCurrentValue *= -1;
				}
				refresh();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// サイズ調整
		mFragment.getView().post(new Runnable(){
			@Override
			public void run() {
				refresh();
			}
		});
	}
	
	public void buttonMethodReset(View v){
		Log.i("test", "clear");
		mCurrentValue = 1;
		mTable.clear();
		refresh();
	}
	
	public void refresh(){
		mFragment.resizeButtons(mTable);
		TextView text = (TextView)findViewById(R.id.textView1);
		String mark = "";
		if(mCurrentValue == 1)mark = "●";
		else mark = "○";
		text.setText("YOUR " + mark + " TURN");
	}

}
