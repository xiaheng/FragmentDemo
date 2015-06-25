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
public class FragmentPage4 extends Fragment{

	 private WebView webview;
		@Override
		public View onCreateView(LayoutInflater inflater,
				ViewGroup container,Bundle savedInstanceState) {	
			View fragement4 = inflater.inflate(R.layout.fragment_4, container, false);
			return fragement4;		
		}	
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			setView();
			setLitener();
			super.onActivityCreated(savedInstanceState);
			
		}
		private void setLitener() {
			// TODO Auto-generated method stub
			webview.loadUrl("http://www.grwtest.com18.cn/wap/grzx.jsp");
			
			webview.setWebViewClient(new WebViewClient(){       
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {       
		            view.loadUrl(url);       
		            return true;       
		        }       
			}); 

		}
		private void setView() {
			// TODO Auto-generated method stub
			webview = (WebView)getView().findViewById(R.id.webView4);
			
		}
}