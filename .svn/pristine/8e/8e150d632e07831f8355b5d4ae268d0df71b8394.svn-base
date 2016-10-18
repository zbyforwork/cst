package com.hxkj.cst.cheshuotong.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class GetVersion {
	
	
	/**
	 * @param context
	 * @return ���ذ汾��+�汾��
	 */
	public static int getAppVersionCode(Context context) {  
	    int versionName = 0;  
	    try {  
	        // ---get the package info---  
	        PackageManager pm = context.getPackageManager();  
	        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);  
	        versionName =pi.versionCode; 
	      
	    } catch (Exception e) {  
	        Log.e("VersionInfo", "Exception", e);  
	    }  
	    return versionName;  
	}  

}
