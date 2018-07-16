package com.app.net.convert;

import com.app.net.internal.http.HttpResponse;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by yuandong on 2018/7/13.
 */

public interface Convert {

    Object parse(HttpResponse response, Type type) throws IOException;

    Object parse(String content, Type type) throws IOException;
}
