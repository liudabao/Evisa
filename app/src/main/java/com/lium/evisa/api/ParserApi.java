package com.lium.evisa.api;

import com.lium.evisa.entity.Country;
import com.lium.evisa.entity.Version;

import java.io.InputStream;
import java.util.List;

/**
 * Created by lenovo on 2016/4/15.
 */
public interface ParserApi {
    public Version parserJson(String s);

    public List<Country> parserXml(String s);

}
