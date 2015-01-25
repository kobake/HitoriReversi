package com.example.reversi;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

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
	}
	
	public void panelMethod(View v){
		Button button = (Button)v;
		// 自分自身を反転
		if(button.getText().toString().equals("●")){
			button.setText("○");
		}
		else{
			button.setText("●");
		}
	}

}
