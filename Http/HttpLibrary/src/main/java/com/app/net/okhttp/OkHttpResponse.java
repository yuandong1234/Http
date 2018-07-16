package com.app.net.okhttp;

import com.app.net.internal.AbstractHttpResponse;
import com.app.net.internal.http.HttpHeader;
import com.app.net.internal.http.HttpStatus;

import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by yuandong on 2018/7/13.
 */

public class OkHttpResponse extends AbstractHttpResponse {

    private Response mResponse;

    public OkHttpResponse(Response response) {
        this.mResponse = response;
    }

    @Override
    public HttpHeader getHeaders() {

        HttpHeader mHeaders = new HttpHeader();

        for (String name : mResponse.headers().names()) {
            mHeaders.set(name, mResponse.headers().get(name));
        }
        return mHeaders;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.getValue(mResponse.code());
    }

    @Override
    public String getStatusMsg() {
        return mResponse.message();
    }

    @Override
    public long getContentLength() {
        if (mResponse.body() != null) {
            return mResponse.body().contentLength();
        }
        return -1;
    }

    @Override
    protected InputStream getBodyInternal() {
        if (mResponse.body() != null) {
            return mResponse.body().byteStream();
        }
        return null;
    }

    @Override
    protected void closeInternal() {
        if (mResponse.body() != null) {
            mResponse.body().close();
        }
    }
}
