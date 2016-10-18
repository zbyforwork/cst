package com.hxkj.cst.cheshuotong.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.CarInfoAdapter;
import com.hxkj.cst.cheshuotong.bean.CarInfoDetail;
import com.hxkj.cst.cheshuotong.bean.CarInfoSimple;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CarDetailActivity extends ActionBarActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_car_image)
    SimpleDraweeView ivCarImage;
    @Bind(R.id.tv_nation_name)
    TextView tvNationName;
    @Bind(R.id.tv_brand_name)
    TextView tvBrandName;
    @Bind(R.id.tv_frame_number)
    TextView tvFrameNumber;
    @Bind(R.id.tv_platetype)
    TextView tvPlatetype;
    @Bind(R.id.tv_plate_number)
    TextView tvPlateNumber;
    @Bind(R.id.tv_model)
    TextView tvModel;
    @Bind(R.id.tv_create_time)
    TextView tvCreateTime;
    @Bind(R.id.tv_alias)
    TextView tvAlias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        ButterKnife.bind(this);
        getData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        final String id = getIntent().getStringExtra("ID");
        String url = ConstKey.GET_CAR_DETAIL_ADDRESS + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        String retContent = ParseReturnUtil.parseRetrun(response, CarDetailActivity.this);
                        MyLog.i(retContent);
                        if (TextUtils.isEmpty(retContent)){
                            return;
                        }
                        CarInfoDetail mCarInfoDetail = GsonTools.parserJsonToArrayBean(retContent, "CAR", CarInfoDetail.class);
                        showCarInfo(mCarInfoDetail);
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("CLID",id);
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

    /**
     * 展示数据
     * @param mCarInfoDetail
     */
    private void showCarInfo(CarInfoDetail mCarInfoDetail) {
        ivCarImage.setImageURI(Uri.parse(ConstKey.IMGAE_ROOT+mCarInfoDetail.getBIGIMG()));
        tvNationName.setText(mCarInfoDetail.getNATIONNAME() + "");
        tvBrandName.setText(mCarInfoDetail.getBRANDNAME()+"");
        tvFrameNumber.setText(mCarInfoDetail.getFRAMENUM()+"");
        tvPlatetype.setText(mCarInfoDetail.getPLATETYPE()+"");
        tvPlateNumber.setText(mCarInfoDetail.getPLATENUMBER()+"");
        tvModel.setText(mCarInfoDetail.getMODELS()+"");
        tvCreateTime.setText(mCarInfoDetail.getCREATETIME()+"");
        tvAlias.setText(mCarInfoDetail.getALIAS()+"");
    }

}
