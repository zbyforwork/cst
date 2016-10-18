package com.hxkj.cst.cheshuotong.activity;
import java.text.DecimalFormat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.utils.BitmapCache;
import com.hxkj.cst.cheshuotong.widget.TouchImageView;


import android.app.Activity;
import android.graphics.PointF;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;




public class SingleTouchImageViewActivity extends Activity {
	
	private TouchImageView image;
	private DecimalFormat df;
	private  RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_touchimageview);
		//
		// DecimalFormat rounds to 2 decimal places.
		//
		mQueue = Volley.newRequestQueue(this);	
		
	
		
		image = (TouchImageView) findViewById(R.id.img);
		String src=getIntent().getExtras().getString("src");
		image.setImageURI(Uri.parse("file://x/"+ src ));


		//Toast.makeText(this, src, Toast.LENGTH_LONG).show();
//		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache(getApplicationContext()));
//		ImageListener listener = ImageLoader.getImageListener(image,
//				R.drawable.ic_launcher, R.drawable.ic_launcher);
//		String imgUrl ="file://x/"+ src ;
//		imageLoader.get(imgUrl, listener);
		
		image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SingleTouchImageViewActivity.this.finish();
				
			}
		});

	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
}
