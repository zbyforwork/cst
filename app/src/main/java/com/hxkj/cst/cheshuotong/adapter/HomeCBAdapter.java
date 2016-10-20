package com.hxkj.cst.cheshuotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hxkj.cst.cheshuotong.R;

import java.util.List;

/**
 * Created by ly on 2016/10/11. convenientBanner 适配
 */

public class HomeCBAdapter implements Holder<Integer> {

    private ImageView convenientImage;
    private List<Integer> convenientBannerDtaSource;

    public HomeCBAdapter(List<Integer> convenientBannerDtaSource) {
        this.convenientBannerDtaSource = convenientBannerDtaSource;
    }


    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_cb, null);
        convenientImage = (ImageView) view.findViewById(R.id.home_cb_image);
        return convenientImage;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        convenientImage.setImageResource(convenientBannerDtaSource.get(position));
    }
}
