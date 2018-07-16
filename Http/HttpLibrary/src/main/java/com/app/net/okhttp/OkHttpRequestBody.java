package com.app.net.okhttp;

import com.app.net.internal.http.ContentType;
import com.app.net.internal.http.HttpMethod;
import com.app.net.utils.LogUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by yuandong on 2018/7/13.
 */

public class OkHttpRequestBody {

    private static final String TAG = OkHttpRequestBody.class.getSimpleName();

    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private static final MediaType STREAM = MediaType.parse("application/octet-stream");
    private static final MediaType PNG = MediaType.parse("image/png");

    private RequestBody mRequestBody;
    private String mUrl;

    private OkHttpRequestBody(Builder builder) {
        this.mRequestBody = builder.mRequestBody;
        this.mUrl = builder.mUrl;
    }

    public RequestBody getRequestBody() {
        return mRequestBody;
    }

    public String getUrl() {
        return mUrl;
    }

    public static class Builder {
        private ContentType mContentType;
        private HttpMethod mMethod;
        private String mJson;
        private Map<String, String> mParams;
        private List<File> mFlies;
        private File mFile;
        private RequestBody mRequestBody;
        private String mUrl;

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder contentType(ContentType contentType) {
            this.mContentType = contentType;
            return this;
        }

        public Builder method(HttpMethod method) {
            this.mMethod = method;
            return this;
        }

        public Builder json(String json) {
            this.mJson = json;
            return this;
        }

        public Builder params(Map<String, String> params) {
            this.mParams = params;
            return this;
        }

        public Builder flies(List<File> flies) {
            this.mFlies = flies;
            return this;
        }

        public Builder file(File file) {
            this.mFile = file;
            return this;
        }

        public OkHttpRequestBody Build() {
            String urlParams = null;
            if (mMethod == HttpMethod.GET) {
                if (mParams != null && mParams.size() > 0) {
                    mUrl = appendParams(mUrl, mParams);
                    urlParams = mUrl;
                }
            } else {
                if (mContentType == ContentType.FORM) {
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    if (mParams != null && mParams.size() > 0) {
                        for (Map.Entry<String, String> entry : mParams.entrySet()) {
                            bodyBuilder.add(entry.getKey(), entry.getValue());
                        }
                    }
                    mRequestBody = bodyBuilder.build();
                    urlParams = appendParams(mUrl, mParams);
                } else if (mContentType == ContentType.JSON) {
                    if (mJson != null && !mJson.equals("")) {
                        mRequestBody = RequestBody.create(JSON, mJson);
                        urlParams = mUrl + "?" + mJson;
                    }
                } else if (mContentType == ContentType.MULTIPART) {
                    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (mParams != null && mParams.size() > 0) {
                        for (Map.Entry<String, String> entry : mParams.entrySet()) {
                            bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
                        }
                    }
                    if (mFlies != null && mFlies.size() > 0) {
                        for (int i = 0; i < mFlies.size(); i++) {
                            File file = mFlies.get(i);
                            bodyBuilder.addFormDataPart("image" + i, file.getName(), RequestBody.create(PNG, file));
                            LogUtil.i(TAG, file.getName() + " : " + file.getAbsolutePath());
                        }
                    }
                    urlParams = appendParams(mUrl, mParams);
                    mRequestBody = bodyBuilder.build();
                } else if (mContentType == ContentType.STREAM) {
                    if (mFile != null) {
                        mRequestBody = RequestBody.create(STREAM, mFile);
                        LogUtil.i(TAG, mFile.getName() + " : " + mFile.getAbsolutePath());
                    }
                }
            }

            LogUtil.i(TAG, "HttpMethod : " + mMethod + "\n" +
                    "ContentType : " + (mContentType != null ? mContentType.getValue() : "") + "\n" +
                    "URL : " + mUrl + "\n" +
                    "URL PARAMS : " + urlParams);
            return new OkHttpRequestBody(this);
        }

    }

    private static String appendParams(String url, Map<String, String> params) {
        if (url == null || url.equals("")) return null;
        if (params == null || params.size() == 0) return url;
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        if (!params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
