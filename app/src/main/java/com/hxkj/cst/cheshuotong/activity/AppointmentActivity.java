package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.WriterException;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.YYXXAdapter;
import com.hxkj.cst.cheshuotong.bean.JSXX;
import com.hxkj.cst.cheshuotong.bean.YYXX;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.DensityUtils;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.QRGenerate;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.divideritemdecoration.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hugo.android.scanner.CaptureActivity;

public class AppointmentActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.lv_swjg)
    UltimateRecyclerView mLvSwjg;
    private ArrayList<YYXX> mYYXXList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
        init();
        getData();
        TApplication.app.addActivity(this);
    }

    private void init() {

        mYYXXList = new ArrayList<>();
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*ImageView imageView = new ImageView(this);
        Bitmap bitmap = null;
        try {
            bitmap = QRGenerate.createQRCode("sadsadsadasdas", DensityUtils.dp2px(this, 100.0f));
            imageView.setImageBitmap(bitmap);
            AlertView alertView = new AlertView(null, null, null, null, null, this, null, null).setCancelable(true)
                    .addExtView(imageView);
            alertView.show();
        } catch (WriterException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * 得到预约信息
     */
    private void getData() {
        String url = ConstKey.CLYYJS_ADDRESS + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, AppointmentActivity.this);
                        if (retContent == null || retContent.equals("{}")) {
                            return;
                        }
                        mYYXXList = (ArrayList<YYXX>) GsonTools.parserJsonToArrayBeans(retContent, "SWJGXX", YYXX.class);
                        showYYXX();
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
                map.put("CLJSDD", TApplication.app.mSelectXZQHDM);
                map.put("CLLBID", TApplication.app.mCLLBID);
                map.put("CLJSSBID", TApplication.app.mCLJSSBID);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i(content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }

    private void showYYXX() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        YYXXAdapter yyxxAdapter = new YYXXAdapter(this, mYYXXList);
        mLvSwjg.setLayoutManager(layoutManager);
        mLvSwjg.setAdapter(yyxxAdapter);
        mLvSwjg.addItemDecoration(new HorizontalDividerItemDecoration.Builder(AppointmentActivity.this)
                        .sizeResId(R.dimen.yyxx_divider)
                        .colorResId(R.color.white)
                        .build()
        );
    }

}
