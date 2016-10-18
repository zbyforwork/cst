package com.hxkj.cst.cheshuotong.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.OrderAdapter;
import com.hxkj.cst.cheshuotong.bean.PayTaxOrder;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PayTaxesFragment extends Fragment {

    @Bind(R.id.recycleview_order)
    UltimateRecyclerView recycleviewOrder;
    boolean hasShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_taxes, container, false);
        ButterKnife.bind(this, view);
        if (TextUtils.isEmpty(TApplication.app.mUserId)) {
            getData();
        }
        init();
        return view;
    }

    private void init() {
        recycleviewOrder.enableDefaultSwipeRefresh(true);
        recycleviewOrder.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        if (!hasShow && !TextUtils.isEmpty(TApplication.app.mUserId)) {
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
                        final String retContent = ParseReturnUtil.parseRetrun(response, getActivity());
                        if (TextUtils.isEmpty(retContent)) {
                            return;
                        }
                        ArrayList<PayTaxOrder> orders = (ArrayList<PayTaxOrder>) GsonTools.parserJsonToArrayBeans(retContent, "DDLB", PayTaxOrder.class);
                        MyLog.i(orders.toString());
                        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                        OrderAdapter adpater = new OrderAdapter(getActivity(), orders);
                        recycleviewOrder.setLayoutManager(lm);
                        recycleviewOrder.setAdapter(adpater);
                        recycleviewOrder.setRefreshing(false);
                        hasShow = true;
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
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}