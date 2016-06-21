package com.lium.evisa.api;

import android.util.Log;

import com.google.gson.Gson;
import com.lium.evisa.R;
import com.lium.evisa.entity.Country;
import com.lium.evisa.entity.Version;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/4/15.
 */
public class ParserApiImpl implements ParserApi {

    @Override
    public Version parserJson(String s) {
       // Log.e("version",s);
        Gson gson=new Gson();
        Version version=gson.fromJson(s,Version.class);
      //  Log.e("version",version.getVersionInfo()+"*"+version.getInfo()+"*"+version.getUrl());
        return version;
    }

    @Override
    public List<Country> parserXml(String xmlData){
        List<Country> countryList = null;
        try {
            countryList=new ArrayList<Country>();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            InputStream in = new ByteArrayInputStream(xmlData.getBytes());
            //xmlPullParser.setInput(new StringReader(xmlData));
            xmlPullParser.setInput(in,"utf-8");
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Log.e("Event", ""+eventType);
                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        //Log.e("TAG", "pName is " + XmlPullParser.START_TAG);
                        String nodeName = xmlPullParser.getName();
                        //Log.e("Node", nodeName);

                        if ("country".equals(nodeName)) {
                           // String pName = xmlPullParser.getAttributeValue(0);
                            Country country=new Country();
                            country.setName(xmlPullParser.getAttributeValue(0));
                            country.setPeriod(Integer.parseInt(xmlPullParser.getAttributeValue(1)));
                            country.setPrice(Integer.parseInt(xmlPullParser.getAttributeValue(3)));
                            country.setImageId(R.drawable.ic_launcher);
                            country.setImaUrl(xmlPullParser.getAttributeValue(4));
                            country.setUrl(xmlPullParser.getAttributeValue(5));
                            country.setTips(xmlPullParser.getAttributeValue(6));
                            country.setIntroduce(xmlPullParser.getAttributeValue(7));
                            countryList.add(country);
                           // Log.e("TAG", "pName1 is " + xmlPullParser.getAttributeValue(0));
                           /* Log.e("TAG", "pName1 is " + xmlPullParser.getAttributeValue(0));
                            Log.e("TAG", "pName2 is " + xmlPullParser.getAttributeValue(1));
                            Log.e("TAG", "pName3 is " + xmlPullParser.getAttributeValue(2));
                            Log.e("TAG", "pName4 is " + xmlPullParser.getAttributeValue(3));
                            Log.e("TAG", "pName5 is " + xmlPullParser.getAttributeValue(4));
                            Log.e("TAG", "pName6 is " + xmlPullParser.getAttributeValue(5));
                            Log.e("TAG", "pName7 is " + xmlPullParser.getAttributeValue(6));
                            Log.e("TAG", "pName8 is " + xmlPullParser.getAttributeValue(7));*/

                        }

                        //case XmlPullParser.START_DOCUMENT:Log.e("TAG", "START_DOCUMENT is " + XmlPullParser.START_DOCUMENT);break;
                        //case XmlPullParser.TEXT:Log.e("TAG", "TEXT is " + XmlPullParser.TEXT);break;
                        //case XmlPullParser.END_DOCUMENT:Log.e("TAG", "END_DOCUMENT is " + XmlPullParser.END_DOCUMENT);break;
                        //case XmlPullParser.END_TAG:Log.e("TAG", "END_TAG is " + XmlPullParser.END_TAG);break;
                    default://Log.e("Event", "default"+eventType);
                        break;
                }

                eventType = xmlPullParser.next();

            }
           // Log.e("Event", ""+eventType);
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return countryList;

    }
}
