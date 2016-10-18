package com.hxkj.cst.cheshuotong.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.activity.ChooseUserCarTypeActivity;
import com.hxkj.cst.cheshuotong.activity.PayTaxesSeven;
import com.hxkj.cst.cheshuotong.activity.PlaceLocationActivity;
import com.hxkj.cst.cheshuotong.bean.PayTaxOrder;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * 刘杨
 * 缴税订单的adapter
 */
public class OrderAdapter extends UltimateViewAdapter<OrderAdapter.ViewHolder> {
	private Context mContext;
	private List<PayTaxOrder> orderList;

	public OrderAdapter(Context context, List<PayTaxOrder> orders) {
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
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pay_tax_order, viewGroup, false);
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
		holder.mTvOrderID.setText(orderList.get(position).getDDBH());
		holder.mTvOrderZone.setText(orderList.get(position).getQYMC());
		holder.mTvOrderState.setText(orderList.get(position).getZTMS());
		holder.mTvOrderCreateTime.setText(orderList.get(position).getCJSJ());
		holder.mRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(orderList.get(position).getCLMC())) {
					Intent intent = new Intent(mContext, PayTaxesSeven.class);
					intent.putExtra("orderId", orderList.get(position).getDDID());
					intent.putExtra("Type", 0);
					mContext.startActivity(intent);
				}else {
					Intent intent = new Intent(mContext, ChooseUserCarTypeActivity.class);
					intent.putExtra("orderId", orderList.get(position).getDDID());
					intent.putExtra("Type", 0);
					mContext.startActivity(intent);
				}
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
		TextView mTvOrderID,mTvOrderState,mTvOrderZone,mTvOrderCreateTime;
		View mRoot;
		public ViewHolder(View itemView) {
			super(itemView);
			//find 各种控件
			mRoot=itemView;
            mTvOrderID = (TextView) itemView.findViewById(R.id.tv_order_id);
            mTvOrderState=(TextView) itemView.findViewById(R.id.tv_order_state);
            mTvOrderZone=(TextView) itemView.findViewById(R.id.tv_order_zone);
            mTvOrderCreateTime=(TextView) itemView.findViewById(R.id.tv_order_create_time);
		}
	}
}


