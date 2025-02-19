/*
package com.fanap.corepos.device.hsm.sina.xcheng;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;

import com.fanap.corepos.R;
import com.fanap.corepos.databinding.FragmentPasswordCardBinding;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class PinpadView {


	public static final int warp_content_width = -2;
	public static final int warp_content_height = -2;
	public static final int match_content_width = -1;
	public static final int match_content_height = -1;

	private static PinpadView mPinpad;

	public boolean byo_state = true;
	private int[][] pinpad_id = { { 1, 4, 7, 10 }, { 2, 5, 8, 0 },
			{ 3, 6, 9, 11 } };

	private static HashMap<String, String> map = new HashMap<String, String>();

	private Context a;

	public AlertDialog mDialog;
	private FrameLayout layout;
	private LinearLayout root;
	private TextView byo;
	private Handler mHandle;

	private boolean isHardwareKeyboard = true;
	private ByteBuffer pinpad_coordinate;
	private TextView[] key_view = new TextView[12];
	private int pwdMold = 13;

	private String title;

	private PinpadView() {
	}

	public static PinpadView INSTANCE() {
		return mPinpad == null ? mPinpad = new PinpadView() : mPinpad;
	}

	public void create(Context context, String title) {
		this.a = context;
		this.title = title;
		if (mDialog == null)
			mDialog = new AlertDialog.Builder(context).create();
		if (pwdMold == 12) {
			pinpad_coordinate = ByteBuffer.allocate(96);
		} else {
			pinpad_coordinate = ByteBuffer.allocate(104);
		}

		mHandle = new Handler(Looper.getMainLooper());

		initMap();
		boundary(context);
		mDialog.setCancelable(false);
		mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialogInterface, int i,
								 KeyEvent keyEvent) {
				return false;
			}
		});
		mDialog.show();
		mDialog.getWindow().setContentView(layout);
		mDialog.getWindow().setGravity(Gravity.BOTTOM);
		mDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		WindowManager.LayoutParams wmlp = mDialog.getWindow().getAttributes();
		WindowManager wm = (WindowManager) a
				.getSystemService(Context.WINDOW_SERVICE);
		Display d = wm.getDefaultDisplay();
		//wmlp.height = 620;
		wmlp.width = d.getWidth();
		wmlp.y = 0;
		mDialog.getWindow().setAttributes(wmlp);
	}

	private void initMap() {
		map.put("0", "0");
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		map.put("6", "6");
		map.put("7", "7");
		map.put("8", "8");
		map.put("9", "9");
		map.put("-21", "ESC");
		map.put("-35", "Enter");
	}

	public void close() {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog = null;
			layout = null;
			root = null;
			byo = null;
			pinpad_coordinate.clear();
		}
	}

	public byte[] calculation(byte[] keys) {
		for (int i = 0; i <= key_view.length - 1; i++) {
			TextView tv = key_view[i];
			Log.d("TAG", String.valueOf(keys[i] - 0x30) + "");
			tv.setText(map.get(String.valueOf(keys[i] - 0x30) + ""));
			byte[] pos = new byte[8];
			int[] location = new int[2];
			tv.getLocationOnScreen(location);
			int leftx = location[0];
			int lefty = location[1];
			int rightx = location[0] + tv.getWidth();
			int righty = location[1] + tv.getHeight();
			byte[] tmp = intToBytes(leftx);
			byte[] tmp1 = intToBytes(lefty);
			byte[] tmp2 = intToBytes(rightx);
			byte[] tmp3 = intToBytes(righty);
			pos[0] = tmp[2];
			pos[1] = tmp[3];
			pos[2] = tmp1[2];
			pos[3] = tmp1[3];
			pos[4] = tmp2[2];
			pos[5] = tmp2[3];
			pos[6] = tmp3[2];
			pos[7] = tmp3[3];
			pinpad_coordinate.put(pos);
		}
		return pinpad_coordinate.array();
	}

	private TextView passwordLengthShow(Context context) {
		byo = new TextView(context);
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
				match_content_width, 0, 1);
		byo.setLayoutParams(ll);
		byo.setGravity(Gravity.CENTER);
		byo.setTextSize(20);
		return byo;
	}

	private TextView titleShow(Context context) {
		TextView mTitle = new TextView(context);
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
				match_content_width, 20, 1);
		mTitle.setLayoutParams(ll);
		mTitle.setGravity(Gravity.CENTER);
		mTitle.setTextSize(20);
		mTitle.setText((title == null ? "Please enter PIN" : title));
		return mTitle;
	}

	private void boundary(Context context) {
		layout = new FrameLayout(context);
		layout.setLayoutParams(new FrameLayout.LayoutParams(
				match_content_width, match_content_height));
		//layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(Color.WHITE);
		layout.setPadding(10, 10, 10, 10);
		if (byo_state) {
			//layout.setWeightSum(8);
			//layout.addView(titleShow(context));
			//layout.addView(passwordLengthShow(context));
			FragmentPasswordCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_password_card, null, false);
			layout = binding.bottomSheetDialog;
			byo = binding.edtPassword;
		} else {
			//layout.setWeightSum(6);
		}

		root = new LinearLayout(context);
		root.setLayoutParams(new LinearLayout.LayoutParams(match_content_width,
				0, 6));
		root.setOrientation(LinearLayout.HORIZONTAL);
		root.setWeightSum(3);

		if (!isHardwareKeyboard) {
			root.addView(column(context, 0, pinpad_id));
			root.addView(column(context, 1, pinpad_id));
			root.addView(column(context, 2, pinpad_id));
		}

		layout.addView(root);
	}

	public void byoShow(final String s) {
		if (byo != null) {
			mHandle.post(new Runnable() {
				@Override
				public void run() {
					byo.setText(s.toString());
				}
			});
		}
	}

	private LinearLayout column(Context context, int index, int[][] id) {
		LinearLayout column = new LinearLayout(context);
		LinearLayout.LayoutParams column_ll = new LinearLayout.LayoutParams(
				match_content_width, match_content_height);
		column_ll.weight = 1;
		column.setLayoutParams(column_ll);
		column.setOrientation(LinearLayout.VERTICAL);
		column.setBackgroundColor(Color.WHITE);
		column.setWeightSum(4);

		column.addView(row(context, id[index][0]));
		column.addView(row(context, id[index][1]));
		column.addView(row(context, id[index][2]));
		column.addView(row(context, id[index][3]));
		return column;
	}

	@SuppressLint("NewApi")
	private TextView row(Context context, int id) {
		TextView tv = new TextView(context);
		LinearLayout.LayoutParams tv_ll = new LinearLayout.LayoutParams(
				match_content_width, match_content_height);
		tv_ll.weight = 1;
		tv.setLayoutParams(tv_ll);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(18);
		tv.setTextColor(Color.BLACK);
		tv.getPaint().setFakeBoldText(true);
		tv.setBackground(buttonResult());
		tv.setId(id);
		key_view[id] = tv;
		return tv;
	}

	private Drawable buttonResult() {
		StateListDrawable sld = new StateListDrawable();
		sld.addState(new int[] { android.R.attr.state_pressed },
				genDrawable(0xffd6d6d6, 1, 0xffE9E9E9, 0));
		sld.addState(new int[] { -android.R.attr.state_pressed },
				genDrawable(0xffffffff, 1, 0xffE9E9E9, 0));
		return sld;
	}

	private Drawable genDrawable(int color, int weight, int linellae_color,
								 float radius) {
		GradientDrawable gd_down = new GradientDrawable();
		gd_down.setColor(color);
		gd_down.setStroke(weight, linellae_color);
		gd_down.setCornerRadius(radius);
		return gd_down;
	}

	private byte[] intToBytes(int value) {
		byte[] src = new byte[] { (byte) (value >> 24 & 255),
				(byte) (value >> 16 & 255), (byte) (value >> 8 & 255),
				(byte) (value & 255) };
		return src;
	}

	*/
/*FragmentPasswordCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_password_card, null, false);
			layout = binding.bottomSheetDialog;
			byo = binding.edtPassword;*//*

}
*/
