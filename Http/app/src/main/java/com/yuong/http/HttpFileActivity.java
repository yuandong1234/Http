package com.yuong.http;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.net.callback.HttpCallBack;
import com.yuong.http.entity.BaseResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;

public class HttpFileActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mContent;
    private ImageView imageView;

    private static final int PHOTO_REQUEST_GALLERY = 1;// 从相册中选择
    private String filePath;
    private Bitmap bitmap;
    private String url = "http://192.168.87.228:8080/Web/UploadFileServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_file);

        mContent = findViewById(R.id.content);
        imageView = findViewById(R.id.image);
        findViewById(R.id.pick_photo).setOnClickListener(this);
        findViewById(R.id.upload).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pick_photo:
                gallery();
                break;
            case R.id.upload:
                upload();
                break;
        }
    }

    /**
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    private void upload() {
        if (TextUtils.isEmpty(filePath)) {
            Toast.makeText(this, "选择文件图片为空", Toast.LENGTH_SHORT).show();
            return;
        }
        ServiceApiProvider.helloWorld3(url, new File(filePath), new HttpCallBack<BaseResponse>() {
                    @Override
                    public void success(final BaseResponse data) {
                        HttpFileActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mContent.setText(data.toString());
                            }
                        });
                    }

                    @Override
                    public void fail(int errorCode, String errorMsg) {

                    }
                }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_GALLERY:
                    if (data != null) {
                        Uri uri = data.getData();
                        Log.e("HttpFileActivity", uri.toString());

                        String[] column = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(uri, column, null, null, null);//从系统表中查询指定Uri对应的照片
                        if (cursor != null) {
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(column[0]);
                            String path = cursor.getString(columnIndex);  //获取照片路径
                            Log.e("HttpFileActivity", path);
                            filePath = path;
                            cursor.close();
                            bitmap = BitmapFactory.decodeFile(path);
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            bitmap.recycle();
        }
    }
}
