package com.app.net.callback;

/**
 * Created by yuandong on 2018/7/13.
 */

public abstract class HttpCallBack<T>{
    public abstract void success(T data);

    public abstract void fail(int errorCode, String errorMsg);
}
