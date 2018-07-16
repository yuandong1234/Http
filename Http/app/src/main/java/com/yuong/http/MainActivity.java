package com.yuong.http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.post).setOnClickListener(this);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.post_file).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post:
                startActivity(new Intent(this, HttpPostActivity.class));
                break;
            case R.id.get:
                startActivity(new Intent(this, HttpGetActivity.class));
                break;
            case R.id.post_file:
                startActivity(new Intent(this, HttpFileActivity.class));
                break;
        }

    }
}
