package com.app.net.callback;

import com.app.net.internal.http.HttpResponse;

/**
 * Created by yuandong on 2018/7/13.
 */

public abstract class WrapperCallBack {

    public abstract void success(HttpResponse response);

    public abstract void fail(int errorCode, String errorMsg);
}
