package com.hxkj.cst.cheshuotong.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class PayTaxesFragment extends Fragment {

    @BindView(R.id.recycleview_order)
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
        CallServer callServer = CallServer.getRequestInstance();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("YHID", TApplication.app.mUserId);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        request.add(map);
        callServer.add(getActivity(), 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), getActivity());
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

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        }, false, false);

    }
}