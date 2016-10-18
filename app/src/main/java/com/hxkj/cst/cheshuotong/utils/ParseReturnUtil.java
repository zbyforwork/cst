package com.hxkj.cst.cheshuotong.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.json.JSONObject;

/**
 * 处理服务器返回数据
 * Created by Liuyang on 2015/10/25.
 */
public class ParseReturnUtil {
    /**
     * @param respose 返回数据
     * @param context 上下文对象
     * @return 解析后的数据 注意：如果返回数据错误 返回NULL
     */
    public static @Nullable String parseRetrun(String respose, Context context) {
        try {
            JSONObject dataJSONObject = new JSONObject(respose);
            String retCode = dataJSONObject.getString("retCode");
           if ("0000".equals(retCode)) {
                String retContent = dataJSONObject.getString("retContent");
                return new String(Base64.decode(retContent, 1),"utf-8");
            }else {
                MyToast.showShortMessage(context, dataJSONObject.getString("retMsg"));
                return null;
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
        return null;
    }
}
