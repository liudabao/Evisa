package com.lium.evisa.ui;


import com.lium.evisa.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MyProgressWebView extends WebView {
	private ProgressBar mProgressBar;
	

	public MyProgressWebView(Context context) {
		super(context);
		mProgressBar = new ProgressBar(context, null,
				android.R.attr.progressBarStyleHorizontal);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 8);
		mProgressBar.setLayoutParams(layoutParams);

		Drawable drawable = context.getResources().getDrawable(
				R.drawable.web_progress_bar_states);
		mProgressBar.setProgressDrawable(drawable);
		mProgressBar.setMax(100);
		addView(mProgressBar);
		setWebChromeClient(new WebChromeClient());
		setWebViewClient(new WebClient());
	}

	public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress == 100) {
				mProgressBar.setVisibility(GONE);
			} else if (mProgressBar.getVisibility() == GONE){
				mProgressBar.setVisibility(VISIBLE);
		    }
			mProgressBar.setProgress(newProgress);
			Log.e("progress",""+newProgress);

			super.onProgressChanged(view, newProgress);
		}
		

	}
	
	public class WebClient extends WebViewClient {
		
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			view.loadUrl(url);
			return true;
		}
	}

	/*@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		mProgressBar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}*/
}