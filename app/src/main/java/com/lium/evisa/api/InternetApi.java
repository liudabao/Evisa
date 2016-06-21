package com.lium.evisa.api;

import com.lium.evisa.entity.EventMessage;
import com.lium.evisa.entity.Version;

import java.util.Map;

public interface InternetApi {

    public EventMessage getHandle(String url);

    public  void  getXmlHandle(String url);

    public EventMessage postHandle(String url,Map<String,String> params);

    public boolean isNetConnectted();
}
