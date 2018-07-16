package com.yuong.http;

import com.app.net.HttpDispatcher;
import com.app.net.HttpTask;
import com.app.net.callback.HttpCallBack;
import com.app.net.internal.http.ContentType;
import com.app.net.internal.http.HttpMethod;

import java.io.File;
import java.util.Map;

/**
 * Created by yuandong on 2018/7/13.
 */

public class ServiceApiProvider {

    private static HttpDispatcher mDispatcher = HttpDispatcher.getInstance();

    public static void helloWorld(String ul, Map<String, String> params, HttpCallBack callBack) {
        HttpTask task = new HttpTask();
        task.setUrl(ul);
        task.setMethod(HttpMethod.POST);
        task.setContentType(ContentType.FORM);
        task.setParams(params);
        task.setCallBack(callBack);
        mDispatcher.add(task);
    }


    public static void helloWorld1(String ul, Map<String, String> params, HttpCallBack callBack) {
        HttpTask task = new HttpTask();
        task.setUrl(ul);
        task.setMethod(HttpMethod.GET);
        task.setParams(params);
        task.setCallBack(callBack);
        mDispatcher.add(task);
    }

    public static void helloWorld2(String ul, Map<String, String> params, HttpCallBack callBack) {
        HttpTask task = new HttpTask();
        task.setUrl(ul);
        task.setMethod(HttpMethod.POST);
        task.setContentType(ContentType.FORM);
        task.setParams(params);
        task.setCallBack(callBack);
        mDispatcher.add(task);
    }
    public static void helloWorld3(String ul, File file, HttpCallBack callBack) {
        HttpTask task = new HttpTask();
        task.setUrl(ul);
        task.setMethod(HttpMethod.POST);
        task.setContentType(ContentType.STREAM);
        task.setFile(file);
        task.setCallBack(callBack);
        mDispatcher.add(task);
    }
}
