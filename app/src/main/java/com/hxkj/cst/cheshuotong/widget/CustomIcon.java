package com.hxkj.cst.cheshuotong.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ly on 2016/10/11. 自定义Icon
 */

public class CustomIcon extends LinearLayout {
    @BindView(R.id.custom_icon_image)
    ImageView mCustomIconImage;
    @BindView(R.id.custom_icon_tv)
    TextView mCustomIconTv;

    public CustomIcon(Context context) {
        super(context);
        initViewAttrs(context, null, 0);
    }

    public CustomIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewAttrs(context, attrs, 0);
    }

    public CustomIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewAttrs(context, attrs, defStyleAttr);
    }

    private void initViewAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_icon, this, true);
        ButterKnife.bind(this, view);

        if (attrs != null) {
            TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.custom_icon);
            float drawableHeight = attrArray.getDimension(
                    attrArray.getIndex(R.styleable.custom_icon_drawableHeight), 0);
            float drawableWidth = attrArray.getDimension(
                    attrArray.getIndex(R.styleable.custom_icon_drawableWith), 0);
            int resId = attrArray.getResourceId(
                    attrArray.getIndex(R.styleable.custom_icon_drawable), 0);
            String describe = attrArray.getString(
                    attrArray.getIndex(R.styleable.custom_icon_describe));
            int describeId = attrArray.getResourceId(R.styleable.custom_icon_describe, 0);

            if (drawableWidth != 0 && drawableHeight != 0) {
                LayoutParams layoutParams = new LayoutParams((int) drawableWidth, (int) drawableHeight);
                mCustomIconImage.setLayoutParams(layoutParams);
            }

            if (resId != 0) {
                mCustomIconImage.setImageResource(resId);
            }

            if (!TextUtils.isEmpty(describe)) {
                mCustomIconTv.setText(describe);
            } else if (describeId != 0) {
                mCustomIconTv.setText(describeId);
            }


            attrArray.recycle();
        }
    }
}
