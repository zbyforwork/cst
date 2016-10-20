package com.hxkj.cst.cheshuotong.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.bean.CarInfoDetail;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarDetailActivity extends ActionBarActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_car_image)
    SimpleDraweeView ivCarImage;
    @BindView(R.id.tv_nation_name)
    TextView tvNationName;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_frame_number)
    TextView tvFrameNumber;
    @BindView(R.id.tv_platetype)
    TextView tvPlatetype;
    @BindView(R.id.tv_plate_number)
    TextView tvPlateNumber;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_alias)
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
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("CLID",id);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i(content);
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                String retContent = ParseReturnUtil.parseRetrun(response.get(), CarDetailActivity.this);
                MyLog.i(retContent);
                if (TextUtils.isEmpty(retContent)){
                    return;
                }
                CarInfoDetail mCarInfoDetail = GsonTools.parserJsonToArrayBean(retContent, "CAR", CarInfoDetail.class);
                showCarInfo(mCarInfoDetail);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
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
