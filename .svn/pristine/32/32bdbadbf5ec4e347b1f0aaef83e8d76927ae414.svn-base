package com.hxkj.cst.cheshuotong.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 生成服务器需要的参数 key
 * Created by 刘杨 on 2015/10/27 13:55.
 */
public class ParamsBuilder {
    /**
     * @param time 当前时间
     * @return
     */
    public static String getKey(long time) {
        String keyStr = "CSTAPP" + time;
        String key = MyMd5.MD5(keyStr);
        return key;
    }

    /**
     * 得到 time 和 key 参数
     * @return
     */
    public static String getParams(){
        long curentTime=new Date().getTime();
        return "&time="+curentTime+"&key="+getKey(curentTime);
    }
    /**把数据源HashMap转换成json
     * @param map
     */
    public static String hashMapToJson(HashMap map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry) it.next();
            string += "\"" + e.getKey() + "\":";
            string += "\"" + e.getValue() + "\",";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }
}
