package com.app.net.internal.http;

import java.io.IOException;

/**
 * Created by yuandong on 2018/7/13.
 */

public interface HttpRequest extends Header {

    String getUri();

    HttpMethod getMethod();

    HttpParams getParams();

    HttpResponse execute() throws IOException;
}
