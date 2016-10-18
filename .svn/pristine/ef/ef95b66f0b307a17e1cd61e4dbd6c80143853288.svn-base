package com.hxkj.cst.cheshuotong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.bean.ArchiveCat;
import com.hxkj.cst.cheshuotong.bean.DAXX;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/10/20.
 */
public class ArchiveContentAdapter extends UltimateViewAdapter<ArchiveContentAdapter.ViewHolder> {


    private List<DAXX> mSearchGoodses;

    public ArchiveContentAdapter(List<DAXX> searchGoodses) {
        this.mSearchGoodses = searchGoodses;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        MyLog.e("onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_archive_list, null);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    Context context;

    @Override
    public int getAdapterItemCount() {
        return mSearchGoodses == null ? 0 : mSearchGoodses.size();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.ivImage.setImageURI(Uri.parse(ConstKey.IMGAE_ROOT + mSearchGoodses.get(position).getDADZ()));
        holder.tvName.setText(mSearchGoodses.get(position).getDAMC());
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    public class ViewHolder extends UltimateRecyclerviewViewHolder {
        SimpleDraweeView ivImage;
        TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (SimpleDraweeView) itemView.findViewById(R.id.iv_image);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);

        }
    }

    @Override
    public long generateHeaderId(int i) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return null;
    }
}
