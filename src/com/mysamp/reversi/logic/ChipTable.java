package com.mysamp.reversi.logic;

import android.R.integer;
import android.view.ViewGroup;


public class ChipTable {
	private Chip[] mChips = new Chip[4 * 4];
	
	public ChipTable(){
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				mChips[y * 4 + x] = new Chip(x, y, 0);
			}
		}
	}
	
	public Chip at(int x, int y){
		if(x >= 0 && x < 4 && y >= 0 && y < 4){
			return mChips[y * 4 + x];
		}
		else{
			return null;
		}
	}
	
	// 指定位置にチップを置けるかどうかを判定
	public boolean _settable(int orgx, int orgy, int offx, int offy, int value){
		// 隣接する場所をチェックしていく
		int x = orgx + offx;
		int y = orgy + offy;
		int flag = 0;
		while(true){
			Chip chip = at(x, y);
			if(chip == null){ return false; }
			else if(chip.get() == 0){ return false; }
			else if(chip.get() == -value){ flag = 1; }
			else if(chip.get() == value){
				if(flag == 1){
					return true;
				}
				else{
					return false;
				}
			}
			x += offx;
			y += offy;
		}
	}
	public boolean settable(int x, int y, int value){
		// まずは置く場所自身をチェック
		Chip pos = at(x, y);
		if(pos == null)return false; // 範囲外
		if(pos.get() != 0)return false; // 既に何かが置かれている
		// 周りをチェック
		if(_settable(x, y, -1, -1, value))return true;
		if(_settable(x, y,  0, -1, value))return true;
		if(_settable(x, y,  1, -1, value))return true;
		if(_settable(x, y, -1,  0, value))return true;
		if(_settable(x, y,  1,  0, value))return true;
		if(_settable(x, y, -1,  1, value))return true;
		if(_settable(x, y,  0,  1, value))return true;
		if(_settable(x, y,  1,  1, value))return true;
		return false;
	}
	public void _around(int orgx, int orgy, int offx, int offy, int value){
		// 隣接する場所をチェックしていく
		if(_settable(orgx, orgy, offx, offy, value)){
			int x = orgx + offx;
			int y = orgy + offy;
			while(true){
				Chip chip = at(x, y);
				if(chip == null)break;
				else if(chip.get() == 0)break;
				else if(chip.get() == -value){
					chip.set(value);
				}
				else if(chip.get() == value)break;
				x += offx;
				y += offy;
			}
		}
	}
	public void set(int x, int y, int value){
		// まずセット
		at(x, y).set(value);
		// 周りの盤面を変更
		_around(x, y, -1, -1, value);
		_around(x, y,  0, -1, value);
		_around(x, y,  1, -1, value);
		_around(x, y, -1,  0, value);
		_around(x, y,  1,  0, value);
		_around(x, y, -1,  1, value);
		_around(x, y,  0,  1, value);
		_around(x, y,  1,  1, value);
	}
	public boolean onClick(int x, int y, int value){
		if(settable(x, y, value)){
			set(x, y, value);
			return true;
		}
		else{
			return false;
		}
	}
	
	// リセット
	public void clear(){
		// 全部空にする
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				at(x, y).set(0);
			}
		}
		
		// 初期チップ
		at(1, 1).set(1);
		at(2, 1).set(-1);
		at(1, 2).set(-1);
		at(2, 2).set(1);
	}
}
