package com.hxkj.cst.cheshuotong;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hxkj.cst.cheshuotong.activity.AccountloginActivity;
import com.hxkj.cst.cheshuotong.bean.CLXX;
import com.hxkj.cst.cheshuotong.bean.JSXX;
import com.hxkj.cst.cheshuotong.bean.SWJG;
import com.hxkj.cst.cheshuotong.bean.User;
import com.hxkj.cst.cheshuotong.bean.XZQH;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.ExceptionUtil;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.hxkj.cst.cheshuotong.utils.PreferencesCookieStore;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 全局application类
 * 2015年10月15日15:31:31
 */
public class TApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static final Object TAG = "VolleyPatterns";
    public static TApplication app = null;
    public Map<Object, Object> cache;
    public Map<Object, Object> clearCache;
    public RequestQueue mRequestQueue;
    public Handler mHandler;
    public List<XZQH> mXZQHList;
    public List<CLXX> mCLXXList;
    public List<SWJG> mSWJGList;
    public int mXZQHBBH, mCLLBBBH, mSWJGBBH;
    public boolean initDataSuccess; //初始化数据是否成功
    public String mSelectXZQHDM;  //选择的缴税地点代码
    public String mHGZMX;  //扫描获得的合格证条码
    public String mSBBXX;  //扫描获得的申报表条码
    public String mCLLBID; //车辆类别ID
    public String mCLJSSBID; //车辆缴税申报ID
    public String mUserId; //用户id
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        cache = new HashMap<>();
        clearCache = new HashMap<>();
        //  Thread.setDefaultUncaughtExceptionHandler(this);
        Fresco.initialize(this);
        getCookie();
        loadLocalData();
    }


    /**
     * 加载本地数据
     */
    private void loadLocalData() {
        mUserId = PreferenceUtils.getPrefString(this, "UserID", null);
        String phone = PreferenceUtils.getPrefString(this, "UserPhone", null);
        String password = PreferenceUtils.getPrefString(this, "UserPassword", null);
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
            login(phone, password);
        }
    }

    /**
     * 登陆验证
     *
     * @param phone    手机号
     * @param password 密码
     */
    private void login(final String phone, final String password) {
        String url = ConstKey.LOGIN + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, TApplication.this);
                        if (retContent == null) {
                            return;
                        } else {
                            User user = GsonTools.parserJsonToArrayBean(retContent, User.class);
                            PreferenceUtils.setPrefString(TApplication.app, "UserID", user.getID());
                            PreferenceUtils.setPrefString(TApplication.app, "UserPhone", user.getPHONE());
                            PreferenceUtils.setPrefString(TApplication.app, "UserPassword", user.getPASSWORD());
                            TApplication.app.mUserId = user.getID();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", phone);
                map.put("mtype", "android");
                map.put("password", password);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i("content:-->" + content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }

    /**
     * 获取服务器cookie
     */
    public void getCookie() {
        String url = ConstKey.LOGIN;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }
   /* *//**
     * 向服务器发送本地数据版本号，更新数据
     *//*
    private void updateData() {
        //得到本地数据的版本号
        mXZQHBBH = PreferenceUtils.getPrefInt(this, "XZQHBBH", 0);
        mCLLBBBH = PreferenceUtils.getPrefInt(this, "CLLBBBH", 0);
        mSWJGBBH = PreferenceUtils.getPrefInt(this, "SWJGBBH", 0);
        String content = "{\"BBH\":\"" + mXZQHBBH + "," + mSWJGBBH + "," + mCLLBBBH + "\"}";
        MyLog.i(content);
        String url = ConstKey.UPDATE_FWQ_DATA + ParamsBuilder.getParams() + "&content=" + Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
        MyLog.i(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            MyLog.i(response);
                            //base64解码返回信息
                            String retContent = ParseReturnUtil.parseRetrun(response, TApplication.this);
                            //如果retContent为空
                            if (null == retContent) {
                                return;
                            }
                            MyLog.i(retContent);
                            JSONObject retObj = new JSONObject(retContent);
                            JSONObject xzqhObj=retObj.getJSONObject("XZQH");
                            if (!xzqhObj.toString().equals("{}")) {
                                PreferenceUtils.setPrefInt(TApplication.this, "XZQHBBH", xzqhObj.getInt("FWQBBH"));
                                PreferenceUtils.setPrefString(TApplication.this, "XZQH", xzqhObj.toString());
                                mXZQHList.addAll(GsonTools.parserJsonToArrayBeans(xzqhObj.toString(), "XZQHLB", XZQH.class));
                            }
                            JSONObject clxxObj=retObj.getJSONObject("CLLB");
                            if (!clxxObj.toString().equals("{}")) {
                                PreferenceUtils.setPrefInt(TApplication.this, "CLLBBBH", clxxObj.getInt("FWQBBH"));
                                PreferenceUtils.setPrefString(TApplication.this, "CLLB", clxxObj.toString());
                                mCLXXList.addAll(GsonTools.parserJsonToArrayBeans(clxxObj.toString(), "CLLBXX", CLXX.class));
                            }
                            JSONObject swjgObj=retObj.getJSONObject("SWJG");
                            if (!swjgObj.toString().equals("{}")) {
                                PreferenceUtils.setPrefInt(TApplication.this, "SWJGBBH", swjgObj.getInt("FWQBBH"));
                                PreferenceUtils.setPrefString(TApplication.this, "SWJG", swjgObj.toString());
                                mSWJGList.addAll(GsonTools.parserJsonToArrayBeans(swjgObj.toString(), "SWJGXX", SWJG.class));
                            }
                            initDataSuccess=true;
                            Intent intent = new Intent(
                                    ConstKey.ACTION_UPDATE_SUCCESS);
                            sendBroadcast(intent);
                        } catch (Exception e) {
                            ExceptionUtil.handleException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        TApplication.app.addToRequestQueue(stringRequest);
    }*/


    /**
     * 懒汉式生成Request
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            //非持久化存储(内存存储) BasicCookieStore | 持久化存储 PreferencesCookieStore
            CookieStore cookieStore = new PreferencesCookieStore(this);
            httpclient.setCookieStore(cookieStore);
            HttpStack httpStack = new HttpClientStack(httpclient);
            mRequestQueue = Volley.newRequestQueue(app, httpStack);
        }
        return mRequestQueue;
    }

    /**
     * 添加任意request(带tag)
     *
     * @param req 请求
     * @param tag Tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * 添加request(无tag)
     *
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * 取消所有请求
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * 存放所以打开的activity的list
     */
    private List<Activity> activityList = new LinkedList<Activity>();


    /**
     * 往集合添加activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    /**
     * 结束所有的activity
     */
    public void exitPayTax() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束所有的activity
     */
    public void exit() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        int id = android.os.Process.myPid();
        if (id != 0) {
            android.os.Process
                    .killProcess(id);
        }
        System.exit(0);
    }

    /**
     * 处理try catch 无法捕捉的异常等等
     * APP 异常崩溃,退出需要做的事.
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        MyLog.e("Application抛出的异常:" + ex.toString(), ex);
        cache.clear();
        SystemClock.sleep(1500);
        //        exit();
        //        app = null;
    }
}
