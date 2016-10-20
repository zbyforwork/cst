package com.hxkj.cst.cheshuotong.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.adapter.ArchiveContentAdapter;
import com.hxkj.cst.cheshuotong.adapter.ArchiveItemAdapter;
import com.hxkj.cst.cheshuotong.bean.ArchiveCat;
import com.hxkj.cst.cheshuotong.bean.DAXX;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchivesContentActivity extends ActionBarActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.re_top)
    RelativeLayout reTop;
    @BindView(R.id.recycleview)
    UltimateRecyclerView recycleview;
    @BindView(R.id.title)
    TextView title;

    private ArchiveItemAdapter mAdapter;

    List<ArchiveCat> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives_content);
        ButterKnife.bind(this);
        init();
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    String name;
    String id;

    public void init() {
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        title.setText(name);
    }

    private void setLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        //给recyclerview 设置布局管理器
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(id)) {
            getData();
        }
    }

    private void getData() {
        String url = ConstKey.GRDAXX_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("DDID", id);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), ArchivesContentActivity.this);
                if (TextUtils.isEmpty(retContent)) {
                    return;
                }
                ArrayList<DAXX> orders = (ArrayList<DAXX>) GsonTools.parserJsonToArrayBeans(retContent, "GRDAXX", DAXX.class);
                MyLog.i(orders.toString());
                ArchiveContentAdapter adpater = new ArchiveContentAdapter(orders);
                setLayout();
                recycleview.setAdapter(adpater);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }
}
