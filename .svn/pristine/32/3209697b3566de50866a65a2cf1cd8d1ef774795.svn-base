package com.hxkj.cst.cheshuotong.utils;




import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


public class MyApplication extends Application {

	private static MyApplication instance;
	private boolean isLogin=false;
	private String userName;
	private String token;
	private String user_id;
	public boolean isShowWelcome;
	private String location;
	private String locationName;
	private String coupon;
	private String integral;
	SharedPreferences share;

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocation() {
		if (location==null) {
			location="104.093377,30.654019";
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// ����ģʽ��ȡΨһ��MyApplicationʵ��
	public static MyApplication getInstance() {
		if (null == instance) {
			instance = new MyApplication();
		}
		return instance;
	}

	@Override
	public void onCreate() {
		
		super.onCreate();
		MyApplication myApplication = MyApplication.getInstance();
		share = getSharedPreferences("user", Context.MODE_PRIVATE);
		if (share.contains("name")) {
			myApplication.changeLoginState(share.getString("name", null));
			myApplication.setToken(share.getString("token", ""));
			myApplication.setUser_id(share.getString("userid", ""));
			
			
		}
		myApplication.setCoupon(share.getString("coupon", "0"));
		myApplication.setIntegral(share.getString("integral", "0"));
	}
	
	public void changeLoginState(String userName) {
		if (userName==null) {
			isLogin=false;
		}else {			
			isLogin=true;
		}
		this.userName=userName;
	}
	
	public boolean getLoginstate() {
		return isLogin;
	}


}
