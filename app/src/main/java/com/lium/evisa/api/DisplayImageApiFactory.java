package com.lium.evisa.api;

public class DisplayImageApiFactory {
	
	private static volatile DisplayImageApi displayImageApi;
	
	private DisplayImageApiFactory(){
		
	}
	
	public static DisplayImageApi getInstance(){
		if(displayImageApi==null){
			synchronized (DisplayImageApiFactory.class) {
				if(displayImageApi==null){
					displayImageApi=new DisplayImageApiImpl();
				}				
			}
		}
		return displayImageApi;
		
	}

}
