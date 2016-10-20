package com.hxkj.cst.cheshuotong.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.jp.wheelview.WheelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayTaxesSix extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_location)
    EditText mEtLocation;
    @BindView(R.id.tv_idcard)
    EditText mTvIdcard;
    @BindView(R.id.tv_address)
    EditText mTvAddress;
    @BindView(R.id.tv_tel)
    EditText mTvTel;
    @BindView(R.id.bt_appointment)
    Button mBtAppointment;
    @BindView(R.id.bt_pay_taxes)
    Button mBtPayTaxes;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_taxes_six);
        ButterKnife.bind(this);
        setListenr();
        mLayoutInflater=LayoutInflater.from(this);
    }

    private void setListenr() {
        mBtPayTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog;
                View extView= mLayoutInflater.inflate(R.layout.layout_appointment,null);
                Button btnCancal= (Button) extView.findViewById(R.id.bt_cancal);
                Button btnSure= (Button) extView.findViewById(R.id.bt_sure);
                final EditText etTel=(EditText)extView.findViewById(R.id.et_tel);
                WheelView address=(WheelView)extView.findViewById(R.id.wv_address);
                ArrayList<String> addressData=new ArrayList<String>();
                addressData.add("红牌楼");
                addressData.add("东门大桥");
               // address.setData(addressData);
                alertDialog=new AlertDialog.Builder(PayTaxesSix.this).setView(extView).show();
                btnCancal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btnSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("".equals(etTel.getText())){
                            MyToast.showShortMessage(PayTaxesSix.this,"请输入电话号码！");
                        }
                        else{
                            MyToast.showShortMessage(PayTaxesSix.this,"！");
                        }
                    }
                });
            }
        });
    }


}
