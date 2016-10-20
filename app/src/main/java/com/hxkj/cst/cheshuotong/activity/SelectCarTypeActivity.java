package com.hxkj.cst.cheshuotong.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.CarTypeSortAdapter;
import com.hxkj.cst.cheshuotong.adapter.PPCLXXAdapter;
import com.hxkj.cst.cheshuotong.bean.CLPP;
import com.hxkj.cst.cheshuotong.bean.CLXX;
import com.hxkj.cst.cheshuotong.bean.PPCLXX;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.CharacterParser;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PinyinComparator;
import com.hxkj.cst.cheshuotong.utils.SideBar;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCarTypeActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.recycleview_clxx)
    UltimateRecyclerView mRecycleviewClxx;
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    private ListView sortListView;
    private SideBar sideBar;
    private CarTypeSortAdapter adapter;


    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<CLPP> mSourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private ArrayList<CLXX> mCLXXArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car_type);
        ButterKnife.bind(this);
        getData();
        TApplication.app.addActivity(this);
    }

    private void initViews() {
        sideBar = (SideBar) findViewById(R.id.sidrbar);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sortListView = (ListView) findViewById(R.id.car_name);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
               /* TApplication.app.mCLLBID = ((CLXX) adapter.getItem(position)).getID() + "";
                Intent intent = new Intent(SelectCarTypeActivity.this, PayTaxesSeven.class);
                intent.putExtra("image", ((CLXX) adapter.getItem(position)).getCLTPDZ());
                startActivity(intent);*/
                ShowPPXX((CLPP) adapter.getItem(position));
            }
        });
        adapter = new CarTypeSortAdapter(this, mSourceDateList);
        sortListView.setAdapter(adapter);
    }

    /**
     * 得到车辆信息
     */
    private void getData() {
        String url = ConstKey.GET_CLPPLB_ADDRESS + ParamsBuilder.getParams();
        MyLog.i(url);
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                String retContent = ParseReturnUtil.parseRetrun(response.get(), SelectCarTypeActivity.this);
                if (null == retContent) {
                    return;
                }
                MyLog.i(retContent);
                try {
                    JSONObject object = new JSONObject(retContent);
                    JSONArray jsonArray = object.getJSONArray("CLPPLB");
                    //TApplication.app.mCLJSSBID = object.getString("JSSBID");
                    parseCLXX(jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    private void parseCLXX(JSONArray jsonArray) throws JSONException {
        mSourceDateList = new ArrayList<>();
        CLPP clpp;
        for (int i = 0; i < jsonArray.length(); i++) {
            clpp = new CLPP();
            JSONObject obj = jsonArray.getJSONObject(i);
            clpp.setPPID(obj.getString("PPID"));
            clpp.setPPMC(obj.getString("PPMC"));
            clpp.setPPTB(obj.getString("PPTB"));
            clpp.setPPSZM(obj.getString("PPSZM"));
            mSourceDateList.add(clpp);
        }
        MyLog.i(mSourceDateList.toString());
        MyLog.i(mSourceDateList.get(mSourceDateList.size() - 1).toString());
        setSortSetter();
        initViews();
    }

    /**
     * 为车型排序并设置sortSetter;
     */
    private void setSortSetter() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        // 根据a-z进行排序源数据
        Collections.sort(mSourceDateList, pinyinComparator);
        /*for (int i = 0; i < mSourceDateList.size(); i++) {
            CLPP clxx = mSourceDateList.get(i);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(clxx.getPPMC());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                clxx.setSortLetters(sortString.toUpperCase());
            } else {
                clxx.setSortLetters("#");
            }
        }*/
    }
    public void ShowPPXX(final CLPP clpp){
        MyLog.i(clpp.toString());
        String url = ConstKey.GET_CLPPCLXX_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("CLPPID",clpp.getPPID());
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i(content);
        map.put("content",content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                String retContent = ParseReturnUtil.parseRetrun(response.get(), SelectCarTypeActivity.this);
                if (null == retContent) {
                    return;
                }
                MyLog.i(retContent);
                ArrayList<PPCLXX> ppclxxes = (ArrayList<PPCLXX>) GsonTools.parserJsonToArrayBeans(retContent,"CLLB" ,PPCLXX.class);
                MyLog.i(ppclxxes.toString());
                PPCLXXAdapter adpater = new PPCLXXAdapter(SelectCarTypeActivity.this, ppclxxes);
                LinearLayoutManager lm=new LinearLayoutManager(SelectCarTypeActivity.this);
                mRecycleviewClxx.setLayoutManager(lm);
                mRecycleviewClxx.setAdapter(adpater);
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }
}
