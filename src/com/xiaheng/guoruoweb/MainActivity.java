package com.xiaheng.guoruoweb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled", "NewApi" })
public class MainActivity extends Activity implements OnClickListener{
	private LinearLayout ll_home;
	private LinearLayout ll_classify;
	private LinearLayout ll_shopping;
	private LinearLayout ll_mine;
	private TextView text_home;
	private TextView text_classify;
	private TextView text_shopping;
	private TextView text_mine;
	private ImageView image_home;
	private ImageView image_classify;
	private ImageView image_shopping;
	private ImageView image_mine;
	//Fragment管理器
    private FragmentManager fragmentManager;
	private FragmentPage1 fragmentPage1;
	private FragmentPage2 fragmentPage2;
	private FragmentPage3 fragmentPage3;
	private FragmentPage4 fragmentPage4;
	private WebView webview;
	
	//退出时间
	private long exitTime = 0;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.activity_main);
		initView();
		//setListener();
		fragmentManager = getFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
		
		webview = (WebView) findViewById(R.id.webView);
		//设置js属性
		WebSettings webSetting = webview.getSettings();
		webSetting.setDatabaseEnabled(true);
		webSetting.setJavaScriptEnabled(true);
		webSetting.setBuiltInZoomControls(true);
		//设置缩放属性
		webview.requestFocus();
		// 设置最小缩放等级
		webview.setInitialScale(100);
		// 无限缩放,设置此属性，可任意比例缩放。
		webview.setHorizontalScrollbarOverlay(true);
//		webview.loadUrl("http://www.grwtest.com18.cn/wap/index.jsp");
		webview.loadUrl("file:///android_asset/index.htm");
		webview.addJavascriptInterface(new scriptInterface(this), "image");
		webview.setWebViewClient(new WebViewClient(){
			// 加载页面的URL
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			// 拦截URL，必须的
			@SuppressLint("NewApi")
			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view,
					String url) {
				return super.shouldInterceptRequest(view, url);
			}
			@Override
			public void onLoadResource(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onLoadResource(view, url);
			}
		});
		
		webview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
			}
		});
		
	}
	
	//添加js交互接口
	class  scriptInterface{
		private Context context;
		public scriptInterface(Context context) {
			this.context = context;
		}
	}

	private void initView(){
		ll_home = (LinearLayout)findViewById(R.id.ll_home);
		ll_classify = (LinearLayout)findViewById(R.id.ll_classify);
		ll_shopping = (LinearLayout)findViewById(R.id.ll_shopping);
		ll_mine = (LinearLayout)findViewById(R.id.ll_mine);
		
		image_home = (ImageView)findViewById(R.id.image_home);
		image_classify = (ImageView)findViewById(R.id.image_classify);
		image_shopping = (ImageView)findViewById(R.id.image_shopping);
		image_mine = (ImageView)findViewById(R.id.image_mine);
		text_home = (TextView)findViewById(R.id.text_home);
		text_classify = (TextView)findViewById(R.id.text_classify);
		text_shopping = (TextView)findViewById(R.id.text_shopping);
		text_mine = (TextView)findViewById(R.id.text_mine);
		ll_home.setOnClickListener(this);
		ll_classify.setOnClickListener(this);
		ll_shopping.setOnClickListener(this);
		ll_mine.setOnClickListener(this);
		
		 
//		btLeft = (ImageButton)findViewById(R.id.header_left_btn);
//		btRight = (ImageButton)findViewById(R.id.header_right_btn);
		
	}

	/**
	 * 注入监听函数
	 
	private void addImageClickListner() {
		// 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
	     
		webview.loadUrl("javascript:(function(){" +
		"var objs = document.getElementsByTagName(\"img\"); " + 
				"for(var i=0;i<objs.length;i++)  " + 
		"{"
				+ "    objs[i].onclick=function()  " + 
		"    {  " 
				+ "        window.imagelistner.openImage(this.src);  " + 
		"    }  " + 
		"}" + 
		"})()");
	}*/
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_home:
			setTabSelection(0);
			break;
		case R.id.ll_classify:
			setTabSelection(1);
			break;
		case R.id.ll_shopping:
			setTabSelection(2);
			break;
		case R.id.ll_mine:
			setTabSelection(3);
			break;
		}
		
	}
	
	private void setTabSelection(int index){
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了首页tab时，改变控件的图片和文字颜色
			image_home.setImageResource(R.drawable.menu1);
			text_home.setTextColor(Color.parseColor("#FF9328"));
			if(fragmentPage1 == null){
				fragmentPage1 = new FragmentPage1();
				transaction.add(R.id.fl_content,fragmentPage1);
			}else {
				transaction.show(fragmentPage1);
			}
			break;
		case 1:
			// 当点击了首页tab时，改变控件的图片和文字颜色
			image_classify.setImageResource(R.drawable.menu2);
			text_classify.setTextColor(Color.parseColor("#FF9328"));
			if(fragmentPage2 == null){
				fragmentPage2 = new FragmentPage2();
				transaction.add(R.id.fl_content,fragmentPage2);
			}else {
				transaction.show(fragmentPage2);
			}
			break;
		case 2:
			// 当点击了首页tab时，改变控件的图片和文字颜色
			image_shopping.setImageResource(R.drawable.menu3);
			text_shopping.setTextColor(Color.parseColor("#FF9328"));
			if(fragmentPage3 == null){
				fragmentPage3 = new FragmentPage3();
				transaction.add(R.id.fl_content,fragmentPage3);
			}else {
				transaction.show(fragmentPage3);
			}
			break;	
		case 3:
			// 当点击了首页tab时，改变控件的图片和文字颜色
			image_mine.setImageResource(R.drawable.menu4);
			text_mine.setTextColor(Color.parseColor("#FF9328"));
			if(fragmentPage4 == null){
				fragmentPage4 = new FragmentPage4();
				transaction.add(R.id.fl_content,fragmentPage4);
			}else {
				transaction.show(fragmentPage4);
			}
			break;
		default:
			break;
		}
		transaction.commit();

	}
	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		image_home.setImageResource(R.drawable.menu1_1);
		text_home.setTextColor(Color.parseColor("#82858b"));
		image_classify.setImageResource(R.drawable.menu2_1);
		text_classify.setTextColor(Color.parseColor("#82858b"));
		image_shopping.setImageResource(R.drawable.menu3_1);
		text_shopping.setTextColor(Color.parseColor("#82858b"));
		image_mine.setImageResource(R.drawable.menu4_1);
		text_mine.setTextColor(Color.parseColor("#82858b"));
	}
	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (fragmentPage1 != null) {
			transaction.hide(fragmentPage1);
		}
		if (fragmentPage2 != null) {
			transaction.hide(fragmentPage2);
		}
		if (fragmentPage3 != null) {
			transaction.hide(fragmentPage3);
		}
		if (fragmentPage4 != null) {
			transaction.hide(fragmentPage4);
		}
	}
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if (keyCode == KeyEvent.KEYCODE_BACK 
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), 
						"再按一次退出程序", Toast.LENGTH_SHORT).show();     
				exitTime = System.currentTimeMillis();
			}else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return true;
    }
	
}
