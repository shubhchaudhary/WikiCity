package com.wikicity.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wikicity.activities.MainActivity;
import com.wikicity.activities.R;

public class WikiFragment extends Fragment {
	
	WebView wiki;
	ProgressDialog progress;
	
	 @Override 
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for this fragment 
	       View rootView=inflater.inflate(R.layout.wiki_fragment, container, false);
	       wiki= (WebView) rootView.findViewById(R.id.webViewWiki);
	       progress = new ProgressDialog(getActivity());
	       wiki.setWebViewClient(new myWebClient(progress));
	       System.out.println("wiki");
	       WebSettings webSettings = wiki.getSettings();
	       webSettings.setJavaScriptEnabled(true);
	       wiki.loadUrl("http://en.wikipedia.org/wiki/"+MainActivity.cityName);
	       return rootView;
	    } 
	  public class myWebClient extends WebViewClient
	  
	  
	    {
		  ProgressDialog progress;
		  public myWebClient(ProgressDialog progress) {
		        this.progress=progress;
		       
			      progress.setMessage("Web Page is Loading....");
			      progress.setTitle("WikiCity");
			      
			      
				
		       
		    } 
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	 
	            view.loadUrl(url);
	           
	            return true;
	 
	        }

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
			//	super.onPageStarted(view, url, favicon);
				progress.show();
				
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				//super.onPageFinished(view, url);
				progress.dismiss();
				
			
			}
	        
	    

	    }
	 
}


