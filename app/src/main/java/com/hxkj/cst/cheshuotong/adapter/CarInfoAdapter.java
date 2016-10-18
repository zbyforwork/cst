package com.hxkj.cst.cheshuotong.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.activity.CarDetailActivity;
import com.hxkj.cst.cheshuotong.bean.CarInfoSimple;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * 刘杨
 * 车辆列表的adapter
 */
public class CarInfoAdapter extends UltimateViewAdapter<CarInfoAdapter.ViewHolder> {
	private Context mContext;
	private List<CarInfoSimple> orderList;

	public CarInfoAdapter(Context context, List<CarInfoSimple> orders) {
		this.mContext=context;
		this.orderList = orders;
	}

	@Override
	public ViewHolder getViewHolder(View view) {
		return new ViewHolder(view);
	}

	//将Layout 转换为view
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_car_info, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public int getAdapterItemCount() {
		if (orderList !=null){
			return  orderList.size()==0?0: orderList.size();
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
		holder.mTvCarAlias.setText(orderList.get(position).getALIAS()+"");
		holder.mTvCarNumber.setText(orderList.get(position).getPLATENUMBER()+"");
		holder.mTvCarType.setText(orderList.get(position).getMODELS()+"");
		holder.mIvImage.setImageURI(Uri.parse(ConstKey.IMGAE_ROOT + orderList.get(position).getICON() + ""));
		holder.mRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(mContext, CarDetailActivity.class);
				intent.putExtra("ID",orderList.get(position).getID());
				mContext.startActivity(intent);
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
		TextView mTvCarAlias, mTvCarType, mTvCarNumber;
		SimpleDraweeView mIvImage;
		View mRoot;
		public ViewHolder(View itemView) {
			super(itemView);
			//find 各种控件
			mRoot=itemView;
            mTvCarAlias = (TextView) itemView.findViewById(R.id.tv_car_alias);
            mTvCarType =(TextView) itemView.findViewById(R.id.tv_car_type);
            mTvCarNumber =(TextView) itemView.findViewById(R.id.tv_car_number);
			mIvImage=(SimpleDraweeView)itemView.findViewById(R.id.image);
		}
	}
}


