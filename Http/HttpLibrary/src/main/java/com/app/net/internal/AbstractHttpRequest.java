package com.app.net.internal;

import com.app.net.internal.http.HttpHeader;
import com.app.net.internal.http.HttpParams;
import com.app.net.internal.http.HttpRequest;
import com.app.net.internal.http.HttpResponse;

import java.io.IOException;

/**
 * Created by yuandong on 2018/7/13.
 */

public abstract class AbstractHttpRequest implements HttpRequest {

    private HttpHeader mHeader = new HttpHeader();
    private HttpParams mParams = new HttpParams();

    @Override
    public HttpHeader getHeaders() {
        return mHeader;
    }

    @Override
    public HttpParams getParams() {
        return mParams;
    }

    @Override
    public HttpResponse execute() throws IOException {
        return executeInternal(mHeader, mParams);
    }

    protected abstract HttpResponse executeInternal(HttpHeader headers, HttpParams params) throws IOException;
}
