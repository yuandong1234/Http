package com.app.net.callback;

import android.util.Log;

import com.app.net.convert.JsonConvert;
import com.app.net.internal.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yuandong on 2018/7/13.
 */

public class JsonCallBack extends WrapperCallBack {

    private HttpCallBack mCallBack;
    private JsonConvert mConvert=new JsonConvert();


    public JsonCallBack(HttpCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public void success(HttpResponse response) {
        try {
            String content = getData(response.getBody());
            Log.i("content",content);
            Object object = mConvert.parse(content, getType());
            if(mCallBack!=null){
                mCallBack.success(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fail(int errorCode, String errorMsg) {
        if (mCallBack != null) {
            mCallBack.fail(errorCode, errorMsg);
        }
    }

    private String getData(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String data = new String(result.toByteArray());
        result.close();
        return data;
    }

    private Type getType() {
        Type type = mCallBack.getClass().getGenericSuperclass();
        Type[] paramType = ((ParameterizedType) type).getActualTypeArguments();
        return paramType[0];
    }
}
