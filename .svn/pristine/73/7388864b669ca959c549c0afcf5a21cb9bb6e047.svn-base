package com.hxkj.cst.cheshuotong.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.activity.AddCarActivity;
import com.hxkj.cst.cheshuotong.activity.ArchivesActivity;
import com.hxkj.cst.cheshuotong.activity.BreakRuleActivity;
import com.hxkj.cst.cheshuotong.activity.BuilddingActivity;
import com.hxkj.cst.cheshuotong.activity.PayTaxesActivity;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ScreenUtils;
import com.hxkj.cst.cheshuotong.widget.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {


    @Bind(R.id.iv_add_car)
    ImageView mIvAddCar;
    @Bind(R.id.ico1)
    LinearLayout ico1;
    @Bind(R.id.ico2)
    LinearLayout ico2;
    @Bind(R.id.ico3)
    LinearLayout ico3;
    @Bind(R.id.ico4)
    LinearLayout ico4;
    @Bind(R.id.ico5)
    LinearLayout ico5;
    @Bind(R.id.ico6)
    LinearLayout ico6;
    @Bind(R.id.convenientBanner)
    ConvenientBanner mConvenientBanner;
    @Bind(R.id.rl_pay_taxs)
    RelativeLayout mRlPayTaxs;
    private List<String> networkImages;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setLinstener();
        init();
        showBanner();
        return view;
    }

    private void showBanner() {
        networkImages = new ArrayList<>();
        networkImages.add("res://x/" + R.drawable.banner1);
        networkImages.add("res://x/" + R.drawable.banner2);
        networkImages.add("res://x/" + R.drawable.banner3);
        networkImages.add("res://x/" + R.drawable.banner4);
        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView(networkImages);
            }
        }, networkImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        //设置翻页的效果，不需要翻页效果可用不设
                .setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer)
                .startTurning(3000);
    }

    private void init() {
        setBannerSize();
    }


    private void setBannerSize() {
        //设置banner的宽高 16:9
        int witdth = ScreenUtils.getScreenWidth(getActivity());
        ViewGroup.LayoutParams lp = mConvenientBanner.getLayoutParams();
        lp.width = witdth;
        lp.height = (int) (witdth * 9.0 / 16);
        mConvenientBanner.setLayoutParams(lp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setLinstener() {
        ico1.setOnClickListener(this);
        ico2.setOnClickListener(this);
        ico3.setOnClickListener(this);
        ico4.setOnClickListener(this);
        ico5.setOnClickListener(this);
        ico6.setOnClickListener(this);
        mRlPayTaxs.setOnClickListener(this);
        mIvAddCar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ico1:
                Intent intent = new Intent(getActivity(), ArchivesActivity.class);
                startActivity(intent);
                break;
            case R.id.ico2:
                intent = new Intent(getActivity(), BreakRuleActivity.class);
                startActivity(intent);
                break;
            case R.id.ico3:
                intent = new Intent(getActivity(), BuilddingActivity.class);
                startActivity(intent);
                break;
            case R.id.ico4:
                intent = new Intent(getActivity(), BuilddingActivity.class);
                startActivity(intent);
                break;
            case R.id.ico5:
                intent = new Intent(getActivity(), BuilddingActivity.class);
                startActivity(intent);
                break;
            case R.id.ico6:
                intent = new Intent(getActivity(), BuilddingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_pay_taxs:
                intent = new Intent(getActivity(), PayTaxesActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_add_car:
                if (TApplication.app.mUserId == null) {
                    MyToast.showShortMessage(getActivity(), "请先登录再进行添加车辆操作");
                    return;
                }
                startActivity(new Intent(getActivity(), AddCarActivity.class));
                break;

        }

    }
}
