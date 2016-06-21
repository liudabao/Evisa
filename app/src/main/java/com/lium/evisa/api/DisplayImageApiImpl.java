package com.lium.evisa.api;

import java.io.File;

import com.bumptech.glide.Glide;
import com.lium.evisa.R;
import com.lium.evisa.global.GlobalContext;

import android.content.Context;
import android.view.WindowManager;
import android.widget.ImageView;

public class DisplayImageApiImpl implements DisplayImageApi{

	private WindowManager manager=(WindowManager)GlobalContext.getContext()
			.getSystemService(Context.WINDOW_SERVICE);
	private int width=manager.getDefaultDisplay().getWidth();
	@Override
	public void display(ImageView imageView, int imageId) {
		// TODO Auto-generated method stub
		
	    Glide.with(GlobalContext.getContext())
	    .load(imageId)
		.centerCrop()
		.crossFade()
	    .error(R.drawable.ic_launcher)
	    .into(imageView);
	}

	@Override
	public void display(ImageView imageView, String imageUrl) {
		// TODO Auto-generated method stub
		Glide.with(GlobalContext.getContext())		
	    .load(imageUrl)
	    .centerCrop()
	    .crossFade()
	    .into(imageView);
	}

	@Override
	public void display(ImageView imageView, String imageUrl, int errorImage) {
		Glide.with(GlobalContext.getContext())
				.load(imageUrl)
				.error(errorImage)
				.centerCrop()
				.crossFade()
				.into(imageView);
	}

}
