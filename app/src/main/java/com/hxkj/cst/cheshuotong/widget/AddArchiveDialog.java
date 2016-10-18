package com.hxkj.cst.cheshuotong.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;

/**
 * Created by Administrator on 2015/10/23.
 */
public class AddArchiveDialog {

    public interface  ArchiveListener{
        public void setText(String name);
    }

    public static Dialog createDialog(Context context,final ArchiveListener listener) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.add_archive, null);// 得到加载view
        /*
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView

        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字

        tipTextView.setText(msg);// 设置加载信息

*/      Button button=(Button) v.findViewById(R.id.button);
        final EditText editText= (EditText) v.findViewById(R.id.edit_text);
        final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                String name=  editText.getText().toString();
                listener.setText(name);
            }
        });

        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        return loadingDialog;

    }
}
