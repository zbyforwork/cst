package com.hxkj.cst.cheshuotong.widget;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 刘杨 on 2015/8/26.
 */
public class NetworkImageHolderView implements CBPageAdapter.Holder<String> {
	private SimpleDraweeView imageView;

	List<String> mImageSources;

	public NetworkImageHolderView(List<String> imageSources) {
		mImageSources = imageSources;
	}

	@Override
	public View createView(Context context) {
		imageView =new SimpleDraweeView(context);
		imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
		return imageView;
	}

	@Override
	public void UpdateUI(Context context, int position, String data) {
		imageView.setImageURI(Uri.parse(mImageSources.get(position)));
	}

}
