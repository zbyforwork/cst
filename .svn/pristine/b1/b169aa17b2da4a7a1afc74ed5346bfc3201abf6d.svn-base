package com.hxkj.cst.cheshuotong.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.activity.PayTaxesSix;
import com.jp.wheelview.WheelView;

import java.util.ArrayList;

/**
 * Created by 刘杨 on 2015/10/23 15:30.
 */
public class MyCustomeDialog extends AlertDialog {
    public MyCustomeDialog(Context context) {
        super(context);
    }

    public MyCustomeDialog(Context context, int theme) {
        super(context, theme);
    }

    public MyCustomeDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_appointment);
        Button btnCancal= (Button)findViewById(R.id.bt_cancal);
        WheelView address=(WheelView)findViewById(R.id.wv_address);
        ArrayList<String> addressData=new ArrayList<String>();
        addressData.add("红牌楼");
        addressData.add("东门大桥");
        //address.setData(addressData);
        btnCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
