package com.yuong.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.net.callback.HttpCallBack;
import com.yuong.http.entity.News;
import com.yuong.http.entity.Person;

import java.util.HashMap;
import java.util.Map;

public class HttpPostActivity extends AppCompatActivity {
    private String url = "http://192.168.87.228:8080/Web/HelloServlet";
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_post);
        tv_content = findViewById(R.id.content);
        initData();
       // initData2();
    }


    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "袁栋");
        params.put("userage", "26");
        params.put("usersex", "0");
        ServiceApiProvider.helloWorld(url, params, new HttpCallBack<Person>() {
            @Override
            public void success(final Person data) {
                Log.i("HttpPostActivity", data.toString());
                HttpPostActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText(data.toString());
                    }
                });


            }

            @Override
            public void fail(int errorCode, String errorMsg) {
                Log.i("HttpPostActivity", "errorCode : "+errorCode+"  errorMsg : "+errorMsg);
            }
        });
    }


    private void initData2() {
        String url = "http://v.juhe.cn/toutiao/index";
        String APPKEY = "aa47561558f285fee99f1943c7b844fb";
        Map<String, String> params = new HashMap<>();
        params.put("key", APPKEY);
        params.put("type", "top");

        ServiceApiProvider.helloWorld2(url, params, new HttpCallBack<News>() {
            @Override
            public void success(final News data) {
                Log.e("HttpGetActivity", data.toString());
                HttpPostActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText(data.toString());
                    }
                });

            }

            @Override
            public void fail(int errorCode, String errorMsg) {

            }
        });
    }


}
