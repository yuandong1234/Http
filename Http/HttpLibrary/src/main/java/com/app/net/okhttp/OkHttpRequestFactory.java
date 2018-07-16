package com.app.net.okhttp;

import com.app.net.HttpRequestFactory;
import com.app.net.internal.http.HttpRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by yuandong on 2018/7/13.
 */

public class OkHttpRequestFactory implements HttpRequestFactory {
    private OkHttpClient mClient;

    public OkHttpRequestFactory() {
        mClient = new OkHttpClient();
    }

    @Override
    public HttpRequest createHttpRequest() {
        return new OkHttpRequest(mClient);
    }

    @Override
    public void setReadTimeOut(int readTimeOut) {
        this.mClient = mClient.newBuilder().
                readTimeout(readTimeOut, TimeUnit.SECONDS).
                build();
    }

    @Override
    public void setWriteTimeOut(int writeTimeOut) {
        this.mClient = mClient.newBuilder().
                writeTimeout(writeTimeOut, TimeUnit.SECONDS).
                build();
    }

    @Override
    public void setConnectionTimeOut(int connectionTimeOut) {
        this.mClient = mClient.newBuilder().
                connectTimeout(connectionTimeOut, TimeUnit.SECONDS).
                build();
    }
}
