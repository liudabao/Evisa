package com.lium.evisa.entity;

import java.io.Serializable;

import android.R.integer;

import android.R.integer;

public class Country implements Serializable{
	
	
	private String name;
	private String introduce;
	private int price;
	private int period;
	private int imageId;
	private String imaUrl;
	private String url;
	private String tips;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImaUrl() {
		return imaUrl;
	}

	public void setImaUrl(String imaUrl) {
		this.imaUrl = imaUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
}
