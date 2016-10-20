package com.hxkj.cst.cheshuotong.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.zxing.WriterException;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.activity.AppointmentActivity;
import com.hxkj.cst.cheshuotong.bean.YYXX;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.DensityUtils;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.QRGenerate;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.HashMap;
import java.util.List;


/**
 * 刘杨
 * 预约信息的adapter
 */
public class YYXXAdapter extends UltimateViewAdapter<YYXXAdapter.ViewHolder> {
    private List<YYXX> mYYXXList;
    private Context mContext;

    public YYXXAdapter(Context context, List<YYXX> YYXXList) {
        this.mYYXXList = YYXXList;
        this.mContext = context;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    //将Layout 转换为view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_yyxx, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getAdapterItemCount() {
        if (mYYXXList != null) {
            return mYYXXList.size() == 0 ? 0 : mYYXXList.size();
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
        holder.mTvName.setText(mYYXXList.get(position).getJGMC());
        holder.mTvTel.setText(mYYXXList.get(position).getLXDH());
        holder.mTvAddress.setText(mYYXXList.get(position).getJGDZ());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeAppointment(mYYXXList.get(position).getJGDM());
            }
        });
    }

    private void MakeAppointment(final String jgdm) {
        String url = ConstKey.CLYYJS_YYJS + ParamsBuilder.getParams();

        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("CLJSSBID", TApplication.app.mCLJSSBID);
        map.put("SWJGDM", jgdm);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i(content);
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(mContext, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), mContext);
                if (retContent == null || retContent.equals("[]")) {
                            /*new AlertView("预约失败!", null, "确定", null, null, mContext, null, null).setCancelable(false).show();*/
                    new MaterialDialog.Builder(mContext)
                            .title("错误")
                            .content("预约失败!")
                            .positiveText("确定")
                            .cancelable(false)
                            .show();
                    return;
                }
                MyLog.i(retContent);
                ImageView imageView = new ImageView(mContext);
                try {
                    Bitmap bitmap = QRGenerate.createQRCode(retContent, DensityUtils.dp2px(mContext, 200.0f));
                    imageView.setImageBitmap(bitmap);
                            /*AlertView alertView = new AlertView("预约成功!", null, null, new String[]{"确定"}, null, mContext, null, new OnItemClickListener() {
                                @Override
                                public void onItemClick(Object o, int position) {
                                   mContext.startActivity(new Intent(mContext, MainActivity.class));
                                    ((AppointmentActivity)mContext).finish();
                                }
                            }).setCancelable(false)
                                    .addExtView(imageView);*/
                    MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                            .title("预约成功！")
                            .customView(imageView, true)
                            .positiveText("确定")
                            .titleGravity(GravityEnum.CENTER)
                            .contentGravity(GravityEnum.CENTER)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    ((AppointmentActivity) mContext).finish();
                                }
                            }).build();
                    dialog.show();
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
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
        TextView mTvName, mTvAddress, mTvTel;
        View mRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            //find 各种控件
            mRoot = itemView;
            mTvName = (TextView) itemView.findViewById(R.id.tv_organization_name);
            mTvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            mTvTel = (TextView) itemView.findViewById(R.id.tv_tel);
        }
    }
}


