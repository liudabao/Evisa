package com.lium.evisa.api;

public class InternetApiFactory {

	private static volatile InternetApi internetApi;

	private InternetApiFactory(){
		
	}

	public static InternetApi getInstance(){
		if(internetApi==null){
			synchronized (InternetApiFactory.class) {
				if(internetApi==null){
					internetApi=new InternetApiImpl();
				}				
			}
		}
		return internetApi;
		
	}

}
