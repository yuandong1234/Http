package com.app.net.convert;

import com.app.net.internal.http.HttpResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by yuandong on 2018/7/13.
 */

public class JsonConvert implements Convert{
    private Gson gson = new Gson();

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Override
    public Object parse(HttpResponse response, Type type) throws IOException {

        Reader reader = new InputStreamReader(response.getBody());
        return gson.fromJson(reader, type);

    }

    @Override
    public Object parse(String content, Type type) throws IOException {
        return gson.fromJson(content, type);
    }
}
