package com.xiaheng.guoruoweb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class LoadingActivity extends Activity{
	private static final int LOAD_DISPLAY_TIME = 1500;//设置显示启动时间
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load);
		
		getWindow().setFormat(PixelFormat.RGBA_8888);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		 
		 new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent  intent = new Intent(LoadingActivity.this,MainActivity.class);
					LoadingActivity.this.startActivity(intent);
					LoadingActivity.this.finish();
				}
			}, LOAD_DISPLAY_TIME);
	}
}
