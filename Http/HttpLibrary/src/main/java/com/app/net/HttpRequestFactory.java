package com.app.net;

import com.app.net.internal.http.HttpRequest;

/**
 * Created by yuandong on 2018/7/13.
 */

public interface HttpRequestFactory {

    HttpRequest createHttpRequest();

    void setReadTimeOut(int readTimeOut);

    void setWriteTimeOut(int writeTimeOut);

    void setConnectionTimeOut(int connectionTimeOut);
}
