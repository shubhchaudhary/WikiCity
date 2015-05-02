package com.wikicity.activities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.wikicity.fragment.CurrentLocFragment;
import com.wikicity.fragment.WikiFragment;
import com.wikicity.utility.GPSTracker;

public class MainActivity extends Activity {
	LocationManager service;
	double longitude, latitude;
	Geocoder geocoder;
	public static  String cityName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		 if (savedInstanceState == null) {
	       getFragmentManager()
	                    .beginTransaction()
	                    .add(R.id.fragmentParentViewGroup, new CurrentLocFragment())
	                    .commit();
	        }
		service = (LocationManager) getSystemService(LOCATION_SERVICE);
		CheckGPS();
		GPSTracker track = new GPSTracker(MainActivity.this);
		latitude = track.getLatitude();
		longitude = track.getLongitude();
	    System.out.println("Latitude"+latitude);
		 
		try {
			Boolean Isinternet = haveNetworkConnection();
			if(Isinternet)
			{
				geocoder = new Geocoder(this, Locale.getDefault());
				 List<Address> addressList;
				 addressList = geocoder.getFromLocation(latitude, longitude, 1);
				if (addressList != null && addressList.size() > 0) {
					
		             cityName = addressList.get(0).getLocality();
		             System.out.println("City"+cityName);
		             Intent i =new Intent(MainActivity.this,CurrentLocFragment.class);
		         }else
		         {
		        	 Toast.makeText(getApplicationContext(), "Please try again!!!", Toast.LENGTH_SHORT).show();
		         }
			}else
			{
				Toast.makeText(getApplicationContext(), "No Internet Connection!!!", Toast.LENGTH_SHORT).show();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         	}

	
	public void CheckGPS() {
		boolean enabled = service
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!enabled) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
	}
	
	 private boolean haveNetworkConnection() {
			boolean haveConnectedWifi = false;
			boolean haveConnectedMobile = false;

			ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
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
