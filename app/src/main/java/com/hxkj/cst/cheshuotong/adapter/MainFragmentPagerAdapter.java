package com.hxkj.cst.cheshuotong.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;


/**
 * 主界面viewPager的适配器
 * Created by 刘杨 on 2015/9/8.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"首页", "订单", "选号"};
    private int[] imageResId = {
            R.drawable.home,
            R.drawable.tax,
            R.drawable.select
    };
    private Context mContext;
    /**
     * 主界面4个fragment的数组
     */
    public Fragment[] mFragments;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context, Fragment[] fragments) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  null;
    }
    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(imageResId[position]);
        return view;
    }
}
