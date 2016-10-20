package com.hxkj.cst.cheshuotong.fragement;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.MapActivity;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.activity.AddCarActivity;
import com.hxkj.cst.cheshuotong.activity.ArchivesActivity;
import com.hxkj.cst.cheshuotong.activity.BreakRuleActivity;
import com.hxkj.cst.cheshuotong.activity.PayTaxesActivity;
import com.hxkj.cst.cheshuotong.adapter.HomeCBAdapter;
import com.hxkj.cst.cheshuotong.adapter.HomeUrecyclerAdapter;
import com.hxkj.cst.cheshuotong.bean.CardBean;
import com.hxkj.cst.cheshuotong.utils.ScreenUtils;
import com.hxkj.cst.cheshuotong.widget.CustomIcon;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luoyang on 2016/10/10. 主页内容
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.home_ultimaterecyclerview)
    UltimateRecyclerView mHomeUltimaterecyclerview;

    ConvenientBanner mConvenientBanner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setUpHeaderAndInitRecycler();
        return view;
    }

    private void setUpHeaderAndInitRecycler() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_header,
                mHomeUltimaterecyclerview, false);
        mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.convenient_banner);
        /*适配广告栏高度*/
        setBannerSize();
        bindingConvenientBannerData();
        setHomeIconListener(view);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        lm.setSmoothScrollbarEnabled(true);


        mHomeUltimaterecyclerview.setLayoutManager(lm);
        //初始化 测试数据
        SparseArray<CardBean> cardBeanSparseArray = new SparseArray<>();
        for (int i = 0; i < 25; i++) {
            CardBean cardBean = new CardBean("CardNick" + i, "CardModel" + i, "CardNumber" + i);
            cardBeanSparseArray.append(i, cardBean);
        }
        HomeUrecyclerAdapter homeUrecyclerAdapter = new HomeUrecyclerAdapter(
                getActivity(),cardBeanSparseArray);
        mHomeUltimaterecyclerview.setAdapter(homeUrecyclerAdapter);
        mHomeUltimaterecyclerview.setRefreshing(false);

        mHomeUltimaterecyclerview.setParallaxHeader(view);

        mHomeUltimaterecyclerview.enableDefaultSwipeRefresh(true);
        mHomeUltimaterecyclerview.setDefaultOnRefreshListener(() ->
                //下拉刷新
                mHomeUltimaterecyclerview.setRefreshing(false));
    }

    private void setBannerSize() {
        //设置banner的宽高 16:6.5
        int witdth = ScreenUtils.getScreenWidth(getActivity());
        ViewGroup.LayoutParams lp = mConvenientBanner.getLayoutParams();
        lp.width = witdth;
        lp.height = witdth * 130 / 320;
        mConvenientBanner.setLayoutParams(lp);
    }

    private void setHomeIconListener(View view) {
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mHomeIconCircum.setOnClickListener(view1 -> {
            //周边钮
            startActivity(new Intent(getActivity(), MapActivity.class));
        });

        viewHolder.mHomeIconCard.setOnClickListener(view1 -> {
            //添加车辆
            startActivity(new Intent(getActivity(), AddCarActivity.class));
        });

        viewHolder.mHomeIconPeccancy.setOnClickListener(view1 -> {
            //违章记录
            startActivity(new Intent(getActivity(), BreakRuleActivity.class));
        });

        viewHolder.mHomeIconArchives.setOnClickListener(view1 -> {
            //档案
            startActivity(new Intent(getActivity(), ArchivesActivity.class));
        });

        viewHolder.mHomeIconPaytax.setOnClickListener(view1 -> {
            //缴税
            startActivity(new Intent(getActivity(), PayTaxesActivity.class));
        });
    }

    List<Integer> convenientBannerDtaSource;

    private void bindingConvenientBannerData() {
        convenientBannerDtaSource = new ArrayList<>();
        TypedArray typedArray = getResources().obtainTypedArray(R.array.convenientBannerDataSource);
        for (int i = 0; i < typedArray.length(); i++) {
            convenientBannerDtaSource.add(typedArray.getResourceId(i, 0));
        }
        typedArray.recycle();

        HomeCBAdapter homeCBAdapter = new HomeCBAdapter(convenientBannerDtaSource);
        mConvenientBanner.setPages(() -> homeCBAdapter,
                convenientBannerDtaSource)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}).startTurning(3000);
                //设置翻页的效果，不需要翻页效果可用不设
    }

    public class ViewHolder {
        @BindView(R.id.home_icon_circum)
        CustomIcon mHomeIconCircum;
        @BindView(R.id.home_icon_card)
        CustomIcon mHomeIconCard;
        @BindView(R.id.home_icon_peccancy)
        CustomIcon mHomeIconPeccancy;
        @BindView(R.id.home_icon_archives)
        CustomIcon mHomeIconArchives;
        @BindView(R.id.home_icon_paytax)
        CustomIcon mHomeIconPaytax;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
