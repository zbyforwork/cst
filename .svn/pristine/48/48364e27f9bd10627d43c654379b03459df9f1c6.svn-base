package com.hxkj.cst.cheshuotong.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.DensityUtils;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddCarActivity extends Activity {

	/**
	 * 返回
	 */
	@Bind(R.id.iv_back)
	ImageView ivBack;
	/**
	 * 车辆类型
	 */
	@Bind(R.id.tv_car)
	TextView tvCar;
	/**
	 * 车型
	 */
	@Bind(R.id.tv_carType)
	TextView tvCarType;
	/**
	 * 车型
	 */
	@Bind(R.id.tv_carType2)
	TextView tvCarType2;
	@Bind(R.id.iv_carType1)
	ImageView ivCarType1;
	/**
	 * 车架号
	 */
	@Bind(R.id.tv_chejiahao)
	TextView tvChejiahao;
	/**
	 * 车架号输入
	 */
	@Bind(R.id.et_chejiahao2)
	EditText etChejiahao2;
	/**
	 * 加入问号
	 */
	@Bind(R.id.iv_question)
	ImageView mIvQuestion;
	/**
	 * 备注
	 */
	@Bind(R.id.tv_remarks)
	TextView tvRemarks;
	/**
	 * 备注2
	 */
	@Bind(R.id.tv_remarkss2)
	EditText tvRemarkss2;

	@Bind(R.id.rel_tips)
	RelativeLayout relTips;
	/**
	 * 确定
	 */
	@Bind(R.id.bt_sure)
	Button btSure;
	@Bind(R.id.iv_jump_to_place_location)
	ImageView mIvJumpToPlaceLocation;
	@Bind(R.id.tv_location)
	TextView mTvLocation;
	@Bind(R.id.linearlayout_car_type_small)
	LinearLayout mLinearlayoutCarTypeSmall;
	@Bind(R.id.linearlayout_car_type_great)
	LinearLayout mLinearlayoutCarTypeGreat;
	@Bind(R.id.tv_add_car_belong_key)
	TextView mTvAddCarBelongKey;
	@Bind(R.id.linearlayout_car_license_hanzi)
	LinearLayout mLinearlayoutCarLicenseHanzi;
	@Bind(R.id.edittext_car_license_number)
	EditText mEdittextCarLicenseNumber;
	@Bind(R.id.linearlayout_car_license)
	LinearLayout mLinearlayoutCarLicense;
	private PopupWindow popupWindow;

	private String mXZQHDM;
	private String mCLID;


	private View mPopupWindowView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_car);
		ButterKnife.bind(this);
		try {
			ButterKnife.bind(this);
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		mIvJumpToPlaceLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(AddCarActivity.this, PlaceLocationActivity.class), 101);
			}
		});
		initPopupWindow();
		mLinearlayoutCarLicenseHanzi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showPopupWindow();
			}
		});
		mIvQuestion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageView imageView = new ImageView(AddCarActivity.this);
				imageView.setImageResource(R.drawable.jiazhao);
				MaterialDialog dialog = new MaterialDialog.Builder(AddCarActivity.this)
						.customView(imageView, true)
						.build();
				dialog.show();
			}
		});
		ivCarType1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivityForResult(new Intent(AddCarActivity.this, SelectCarTypeActivity.class), 201);
			}
		});
		btSure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				addCar();
			}
		});
		mLinearlayoutCarTypeSmall.setSelected(true);
		mLinearlayoutCarLicense.setSelected(true);
		mLinearlayoutCarTypeSmall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mLinearlayoutCarTypeSmall.setSelected(true);
				mLinearlayoutCarLicense.setSelected(true);
				mLinearlayoutCarTypeGreat.setSelected(false);
			}
		});
		mLinearlayoutCarTypeGreat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mLinearlayoutCarTypeSmall.setSelected(false);
				mLinearlayoutCarLicense.setSelected(false);
				mLinearlayoutCarTypeGreat.setSelected(true);
			}
		});
	}

	/**
	 * 添加车辆
	 */
	private void addCar() {
		final String cph = mTvAddCarBelongKey.getText().toString() + mEdittextCarLicenseNumber.getText().toString().trim();
		final String cjh = etChejiahao2.getText().toString().trim();
		String cphlx = "";
		if (mLinearlayoutCarTypeSmall.isSelected()) {
			cphlx = "小型车";
		}
		if (mLinearlayoutCarTypeGreat.isSelected()) {
			cphlx = "大型车";
		}
		final String alias = tvRemarkss2.getText().toString().trim();
		final String finalCphlx = cphlx;
		String url = ConstKey.ADD_CAR_ADDRESS + ParamsBuilder.getParams();
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						MyLog.i(response);
						final String retContent = ParseReturnUtil.parseRetrun(response, AddCarActivity.this);
						if (retContent != null) {
							MyToast.showShortMessage(AddCarActivity.this, "添加成功!");
							finish();
						}
					}
				}
				, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<>();
				map.put("YHID", TApplication.app.mUserId);
				map.put("SZQY", mXZQHDM);
				map.put("CLLBID", mCLID);
				map.put("CPHLX", finalCphlx);
				map.put("CPH", cph);
				map.put("CJH", cjh);
				map.put("ALIAS", alias);
				String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
				map.clear();
				MyLog.i(content);
				map.put("content", content);
				return map;
			}
		};
		// 把这个请求加入请求队列
		TApplication.app.addToRequestQueue(stringRequest);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 101 && resultCode == RESULT_OK) {
			String XZQHMC = data.getStringExtra("XZQHMC");
			if (XZQHMC != null) {
				mTvLocation.setText(XZQHMC);
			}
			String XZQHDM = data.getStringExtra("XZQHDM");
			if (XZQHDM != null) {
				mXZQHDM = XZQHDM;
			}
		}
		if (requestCode == 201 && resultCode == RESULT_OK) {
			String clmc = data.getStringExtra("CLMC");
			if (clmc != null) {
				tvCarType2.setText(clmc);
			}
			String clid = data.getStringExtra("CLID");
			if (clid != null) {
				mCLID = clid;
			}
		}
	}

	private void initPopupWindow() {
		initPopupWindowView();
		//初始化popupwindow，绑定显示view，设置该view的宽度/高度
		popupWindow = new PopupWindow(mPopupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景；使用该方法点击窗体之外，才可关闭窗体
		//        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		//Background不能设置为null，dismiss会失效
		//		popupWindow.setBackgroundDrawable(null);
		//设置渐入、渐出动画效果
		popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
		;
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		//popupWindow调用dismiss时触发，设置了setOutsideTouchable(true)，点击view之外/按键back的地方也会触发

	}

	private void initPopupWindowView() {
		mPopupWindowView = LayoutInflater.from(this).inflate(R.layout.select_car_number_province_layout, null);
		LinearLayout rl1 = (LinearLayout) mPopupWindowView.findViewById(R.id.rl_1);
		LinearLayout rl2 = (LinearLayout) mPopupWindowView.findViewById(R.id.rl_2);
		LinearLayout rl3 = (LinearLayout) mPopupWindowView.findViewById(R.id.rl_3);
		LinearLayout rl4 = (LinearLayout) mPopupWindowView.findViewById(R.id.rl_4);
		mPopupWindowView.findViewById(R.id.cancel_action).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		int width = (ScreenUtils.getScreenWidth(this) - DensityUtils.dp2px(this, 45)) / 8;
		for (int i = 0; i < rl1.getChildCount(); i++) {
			View child = rl1.getChildAt(i);
			if (child instanceof Button) {
				ViewGroup.LayoutParams lp = child.getLayoutParams();
				lp.width = width;
				lp.height = width;
				child.setLayoutParams(lp);
				child.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String s = ((Button) v).getText().toString();
						mTvAddCarBelongKey.setText(s);
						popupWindow.dismiss();
					}
				});
			}
		}
		for (int i = 0; i < rl2.getChildCount(); i++) {
			View child = rl2.getChildAt(i);
			if (child instanceof Button) {
				ViewGroup.LayoutParams lp = child.getLayoutParams();
				lp.width = width;
				lp.height = width;
				child.setLayoutParams(lp);
				child.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String s = ((Button) v).getText().toString();
						mTvAddCarBelongKey.setText(s);
						popupWindow.dismiss();
					}
				});
			}
		}
		for (int i = 0; i < rl3.getChildCount(); i++) {
			View child = rl3.getChildAt(i);
			if (child instanceof Button) {
				ViewGroup.LayoutParams lp = child.getLayoutParams();
				lp.width = width;
				lp.height = width;
				child.setLayoutParams(lp);
				child.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String s = ((Button) v).getText().toString();
						mTvAddCarBelongKey.setText(s);
						popupWindow.dismiss();
					}
				});
			}
		}
		for (int i = 0; i < rl4.getChildCount(); i++) {
			View child = rl4.getChildAt(i);
			if (child instanceof Button) {
				ViewGroup.LayoutParams lp = child.getLayoutParams();
				lp.width = width;
				lp.height = width;
				child.setLayoutParams(lp);
				child.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String s = ((Button) v).getText().toString();
						mTvAddCarBelongKey.setText(s);
						popupWindow.dismiss();
					}
				});
			}
		}
	}

	private void showPopupWindow() {
		if (!popupWindow.isShowing()) {
			popupWindow.showAtLocation(mEdittextCarLicenseNumber, Gravity.BOTTOM, 0, 0);
		} else {
			popupWindow.dismiss();
		}
	}

}
