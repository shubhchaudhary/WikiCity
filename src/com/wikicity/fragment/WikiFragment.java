package com.wikicity.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;

import com.wikicity.activities.MainActivity;
import com.wikicity.activities.R;

public class WikiFragment extends Fragment {
	
	WebView wiki;
	
	 @Override 
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for this fragment 
	       View rootView=inflater.inflate(R.layout.wiki_fragment, container, false);
	       wiki= (WebView) rootView.findViewById(R.id.webViewWiki);
	       wiki.loadUrl("http://en.wikipedia.org/wiki/"+MainActivity.cityName);
	       return rootView;
	    } 
}
