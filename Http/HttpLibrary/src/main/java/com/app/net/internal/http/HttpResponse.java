package com.app.net.internal.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yuandong on 2018/7/13.
 */

public interface HttpResponse extends Header{

    HttpStatus getStatus();

    String getStatusMsg();

    InputStream getBody() throws IOException;

    long getContentLength();

    void close();
}
