package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.adapter.Xuanhao_CityAdapter;
import com.hxkj.cst.cheshuotong.adapter.Xuanhao_ProvinceAdapter;
import com.hxkj.cst.cheshuotong.model.Province;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Xuanhao_PlaceLocationActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.recycleview_province)
    UltimateRecyclerView mRecycleviewProvince;
    @Bind(R.id.recycleview_city)
    UltimateRecyclerView mRecycleviewCity;
    @Bind(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;

    List<Province> mProvinceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_location);
        ButterKnife.bind(this);
        getData();
        init();

    }

    /**
     * 得到省市信息
     */
    private void getData() {
        mProvinceList=new ArrayList<>();
        Province pro=new Province();
        pro.setName("北京");
        pro.setMunicipality(true);
        mProvinceList.add(pro);
        Province pro1=new Province();
        pro1.setName("上海");
        pro1.setMunicipality(true);
        mProvinceList.add(pro1);
        Province pro2=new Province();
        pro2.setName("四川");
        pro2.setMunicipality(false);
        List<String> cities=new ArrayList<>();
        cities.add("成都");
        cities.add("南充");
        cities.add("德阳");
        cities.add("自贡");
        pro2.setCities(cities);
        mProvinceList.add(pro2);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        Xuanhao_ProvinceAdapter adpater=new  Xuanhao_ProvinceAdapter(this,mProvinceList);
        mRecycleviewProvince.setLayoutManager(lm);
        mRecycleviewProvince.setAdapter(adpater);
    }

    private void init() {
        //关闭侧边栏滑动打开
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void ShowCities(List<String> cities){
        MyLog.i(cities.toString());
        LinearLayoutManager lm=new LinearLayoutManager(this);
        Xuanhao_CityAdapter adpater=new Xuanhao_CityAdapter(this,cities);
        mRecycleviewCity.setLayoutManager(lm);
        mRecycleviewCity.setAdapter(adpater);
        mDrawerLayout.openDrawer(Gravity.RIGHT);
    }


}
