package com.hxkj.cst.cheshuotong.adapter;

import android.app.Activity;
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
import com.hxkj.cst.cheshuotong.activity.PlaceLocationActivity;
import com.hxkj.cst.cheshuotong.activity.SelectCarTypeActivity;
import com.hxkj.cst.cheshuotong.bean.PPCLXX;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * 刘杨
 * 品牌车辆信息的adapter
 */
public class PPCLXXAdapter extends UltimateViewAdapter<PPCLXXAdapter.ViewHolder> {
	private Context mContext;
	private List<PPCLXX> list;

	public PPCLXXAdapter(Context context, List<PPCLXX> cities) {
		this.mContext=context;
		this.list = cities;
	}

	@Override
	public ViewHolder getViewHolder(View view) {
		return new ViewHolder(view);
	}

	//将Layout 转换为view
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ppclxx, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public int getAdapterItemCount() {
		if (list !=null){
			return  list.size()==0?0: list.size();
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
		holder.mTvName.setText(list.get(position).getCLMC());
		holder.mImage.setImageURI(Uri.parse(ConstKey.IMGAE_ROOT+list.get(position).getCLTB()));
		holder.mRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.putExtra("CLMC", list.get(position).getCLMC());
				intent.putExtra("CLID", list.get(position).getCLID());
				((SelectCarTypeActivity)mContext).setResult(Activity.RESULT_OK,intent);
				((SelectCarTypeActivity) mContext).finish();
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
		TextView mTvName;
		SimpleDraweeView mImage;
		View mRoot;
		public ViewHolder(View itemView) {
			super(itemView);
			//find 各种控件
			mRoot=itemView;
			mImage=(SimpleDraweeView)itemView.findViewById(R.id.image);
			mTvName = (TextView) itemView.findViewById(R.id.title);
		}
	}
}


