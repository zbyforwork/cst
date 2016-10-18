package com.hxkj.cst.cheshuotong.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.activity.PlaceLocationActivity;
import com.hxkj.cst.cheshuotong.activity.WebViewActivity;
import com.hxkj.cst.cheshuotong.activity.Xuanhao_PlaceLocationActivity;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * 刘杨
 * 全部评论的adapter
 */
public class Xuanhao_CityAdapter extends UltimateViewAdapter<Xuanhao_CityAdapter.ViewHolder> {
	private Context mContext;
	private List<String> mCities;

	public Xuanhao_CityAdapter(Context context, List<String> cities) {
		this.mContext=context;
		this.mCities = cities;
	}

	@Override
	public ViewHolder getViewHolder(View view) {
		return new ViewHolder(view);
	}

	//将Layout 转换为view
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public int getAdapterItemCount() {
		if (mCities!=null){
			return  mCities.size()==0?0:mCities.size();
		}
		return 0;
	}

	@Override
	public long generateHeaderId(int i) {
		return 0;
	}

	//绑定数据
	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.mTvCity.setText(mCities.get(position));
		holder.mRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MyToast.show(mContext, "即将开放");
//				Intent intent=new Intent(mContext, WebViewActivity.class);
//				intent.putExtra("url", "http://www.scjj.gov.cn:121/cd_chose.aspx");
//				mContext.startActivity(intent);
//				((Xuanhao_PlaceLocationActivity) mContext).finish();
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
		TextView mTvCity;
		View mRoot;
		public ViewHolder(View itemView) {
			super(itemView);
			//find 各种控件
			mRoot=itemView;
			mTvCity = (TextView) itemView.findViewById(R.id.tv_city_name);
		}
	}
}


