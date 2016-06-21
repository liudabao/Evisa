package com.lium.evisa.api;

public class ParserApiFactory {

	private static volatile ParserApi parserApi;

	private ParserApiFactory(){
		
	}

	public static ParserApi getInstance(){
		if(parserApi==null){
			synchronized (ParserApiFactory.class) {
				if(parserApi==null){
					parserApi=new ParserApiImpl();
				}				
			}
		}
		return parserApi;
		
	}

}
