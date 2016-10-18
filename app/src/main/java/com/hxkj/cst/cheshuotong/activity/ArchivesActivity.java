package com.hxkj.cst.cheshuotong.activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.ArchiveAdapter;
import com.hxkj.cst.cheshuotong.adapter.OrderAdapter;
import com.hxkj.cst.cheshuotong.bean.ArchiveCat;
import com.hxkj.cst.cheshuotong.bean.PayTaxOrder;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.widget.AddArchiveDialog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.divideritemdecoration.HorizontalDividerItemDecoration;
import com.marshalchen.ultimaterecyclerview.divideritemdecoration.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArchivesActivity extends ActionBarActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.recycleview)
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, ArchivesActivity.this);
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
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("YHID", TApplication.app.mUserId);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i("content:-->" + content);
                map.put("content",content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);

    }

    private void setLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        //给recyclerview 设置布局管理器
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(gridLayoutManager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();ButterKnife.unbind(this);

    }
}
