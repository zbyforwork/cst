package com.baidu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.hxkj.cst.cheshuotong.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luoyang on 2016/10/19. 周边
 */

public class MapActivity extends AppCompatActivity {


    @BindView(R.id.map_back)
    TextView mMapBack;
    @BindView(R.id.map_title)
    TextView mMapTitle;
    @BindView(R.id.map_contain)
    MapView mMapContain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        setUpNavigationBar();
    }

    private void setUpNavigationBar() {
        mMapBack.setOnClickListener(view -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMapContain.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapContain.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapContain.onDestroy();
    }
}
