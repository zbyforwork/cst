package com.hxkj.cst.cheshuotong.adapter;




import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hxkj.cst.cheshuotong.MainActivity;


public class MyWebViewClient extends WebViewClient{

	Dialog dialog=null;
	static boolean firsttime=true;
	String tempurl;
 
	@Override
	public void onPageFinished(WebView view, String url) {
		if (firsttime) {
			firsttime=false;
			view.loadUrl(tempurl);
		}
		try {
			if (dialog!=null&&dialog.isShowing()) {
				dialog.dismiss();
				dialog=null;
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		super.onPageFinished(view, url);
		if (url.indexOf("login_out.php")!=-1) {
			view.loadUrl("<script>history.go(-1)</script>");
//			Intent intent = new Intent(context,MainActivity.class);            	 
//			context.startActivity(intent);  
			context.finish();
		}
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// TODO Auto-generated method stub


		super.onPageStarted(view, url, favicon);
	}
	private  Activity context;
	public MyWebViewClient(Activity context){
		this.context=context;
	}
	
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			 view.stopLoading();
         	
			 if (failingUrl.indexOf("alipay")==-1) {
				
				 Toast.makeText(context, "����ʧ��,������������", Toast.LENGTH_LONG).show();
				 Intent intent = new Intent(context,MainActivity.class);
				 context.startActivity(intent);
			}
           	//view.loadUrl("file:///android_asset/index.htm");
			//super.onReceivedError(view, errorCode, description, failingUrl);
		}
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			

        	//Toast.makeText(view.getContext(), "comming", Toast.LENGTH_LONG).show();
            if (url.startsWith("mailto:") || url.startsWith("geo:") ||url.startsWith("tel:")||url.startsWith("sms:")||url.startsWith("mqqwpa:")) {
              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//              MyLog.i(url);
             // Toast.makeText(view.getContext(), "comming", Toast.LENGTH_LONG).show();
               context.startActivity(intent);
               return true;  
              }

            if (url.indexOf("default")!=-1) {
            	Intent intent = new Intent(context,MainActivity.class);   
            	intent.putExtra("action", 0);
            	context.startActivity(intent);
            	
            	return true;
            }
            if (url.indexOf("category")!=-1) {
            	Intent intent = new Intent(context,MainActivity.class);  
            	intent.putExtra("action", 1);
            	context.startActivity(intent);
            	
            	return true;
            }
           /* if (url.indexOf("member")!=-1) {
            	Intent intent = new Intent(context,MainActivity.class);    
            	intent.putExtra("action", 4);
            	context.startActivity(intent);
            	context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            	return true;
            }*/

            if (url.indexOf("logout")!=-1) {            	
            	return true;
            }

           
          
            view.loadUrl(url);
            return true;  
			
		}
		
		
	}