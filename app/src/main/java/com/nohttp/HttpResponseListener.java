package com.nohttp;

import android.content.Context;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;


public class HttpResponseListener<T> implements OnResponseListener<T> {
    /*自己写一个dialog来显示*/
    //private WaitDialog mWaitDialog;
    /**
     * 当前请求
     */
    private Request<T> mRequest;

    /**
     * 结果回调
     */
    private HttpListener<T> callback;

    /**
     * 是否显示dialog
     */
    private Context context;

    /**
     * @param context      context用来实例化dialog
     * @param request      请求对象
     * @param httpCallback 回调对象
     * @param canCancel    是否允许用户取消请求
     * @param isLoading    是否显示dialog
     */
    HttpResponseListener(Context context, Request<T> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.context = context;
        this.mRequest = request;
        this.callback = httpCallback;
    }

    HttpResponseListener(Context context, Request<T> request, HttpListener<T> httpCallback) {
        this.context = context;
        this.mRequest = request;
        this.callback = httpCallback;
    }

    @Override
    public void onStart(int what) {
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        if (callback != null) {
            callback.onSucceed(what, response);
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        callback.onFailed(what, url, tag, exception.getMessage(), responseCode, networkMillis);
    }

    @Override
    public void onFinish(int what) {
    }
}
