package com.xiaheng.guoruoweb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentPage1 extends Fragment{
     private WebView webview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragement1 = inflater.inflate(R.layout.fragment_1, container, false);
		return  fragement1; 
	}
	 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		   
		 setView(); 
		 setListener();
		super.onActivityCreated(savedInstanceState);
	}

	@SuppressLint("JavascriptInterface")
	private void setListener() {

//		webview.loadUrl("http://www.grwtest.com18.cn/wap/index.jsp");
		webview.loadUrl("file:///android_asset/index.htm");
		webview.setWebViewClient(new WebViewClient(){       
	        public boolean shouldOverrideUrlLoading(WebView   view, String url) {       
	            view.loadUrl(url);    
	            return true;       
	        } 

		}); 
	
	}
	
	private void setView() {
		// TODO Auto-generated method stub
		 webview=(WebView)getView().findViewById(R.id.webView1);
		 
	}
	
}
