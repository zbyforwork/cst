package com.hxkj.cst.cheshuotong.utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Zip解压缩
 * Created by 刘杨 on 2015/10/23 17:55.
 */
public class ZipDecode {
    /**
     * @param bytes 压缩后的字节组
     * @return
     */
    public static String decode(byte[] bytes) {
        ZipInputStream Zin = new ZipInputStream(new ByteArrayInputStream(bytes));
        ZipEntry entry;
        try {
            entry = Zin.getNextEntry();
            ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
            int ch;
            while((ch = Zin.read() ) != -1){
                bAOutputStream.write(ch);
            }
            String data = bAOutputStream.toString();
            bAOutputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
