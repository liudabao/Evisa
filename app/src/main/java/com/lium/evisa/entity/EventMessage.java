package com.lium.evisa.entity;

import java.io.InputStream;

/**
 * Created by lenovo on 2016/4/15.
 */
public class EventMessage {
    private  boolean result;
    private  String content;
   // private InputStream in;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
