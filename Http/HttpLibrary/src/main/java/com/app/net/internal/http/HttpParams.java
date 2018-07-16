package com.app.net.internal.http;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by yuandong on 2018/7/13.
 */

public class HttpParams {

    private String mUrl;
    private HttpMethod mHttpMethod;
    private ContentType mContentType=ContentType.FORM;
    private String mJson;
    private Map<String, String> mParams;
    private List<File> mFiles;
    private File mFile;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public HttpMethod getHttpMethod() {
        return mHttpMethod;
    }

    public void setHttpMethod(HttpMethod method) {
        this.mHttpMethod = method;
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

    public List<File> getFiles() {
        return mFiles;
    }

    public void setFiles(List<File> files) {
        this.mFiles = files;
    }

    public File getFile() {
        return mFile;
    }

    public void setFile(File file) {
        this.mFile = file;
    }

}
