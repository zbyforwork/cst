package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.bean.YYXX;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.DensityUtils;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.QRGenerate;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAppointmentActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.iv_ewm)
    ImageView ivEwm;
    String mEwm;
    String mSwjgdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSwjgdm=getIntent().getStringExtra("swjgdm");
        if (!TextUtils.isEmpty(mSwjgdm)) {
            getData();
        }
    }

    /**
     * 得到预约信息
     */
    private void getData() {
        String url = ConstKey.CLYYJS_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("SWJGDM", mSwjgdm);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i(content);
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), ShowAppointmentActivity.this);
                if (retContent == null || retContent.equals("{}")) {
                    return;
                }
                try {
                    JSONObject obj=new JSONObject(retContent);
                    mEwm=obj.getString("EWM");
                    showEwm(mEwm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayList<YYXX> mYYXXList = (ArrayList<YYXX>) GsonTools.parserJsonToArrayBeans(retContent, "SWJGXX", YYXX.class);
                showYYXX(mYYXXList.get(0));
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    private void showEwm(String mEwm) {
        Bitmap bitmap = null;
        try {
            bitmap = QRGenerate.createQRCode(mEwm, DensityUtils.dp2px(ShowAppointmentActivity.this, 200.0f));
            ivEwm.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void showYYXX(YYXX yyxx) {
        tvName.setText(yyxx.getJGMC());
        tvAddress.setText(yyxx.getJGDZ());
        tvAddress.setText(yyxx.getLXDH());
    }
}
