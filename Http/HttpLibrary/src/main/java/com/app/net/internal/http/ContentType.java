package com.app.net.internal.http;

/**
 * Created by yuandong on 2018/7/13.
 */


public enum  ContentType {
    JSON("application/json;charset=UTF-8"),//application/json; charset=utf-8
    FORM("application/x-www-form-urlencoded"),//application/x-www-form-urlencoded
    MULTIPART("multipart/form-data"),//multipart/form-data
    STREAM("application/octet-stream");//二进制流数据

    // ....

    /**
     * 常见的文件类型
     *  text/html	HTML格式
     text/plain	纯文本格式
     text/xml	XML格式
     image/gif	gif图片格式
     image/jpeg	jpg图片格式
     image/png	png图片格式
     application/xhtml+xml	XHTML格式
     application/xml	XML数据格式
     application/atom+xml	Atom XML聚合格式
     application/json	JSON数据格式
     application/pdf	pdf格式
     application/msword	Word文档格式
     application/octet-stream	二进制流数据
     */

    private String mValue;

    ContentType(String mValue) {
        this.mValue = mValue;
    }

    public String getValue() {
        return mValue;
    }

    public static ContentType getContentType(String value){
        for (ContentType contentType : values()) {
            if (value.equalsIgnoreCase(contentType.mValue) ) {
                return contentType;
            }
        }
        return null;
    }

}
