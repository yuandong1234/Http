package com.app.net.internal;

import com.app.net.internal.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by yuandong on 2018/7/13.
 */

public abstract class AbstractHttpResponse implements HttpResponse {

    private static final String GZIP = "gzip";
    private GZIPInputStream mGzipInputStream;

    @Override
    public InputStream getBody() throws IOException {
        InputStream body = getBodyInternal();
        if (isGzip()) {
            return getBodyGzip(body);
        }
        return body;
    }


    @Override
    public void close() {
        if (mGzipInputStream != null) {
            try {
                mGzipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeInternal();
    }


    protected abstract InputStream getBodyInternal();

    protected abstract void closeInternal();


    private boolean isGzip() {
        String contentEncoding = getHeaders().getContentEncoding();
        return GZIP.equals(contentEncoding);
    }

    private InputStream getBodyGzip(InputStream body) {
        if (this.mGzipInputStream == null) {
            try {
                this.mGzipInputStream = new GZIPInputStream(body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mGzipInputStream;
    }
}
