package com.hxkj.cst.cheshuotong;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomActivtiy extends Activity {

    @BindView(R.id.convenientBanner)
    ViewPager convenientBanner;

    SimpleDraweeView[] mImageView = null;

    List<Integer> localImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_activtiy);
        ButterKnife.bind(this);
        initBanner();
    }

    /**
     * 2015-10-13 12:19:31
     */
    private void initBanner() {
        localImages.add(R.drawable.welcomone);
        localImages.add(R.drawable.welcomtwo);
        localImages.add(R.drawable.welcomthree);

        mImageView = new SimpleDraweeView[localImages.size()];

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < localImages.size(); i++) {
            mImageView[i] = new SimpleDraweeView(getApplicationContext());
            mImageView[i].setImageURI(Uri.parse("res://x/" + localImages.get(i)));
            mImageView[i].setScaleType(ImageView.ScaleType.FIT_XY);
            mImageView[i].setLayoutParams(params);
        }
        mImageView[localImages.size() - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomActivtiy.this, MainActivity.class));
                finish();
            }
        });

        MyAdapter myAdapter = new MyAdapter();
        convenientBanner.setAdapter(myAdapter);
    }


    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return localImages.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(mImageView[position
                    % mImageView.length]);

        }

        public Object instantiateItem(View container, int position) {

            ((ViewPager) container).addView(mImageView[position], 0);
            return mImageView[position];

        }

    }


}
