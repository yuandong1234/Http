package com.app.net;

import com.app.net.callback.WrapperCallBack;
import com.app.net.internal.http.ContentType;
import com.app.net.internal.http.HttpParams;
import com.app.net.internal.http.HttpRequest;
import com.app.net.internal.http.HttpResponse;
import com.app.net.utils.LogUtil;

import java.io.IOException;

/**
 * Created by yuandong on 2018/7/13.
 */

public class HttpRunnable implements Runnable {

    private HttpRequest mHttpRequest;
    private HttpDispatcher mHttpDispatcher;
    private HttpTask mHttpTask;

    public HttpRunnable(HttpRequest httpRequest, HttpTask httpTask, HttpDispatcher httpDispatcher) {
        this.mHttpRequest = httpRequest;
        this.mHttpDispatcher = httpDispatcher;
        this.mHttpTask = httpTask;
    }

    @Override
    public void run() {
        try {
            addParams(mHttpRequest.getParams(), mHttpTask);

            HttpResponse response = mHttpRequest.execute();

            String contentType = response.getHeaders().getContentType();
            LogUtil.e("**************",contentType);
            ContentType httpContentType = ContentType.getContentType(contentType);
            LogUtil.e("**************",httpContentType.getValue());

            WrapperCallBack wrapperCallBack = HttpCallBckFactory.createCallBack(httpContentType, mHttpTask.getCallBack());
            if (response.getStatus().isSuccess()) {
                if (wrapperCallBack != null) {
                    wrapperCallBack.success(response);
                }
            } else {
                if (wrapperCallBack != null) {
                    wrapperCallBack.fail(response.getStatus().getCode(), response.getStatusMsg());
                }
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mHttpDispatcher.finish(mHttpTask);
        }
    }

    private void addParams(HttpParams params, HttpTask task) {
        if (task == null) return;
        params.setUrl(task.getUrl());
        params.setHttpMethod(task.getMethod());
        params.setContentType(task.getContentType());
        params.setJson(task.getJson());
        params.setParams(task.getParams());
        params.setFiles(task.getFlies());
        params.setFile(task.getFile());

    }
}
