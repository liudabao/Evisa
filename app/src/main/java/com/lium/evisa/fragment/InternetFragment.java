package com.lium.evisa.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lium.evisa.R;
import com.lium.evisa.api.InternetApi;
import com.lium.evisa.api.InternetApiFactory;


public class InternetFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view= inflater.inflate(R.layout.fragment_internet, container, false);
		ImageButton imageButton=(ImageButton) view.findViewById(R.id.internet_button);
		imageButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				InternetApi internetApi= InternetApiFactory.getInstance();
				boolean isConnect=internetApi.isNetConnectted();
				if(isConnect){
					HallFragment hallFragment=new HallFragment();
					FragmentManager fm=getFragmentManager();
					FragmentTransaction transaction=fm.beginTransaction();
					
					transaction.replace(R.id.frame, new HallFragment());
					//transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					transaction.addToBackStack(null);
					transaction.commit();
				}
				
			}
			
		});
		return view;
	}

}
