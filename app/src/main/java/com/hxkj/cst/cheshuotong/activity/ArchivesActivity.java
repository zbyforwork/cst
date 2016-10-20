package com.hxkj.cst.cheshuotong.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.ArchiveAdapter;
import com.hxkj.cst.cheshuotong.bean.PayTaxOrder;
import com.hxkj.cst.cheshuotong.utils.Base64;
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
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchivesActivity extends ActionBarActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.recycleview)
    UltimateRecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives);
        ButterKnife.bind(this);
       init();



    }

    private void init() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycleview.enableDefaultSwipeRefresh(true);
        recycleview.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!TextUtils.isEmpty(TApplication.app.mUserId)) {
                    getData();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(TApplication.app.mUserId)){
            getData();
        }
    }

    private void getData() {
        String url = ConstKey.GRJSDD_ADDRESS + ParamsBuilder.getParams();
        CallServer callServer = CallServer.getRequestInstance();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("YHID", TApplication.app.mUserId);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content",content);
        request.add(map);
        callServer.add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), ArchivesActivity.this);
                if (TextUtils.isEmpty(retContent)){
                    return;
                }
                ArrayList<PayTaxOrder> orders= (ArrayList<PayTaxOrder>) GsonTools.parserJsonToArrayBeans(retContent, "DDLB", PayTaxOrder.class);
                MyLog.i(orders.toString());
                ArchiveAdapter adpater = new ArchiveAdapter(orders);
                setLayout();
                recycleview.setAdapter(adpater);
                recycleview.setRefreshing(false);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    private void setLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        //给recyclerview 设置布局管理器
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(gridLayoutManager);
    }
}
