package com.hxkj.cst.cheshuotong.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.activity.PlaceLocationActivity;
import com.hxkj.cst.cheshuotong.bean.XZQH;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * 刘杨
 * 全部评论的adapter
 */
public class ProvinceAdapter extends UltimateViewAdapter<ProvinceAdapter.ViewHolder> {
	private Context mContext;
	private List<XZQH> mProvinceList;

	public ProvinceAdapter(Context context,List<XZQH> provinceList) {
		this.mContext=context;
		this.mProvinceList = provinceList;
	}

	@Override
	public ViewHolder getViewHolder(View view) {
		return new ViewHolder(view);
	}

	//将Layout 转换为view
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_province, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public int getAdapterItemCount() {
		return mProvinceList.size();
	}

	@Override
	public long generateHeaderId(int i) {
		return 0;
	}

	//绑定数据
	@Override
	public void onBindViewHolder(ViewHolder holder,  final int position) {
		String provinceName=mProvinceList.get(position).getXZQHMC();
		holder.mTvProvince.setText(provinceName);
	/*	final boolean isMunicipality=provinceName.equals("北京市")||provinceName.equals("上海市")||provinceName.equals("天津市")||provinceName.equals("重庆市");
		final  int positioneTemp=position;
		if (isMunicipality){
			holder.mIvJumpToCities.setVisibility(View.GONE);
		}*/
		holder.mRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if (isMunicipality){
					((PlaceLocationActivity)mContext).setResult(Activity.RESULT_OK,new Intent().putExtra("result", mProvinceList.get(positioneTemp).getXZQHMC()));
					((PlaceLocationActivity) mContext).finish();
				}else {
					((PlaceLocationActivity) mContext).ShowCities(mProvinceList.get(positioneTemp).getXSXZQH());
				}*/
				((PlaceLocationActivity) mContext).ShowCities(mProvinceList.get(position).getXSXZQH());
			}
		});

	}

	//头部的ViewHolder 未使用
	@Override
	public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
		return null;
	}

	//绑定头部的的数据,未使用
	@Override
	public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

	}


	//获取控件
	public static class ViewHolder extends UltimateRecyclerviewViewHolder {
		TextView mTvProvince;
		ImageView mIvJumpToCities;
		View mRoot;

		public ViewHolder(View itemView) {
			super(itemView);
			//find 各种控件
			mTvProvince = (TextView) itemView.findViewById(R.id.tv_province_name);
			mIvJumpToCities = (ImageView) itemView.findViewById(R.id.iv_jump_to_city);
			mRoot=itemView;
		}
	}
}


