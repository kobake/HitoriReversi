package com.mysamp.reversi;

import java.util.ArrayList;
import java.util.Timer;

import com.mysamp.reversi.R;
import com.mysamp.reversi.logic.Chip;
import com.mysamp.reversi.logic.ChipTable;

import android.R.integer;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.Button;

interface BoardClickListener{
	public void onClick(int x, int y);
}

public class BoardFragment extends Fragment {
	private Button[] mButtons = new Button[4 * 4];

	public BoardFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_board, container,
				false);
		return rootView;
	}
	
	// 見た目をロジックに合わせる
	public Button getButton(int x, int y){
		if(x >= 0 && x < 4 && y >= 0 && y < 4){
			return mButtons[y * 4 + x];
		}
		else{
			return null;
		}
	}
	
	public void initButtons(final BoardClickListener listener){
		for(int y = 0; y < 4; y++){
			for(int x = 0 ; x < 4; x++){
				final int X = x;
				final int Y = y; 
				mButtons[y * 4 + x] = new Button(this.getView().getContext());
				mButtons[y * 4 + x].setText(x + "," + y);
				((ViewGroup)this.getView().findViewById(R.id.relative1)).addView(mButtons[y * 4 + x]);
				mButtons[y * 4 + x].setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						listener.onClick(X, Y);
					}
				});
			}
		}
	}
	public void resizeButtons(ChipTable chipTable){
		//Log.i("test", "post w = " + v.getWidth());

		// 盤面を正方形にする
		View v = BoardFragment.this.getView();
		int w = v.getWidth();
		{
			LayoutParams params = v.getLayoutParams();
			params.height = w;
			v.setLayoutParams(params);
		}
		
		// ボタン位置、サイズ、白黒
		int size = w / 4;
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				Button b = getButton(x, y);
				LayoutParams params = b.getLayoutParams();
				params.width = size;
				params.height = size;
				b.setLayoutParams(params);
				b.setX(x * size);
				b.setY(y * size);
				Chip chip = chipTable.at(x, y);
				if(chip.get() == 0){
					b.setText("");
				}
				else if(chip.get() == 1){
					b.setText("●");
				}
				else if(chip.get() == -1){
					b.setText("○");
				}
			}
		}
		
		// フォントサイズ調整
		for(Button button : mButtons){
			button.setTextSize(size * 0.3f);
		}
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
