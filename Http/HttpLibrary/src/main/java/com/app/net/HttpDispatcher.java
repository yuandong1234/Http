package com.app.net;

import com.app.net.internal.http.HttpRequest;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yuandong on 2018/7/13.
 */

public class HttpDispatcher {
    private static final int MAX_REQUEST_SIZE = 60;

    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {

        private AtomicInteger index = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("http thread name is " + index.getAndIncrement());
            return thread;
        }
    });


    private HttpRequestProvider mRequestProvider;

    private Deque<HttpTask> mRunning = new ArrayDeque<>();

    private Deque<HttpTask> mCache = new ArrayDeque<>();


    private  HttpDispatcher() {
        mRequestProvider = new HttpRequestProvider();
    }


    private static class SingletonHolder {
        private static final HttpDispatcher INSTANCE = new HttpDispatcher();
    }


    public static  HttpDispatcher getInstance() {
        return SingletonHolder.INSTANCE;

    }

    //add the request task to the request task Deque
    public void add(HttpTask task) {

        if (mRunning.size() > MAX_REQUEST_SIZE) {
            mCache.add(task);
        } else {
            doHttpRequest(task);
        }

    }


    //add http request task
    public void doHttpRequest(HttpTask task) {
        HttpRequest httpRequest = null;
        try {
            httpRequest = mRequestProvider.getHttpRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sThreadPool.execute(new HttpRunnable(httpRequest, task, this));
    }


    // remove the request task when the request task completed
    public void finish(HttpTask task) {
        mRunning.remove(task);
        if (mRunning.size() > MAX_REQUEST_SIZE) {
            return;
        }

        if (mCache.size() == 0) {
            return;
        }

        Iterator<HttpTask> iterator = mCache.iterator();

        while (iterator.hasNext()) {
            HttpTask next = iterator.next();
            mRunning.add(next);
            iterator.remove();
            doHttpRequest(next);
        }
    }

}
