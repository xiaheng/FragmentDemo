package com.xiaheng.guoruoweb;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentPage2 extends Fragment{
    private WebView webview;
    
	@Override
	public View onCreateView(LayoutInflater inflater, 
					ViewGroup container,Bundle savedInstanceState) {	
		View fragement2 = inflater.inflate(R.layout.fragment_2, container, false);
		return fragement2;		
	}	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	setView();
    	setListener();
    	super.onActivityCreated(savedInstanceState);
    	
    }
	private void setView() {
		// TODO Auto-generated method stub
		webview = (WebView)getView().findViewById(R.id.webView2);
	}
	private void setListener() {
		// TODO Auto-generated method stub
		webview.loadUrl("http://www.grwtest.com18.cn/wap/ftype.jsp");
		webview.setWebViewClient(new WebViewClient(){       
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {       
	            view.loadUrl(url);       
	            return true;       
	        }       
	}); 
	}
}