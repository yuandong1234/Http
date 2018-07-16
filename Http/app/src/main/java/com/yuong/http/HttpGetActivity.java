package com.yuong.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.net.callback.HttpCallBack;
import com.yuong.http.entity.News;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class HttpGetActivity extends AppCompatActivity {

    private TextView mContent;

    private String APPKEY = "aa47561558f285fee99f1943c7b844fb";
    private String url = "http://v.juhe.cn/toutiao/index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get);

        mContent = findViewById(R.id.content);
        initData();
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("key", APPKEY);
        params.put("type", "top");

       ServiceApiProvider.helloWorld1(url, params, new HttpCallBack<News>() {
           @Override
           public void success(final News data) {
               Log.e("HttpGetActivity",data.toString());
               HttpGetActivity.this.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       mContent.setText(data.toString());
                   }
               });

           }

           @Override
           public void fail(int errorCode, String errorMsg) {

           }
       });

    }
}
