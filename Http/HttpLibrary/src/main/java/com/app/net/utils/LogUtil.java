package com.app.net.utils;

import android.util.Log;

/**
 * Created by yuandong on 2018/7/13.
 */

public class LogUtil {

    public static boolean DEBUG = true;

    private LogUtil() {
        throw new UnsupportedOperationException("can out  instantiate ...");
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
