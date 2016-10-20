package com.hxkj.cst.cheshuotong;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.activity.BuilddingActivity;
import com.hxkj.cst.cheshuotong.adapter.MainFragmentPagerAdapter;
import com.hxkj.cst.cheshuotong.fragement.HomeFragment;
import com.hxkj.cst.cheshuotong.fragement.PayTaxesFragment;
import com.hxkj.cst.cheshuotong.fragement.XuanhaoFragment;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    /**
     * 界面的导航栏
     */
    @BindView(R.id.mainTabLayout)
    TabLayout mMainTabLayout;
    /**
     * 主界面的viewPager，显示各个fragment
     */
    @BindView(R.id.mainLayout)
    ViewPager mMainLayout;

    /**
     * 主界面的viewPager的适配器
     */
    public MainFragmentPagerAdapter mMainFragmentPagerAdapter;
    public FragmentManager mFragmentManager;
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.iv_login)
    TextView mIvLogin;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.user)
    RelativeLayout user;
    /**
     * 车辆管理
     */
    @BindView(R.id.ll_carManage)
    LinearLayout llCarManage;
    /**
     * 消息中心
     */
    @BindView(R.id.ll_newsCenter)
    LinearLayout llNewsCenter;
    /**
     * 违章记录
     */
    @BindView(R.id.ll_breakLawsRecords)
    LinearLayout llBreakLawsRecords;
    /**
     * 设置中心
     */
    @BindView(R.id.ll_settingCenter)
    LinearLayout llSettingCenter;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(TApplication.app.mUserId)) {
            mIvLogin.setVisibility(View.VISIBLE);
        } else {
            mIvLogin.setVisibility(View.GONE);
        }
    }

    private void init() {
        //添加适配器的fragment数组
        mFragments = new Fragment[3];
        mFragments[0] = new HomeFragment();
        mFragments[1] = new PayTaxesFragment();
        mFragments[2] = new XuanhaoFragment();
        //得到适配器
        mMainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mFragmentManager = getSupportFragmentManager();
        mMainLayout.setAdapter(mMainFragmentPagerAdapter);
        mMainTabLayout.setupWithViewPager(mMainLayout);
        mMainTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mMainLayout.setOffscreenPageLimit(3);
        for (int i = 0; i < mMainTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mMainTabLayout.getTabAt(i);
            tab.setCustomView(mMainFragmentPagerAdapter.getTabView(i));
        }
    }


    private void setListener() {
        /**
         *打开菜单
         */
        mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        /**
         *设置标题
         */
        mMainLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvTitle.setText("车税通");
                        if (TextUtils.isEmpty(TApplication.app.mUserId)) {
                            mIvLogin.setVisibility(View.VISIBLE);
                        } else {
                            mIvLogin.setVisibility(View.GONE);
                        }
                        break;
                    case 1:
                        mTvTitle.setText("缴税订单");
                        mIvLogin.setVisibility(View.GONE);
                        break;
                    case 2:
                        mTvTitle.setText("选号");
                        mIvLogin.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        /**
         * 车辆管理
         */
        llCarManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.show(getApplicationContext(), "即将开放");
                // startActivity(new Intent(getApplicationContext(), CarManageActivitty.class));
                mDrawerLayout.closeDrawers();
            }
        });

        /**
         * 消息中心
         */
        llNewsCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BuilddingActivity.class));
                mDrawerLayout.closeDrawers();
            }
        });

        /***
         * 违章记录
         */
        llBreakLawsRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BuilddingActivity.class));
                mDrawerLayout.closeDrawers();
            }
        });

        /**
         * 设置中心
         */

        llSettingCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        mIvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.show(getApplicationContext(), "即将开放");


                //startActivity(new Intent(MainActivity.this, AccountloginActivity.class));
                mDrawerLayout.closeDrawers();
            }
        });


    }

    private void logOut() {
        String url = ConstKey.LOGOUT + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), MainActivity.this);
                if (retContent == null) {
                    return;
                } else {
                    if (!TextUtils.isEmpty(TApplication.app.mUserId)) {
                        PreferenceUtils.clearPreference(TApplication.app, PreferenceManager
                                .getDefaultSharedPreferences(TApplication.app));
                    }
                    TApplication.app.exit();
                    mDrawerLayout.closeDrawers();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        }, false, false);
    }

    long currentTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // MyToast.show(getApplicationContext(),"2秒内再按一次退出");
            if (new Date().getTime() - currentTime < 2000) {
                System.exit(0);
                return true;
            } else {
                MyToast.show(getApplicationContext(), "2秒内再按一次退出");
                currentTime = new Date().getTime();
            }
        }


        return false;
    }


}
