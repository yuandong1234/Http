package com.app.net.internal.http;

import java.util.Map;

/**
 * Created by yuandong on 2018/7/13.
 */

public interface NameValueMap<K, V> extends Map<K, V> {
    String get(String name);

    void set(String name, String value);

    void setAll(Map<String, String> map);
}
