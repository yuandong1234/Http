package com.app.net;

import com.app.net.internal.http.HttpRequest;
import com.app.net.okhttp.OkHttpRequestFactory;

import java.io.IOException;

/**
 * Created by yuandong on 2018/7/13.
 */

public class HttpRequestProvider {
    private HttpRequestFactory mHttpRequestFactory;

    public HttpRequestProvider() {
        mHttpRequestFactory = new OkHttpRequestFactory();
    }

    public HttpRequest getHttpRequest() throws IOException {
        return mHttpRequestFactory.createHttpRequest();
    }

    public HttpRequestFactory getHttpRequestFactory() {
        return mHttpRequestFactory;
    }

    public void setHttpRequestFactory(HttpRequestFactory httpRequestFactory) {
        this.mHttpRequestFactory = httpRequestFactory;
    }
}
