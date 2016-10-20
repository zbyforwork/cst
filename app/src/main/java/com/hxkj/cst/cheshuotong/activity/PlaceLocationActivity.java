package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.adapter.CityAdapter;
import com.hxkj.cst.cheshuotong.adapter.ProvinceAdapter;
import com.hxkj.cst.cheshuotong.bean.XZQH;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceLocationActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.recycleview_province)
    UltimateRecyclerView mRecycleviewProvince;
    @BindView(R.id.recycleview_city)
    UltimateRecyclerView mRecycleviewCity;
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;

    List<XZQH> mProvinceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_location);
        ButterKnife.bind(this);
        getData();

    }

    /**
     * 得到省市信息
     */
    private void getData() {
        mProvinceList=new ArrayList<>();
        String url = ConstKey.GET_XZQHLB_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), PlaceLocationActivity.this);
                if (retContent == null) {
                    return;
                } else {
                    MyLog.i(retContent);
                    mProvinceList =  GsonTools.parserJsonToArrayBeans(retContent, "XZQHLB", XZQH.class);
                    init();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    private void init() {
        LinearLayoutManager lm=new LinearLayoutManager(this);
        ProvinceAdapter adpater=new ProvinceAdapter(this,mProvinceList);
        mRecycleviewProvince.setLayoutManager(lm);
        mRecycleviewProvince.setAdapter(adpater);
        //关闭侧边栏滑动打开
       // mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void ShowCities(List<XZQH> cities){
        MyLog.i(cities.toString());
        LinearLayoutManager lm=new LinearLayoutManager(this);
        CityAdapter adpater=new CityAdapter(this,cities);
        mRecycleviewCity.setLayoutManager(lm);
        mRecycleviewCity.setAdapter(adpater);
        mDrawerLayout.openDrawer(Gravity.RIGHT);
    }


}
