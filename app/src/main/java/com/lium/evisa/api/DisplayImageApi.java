package com.lium.evisa.api;

import java.io.File;

import android.widget.ImageView;

public interface DisplayImageApi {
	

	public void display(ImageView imageView,int imageId);
	

	public void display(ImageView imageView,String imageUrl);

	public void display(ImageView imageView,String imageUrl,int errorImage);
}
