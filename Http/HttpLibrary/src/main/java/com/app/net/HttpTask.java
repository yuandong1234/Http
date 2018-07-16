package com.app.net;

import com.app.net.callback.HttpCallBack;
import com.app.net.internal.http.ContentType;
import com.app.net.internal.http.HttpMethod;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by yuandong on 2018/7/13.
 */

public class HttpTask {
    private String mUrl;
    private HttpMethod mMethod;
    private ContentType mContentType;
    private String mJson;
    private Map<String, String> mParams;
    private List<File> mFlies;
    private File mFile;
    private HttpCallBack mCallBack;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public HttpMethod getMethod() {
        return mMethod;
    }

    public void setMethod(HttpMethod method) {
        this.mMethod = method;
    }

    public ContentType getContentType() {
        return mContentType;
    }

    public void setContentType(ContentType contentType) {

        this.mContentType = contentType;
    }

    public String getJson() {
        return mJson;
    }

    public void setJson(String json) {
        this.mJson = json;
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    public void setParams(Map<String, String> params) {
        this.mParams = params;
    }

    public List<File> getFlies() {
        return mFlies;
    }

    public void setFlies(List<File> flies) {
        this.mFlies = flies;
    }

    public File getFile() {
        return mFile;
    }

    public void setFile(File file) {
        this.mFile = file;
    }

    public HttpCallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(HttpCallBack callBack) {
        this.mCallBack = callBack;
    }
}
