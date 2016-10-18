package com.hxkj.cst.cheshuotong.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {	
	
	static List<String> existFile=new ArrayList<String>();//�Ѿ����ڵ��ļ��б�
	
	static int currentThreadCount=0;
	
	private File CachDir;

	final String sdCardPath="shangyitong/download";
	
    private LruCache<String, Bitmap> cache;
    public BitmapCache(Context context) {
        cache = new LruCache<String, Bitmap>(100 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        
        
        CachDir=new File(context.getFilesDir(),sdCardPath);
     //   MyLog.i(CachDir.toString());
		if (!CachDir.exists()) {
			CachDir.mkdirs();
		}
		//�����ڵ��ļ������б�
		String[] lists=CachDir.list();
		if (lists!=null) {
			for (String string : lists) {
				existFile.add(string);
			}
			
		}
    }
    

    
    
    @Override
    public Bitmap getBitmap(String url) {
    	Bitmap bitmap=cache.get(url);
    	if (bitmap!=null) {
			
    		return bitmap;
		}
    	
    	//Log.i("yaochuan", url);
    	if (existFile.contains(MyMd5.MD5(url))) {			
			return BitmapFactory.decodeFile(CachDir+"/"+MyMd5.MD5(url));
		}   	
    	
    	return null;
    	
    	
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);//png����
        try {
        	FileOutputStream out=new FileOutputStream(new File(CachDir,MyMd5.MD5(url)));
        	out.write(baos.toByteArray());
        	out.flush();
        	out.close();
        	existFile.add(url.substring(url.lastIndexOf("/")));
			
		} catch (Exception e) {
		e.printStackTrace();
		}
    }
}