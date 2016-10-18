package com.hxkj.cst.cheshuotong.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络检查工具类
 */

public  class CheckIsOpenNet {
	/**
	 * 检查网络是否OK
	 * 
	 * @param act
	 * @return
	 */
	public static boolean isOpenNetwork(Activity act) {
		ConnectivityManager manager = (ConnectivityManager) act
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isAvailable()) {
			return false;
		}
		return true;
	}

}
