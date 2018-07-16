package com.app.net;

import com.app.net.callback.HttpCallBack;
import com.app.net.callback.JsonCallBack;
import com.app.net.callback.WrapperCallBack;
import com.app.net.internal.http.ContentType;

/**
 * Created by yuandong on 2018/7/13.
 */

public class HttpCallBckFactory {

    public static WrapperCallBack createCallBack(ContentType contentType, HttpCallBack callBack) {

        if (contentType == null || callBack == null) return null;

        WrapperCallBack wrapperCallBack = null;
        if (contentType == ContentType.JSON) {
            wrapperCallBack = new JsonCallBack(callBack);
        } else {
            //TODO
        }
        return wrapperCallBack;
    }
}
