package com.hxkj.cst.cheshuotong;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hxkj.cst.cheshuotong.bean.CLXX;
import com.hxkj.cst.cheshuotong.bean.SWJG;
import com.hxkj.cst.cheshuotong.bean.User;
import com.hxkj.cst.cheshuotong.bean.XZQH;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

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
        NoHttp.initialize(this);
        Fresco.initialize(this);
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
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("mtype", "android");
        map.put("password", password);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), TApplication.this);
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

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
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
    }
}
