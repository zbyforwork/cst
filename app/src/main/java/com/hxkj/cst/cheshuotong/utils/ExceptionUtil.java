package com.hxkj.cst.cheshuotong.utils;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 所有异常处理类
 * 张钦
 * 2015年8月24日
 */
public class ExceptionUtil {
    private static boolean state = false;

    public static void handleException(Exception e) {
        String str = "";
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        str = stringWriter.toString();
        if (state) {

            //联网发送
        } else {
            MyLog.i(str);
        }

    }


}
