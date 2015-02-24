package com.mysamp.reversi.logic;

import android.view.ViewGroup;
import android.widget.Button;

public class Chip{
	private int mX = 0;
	private int mY = 0;
	private int mValue = 0; // 0:空 1:黒 -1:白

	public Chip(int x, int y, int value){
		mX = x;
		mY = y;
		mValue = value;
	}
	public void set(int value){ mValue = value; }
	public int get(){ return mValue; }
	public void swap(){ mValue *= -1; }
}
