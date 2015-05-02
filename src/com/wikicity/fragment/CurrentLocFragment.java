package com.wikicity.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wikicity.activities.MainActivity;
import com.wikicity.activities.R;

public class CurrentLocFragment extends Fragment{
	EditText cityName;
	Button btnDetail;
	
	
	 @Override 
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for this fragment 
	       View rootView=inflater.inflate(R.layout.location_fragment, container, false);
	       cityName= (EditText) rootView.findViewById(R.id.edtCity);
	       btnDetail=(Button) rootView.findViewById(R.id.btnWiki);
	       cityName.setText(""+MainActivity.cityName);
	       btnDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Boolean Isinternet = haveNetworkConnection();
				String city=cityName.getText().toString().trim();
				if(Isinternet)
				{
					if(!(city.equals("")))
					{
						MainActivity.cityName=city;
				  Fragment fragment = new WikiFragment(); 
			        FragmentManager fragmentManager = getActivity().getFragmentManager(); 
			        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); 
			        fragmentTransaction.replace(R.id.fragmentParentViewGroup, fragment);
			        fragmentTransaction.addToBackStack(null); 
			        fragmentTransaction.commit(); 
				}else
				{
					Toast.makeText(getActivity(), "Please Enter City Name", Toast.LENGTH_SHORT).show();
				}
					}else
				{
					Toast.makeText(getActivity(), "No Internet Connection!!!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	       return rootView;
	       
	    } 
	 
	 
	 private boolean haveNetworkConnection() {
			boolean haveConnectedWifi = false;
			boolean haveConnectedMobile = false;

			ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] netInfo = cm.getAllNetworkInfo();
			for (NetworkInfo ni : netInfo) {
				if (ni.getTypeName().equalsIgnoreCase("WIFI"))
					if (ni.isConnected())
						haveConnectedWifi = true;
				if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
					if (ni.isConnected())
						haveConnectedMobile = true;
			}
			return haveConnectedWifi || haveConnectedMobile;
		}
}
