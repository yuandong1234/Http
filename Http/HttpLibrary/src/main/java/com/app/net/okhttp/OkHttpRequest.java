package com.app.net.okhttp;

import com.app.net.internal.AbstractHttpRequest;
import com.app.net.internal.http.HttpHeader;
import com.app.net.internal.http.HttpMethod;
import com.app.net.internal.http.HttpParams;
import com.app.net.internal.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yuandong on 2018/7/13.
 */

public class OkHttpRequest extends AbstractHttpRequest {

    private OkHttpClient mClient;

    public OkHttpRequest(OkHttpClient client) {
        this.mClient = client;
    }

    @Override
    public String getUri() {
        return getParams().getUrl();
    }

    @Override
    public HttpMethod getMethod() {
        return getParams().getHttpMethod();
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader headers, HttpParams params) throws IOException {
        Request.Builder builder = new Request.Builder();


        OkHttpRequestBody okHttpRequestBody = new OkHttpRequestBody.Builder().
                contentType(params.getContentType()).
                method(params.getHttpMethod()).
                url(params.getUrl()).
                json(params.getJson()).
                params(params.getParams()).
                flies(params.getFiles()).
                file(params.getFile()).
                Build();

        HttpMethod mMethod = params.getHttpMethod();

        if (mMethod == HttpMethod.GET) {
            builder.url(okHttpRequestBody.getUrl()).get();
        } else {
            builder.url(okHttpRequestBody.getUrl()).post(okHttpRequestBody.getRequestBody());
        }

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = builder.build();

        Response response = mClient.newCall(request).execute();

        return new OkHttpResponse(response);
    }
}
