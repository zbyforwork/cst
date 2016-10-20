package com.hxkj.cst.cheshuotong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.bean.CardBean;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;


/**
 * Created by ly on 2016/10/11. 广告栏适配
 */
public class HomeUrecyclerAdapter extends UltimateViewAdapter<HomeUrecyclerAdapter.ViewHolder> {


    private Context context;
    private SparseArray<CardBean> cardBeanSparseArray;

    public HomeUrecyclerAdapter(Context context, SparseArray<CardBean> cardBeanSparseArray) {
        this.context = context;
        this.cardBeanSparseArray = cardBeanSparseArray;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    //将Layout 转换为view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_home_content, parent, false);
        //ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public int getAdapterItemCount() {
        return cardBeanSparseArray.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position < getItemCount()
                && (customHeaderView != null ? position <= cardBeanSparseArray.size() : position < cardBeanSparseArray.size())
                && (customHeaderView == null || position > 0)) {
            position -= customHeaderView == null ? 0 : 1;
            CardBean cardBean = cardBeanSparseArray.get(position);
            Log.d("HomeUrecyclerAdapter", "position:" + position + "==" + cardBean.getCardNick());
            holder.mIhcNick.setText(cardBean.getCardNick());
            holder.mIhcModel.setText(cardBean.getCardModel());
            holder.mIhcNumber.setText(cardBean.getCardNumber());
        }
    }

    //头部的ViewHolder 未使用
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    //绑定头部的的数据,未使用
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends UltimateRecyclerviewViewHolder {
        ImageView mIhcCardImage;
        TextView mIhcNick;
        TextView mIhcModel;
        TextView mIhcNumber;
        ViewHolder(View itemView) {
            super(itemView);
            mIhcCardImage = (ImageView) itemView.findViewById(R.id.ihc_card_image);
            mIhcNick = (TextView) itemView.findViewById(R.id.ihc_nick);
            mIhcModel = (TextView) itemView.findViewById(R.id.ihc_model);
            mIhcNumber = (TextView) itemView.findViewById(R.id.ihc_number);
        }
    }
}
