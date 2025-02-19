/*
package com.fanap.corepos.device.hsm.sina.xcheng;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.pos.sdk.emvcore.POIEmvCoreManager;
import com.pos.sdk.emvcore.POIEmvCoreManager.EmvPinConstraints;
import com.pos.sdk.security.POIHsmManage;

public class PinpadDialog {

	public static final int PLAIN_PIN    = 1;
	public static final int ONLINE_PIN   = 2;
	public static final int ENCIPHER_PIN = 3;

	private String DEFAULT_EXP_PIN_LEN_IND = "4";
	private int    DEFAULT_TIMEOUT_MS      = 30000;

	private int pinKeyIndex;
	private int keyMode = POIHsmManage.PED_PINBLOCK_FETCH_MODE_TPK;

	private String  pinCard;
	private int     pinType;
	private boolean pinBypass;
	private POIHsmManage     hsmManage;
	private PinEventListener pinEventListener;
	private PinpadView View;
	private Context context;
	private PWListener listener;
	private String pinpadExpPinLen = "4";


	public interface PWListener {
		void onConfirm(int VerifyResult, byte[] pinblock, byte[] pinksn);

		void onCancel();

		void onError(int VerifyResult, int PinTryCntOut);
	}

	public void setListener(PWListener listener) {
		this.listener = listener;
	}

	public PinpadDialog(Context context,  Bundle bundle, int pinKeyIndex) {
		this.context = context;
		this.View = PinpadView.INSTANCE();
		this.hsmManage = POIHsmManage.getDefault();
		this.pinEventListener = new PinEventListener();
		this.pinKeyIndex = pinKeyIndex;

		switch (bundle.getInt(EmvPinConstraints.PIN_TYPE, -1)) {
			case POIEmvCoreManager.PIN_PLAIN_PIN:
				pinType = PLAIN_PIN;
				break;
			case POIEmvCoreManager.PIN_ONLINE_PIN:
				pinType = ONLINE_PIN;
				break;
			case POIEmvCoreManager.PIN_ENCIPHER_PIN:
				pinType = ENCIPHER_PIN;
				break;
			default:
				break;
		}


		if (bundle.containsKey(EmvPinConstraints.PIN_CARD)) {
			pinCard = bundle.getString(EmvPinConstraints.PIN_CARD);
		}
		if (bundle.containsKey(EmvPinConstraints.PIN_BYPASS)) {
			pinBypass = bundle.getBoolean(EmvPinConstraints.PIN_BYPASS);
		}
	}

	public int showDialog() {
		int result = onOnlinePin();

		this.View.create(context, "لطفا رمز خود را وارد نمایید");

		return result;
	}

	public void closeDialog() {
		View.close();
		hsmManage.unregisterListener(pinEventListener);
	}

	private int onOnlinePin() {
		hsmManage.registerListener(pinEventListener);

		byte[] data = new byte[24];
		byte[] temp = CalcPinBlock.calcPinBlock(pinCard).getBytes();
		System.arraycopy(temp, 0, data, 0, 16);


		byte[] formatData = {0, 0, 0, 0, 0, 0, 0, 0};
		System.arraycopy(formatData, 0, data, 16, 8);

		return hsmManage.PedGetPinBlock(keyMode, pinKeyIndex, 0, DEFAULT_TIMEOUT_MS, data, DEFAULT_EXP_PIN_LEN_IND);
	}

	private class PinEventListener implements POIHsmManage.EventListener {

		private String TAG = "PinEventListener";

		@Override
		public void onPedVerifyPin(POIHsmManage manage, int type, byte[] rspBuf) {
			if (type == POIHsmManage.PED_VERIFY_PIN_TYPE_PLAIN || type == POIHsmManage.PED_VERIFY_PIN_TYPE_CIPHER) {
				int sw1 = (rspBuf[1] >= 0 ? rspBuf[1] : (rspBuf[1] + 256));
				int sw2 = (rspBuf[2] >= 0 ? rspBuf[2] : (rspBuf[2] + 256));

				if (sw1 == 0x90 && sw2 == 0x00) {
					listener.onConfirm(0,null, null);
				} else if (sw1 == 0x63 && (sw2 & 0xc0) == (int) 0xc0) {
					if ((sw2 & 0x0F) == 0) {
						listener.onError(EmvPinConstraints.VERIFY_PIN_BLOCK, 0);
					} else {
						listener.onError(EmvPinConstraints.VERIFY_ERROR, sw2 & 0x0F);
					}
				} else if (sw1 == 0x69 && (sw2 == (int) 0x83 || sw2 == (int) 0x84)) {
					listener.onError(EmvPinConstraints.VERIFY_PIN_BLOCK, 0);
				} else {
					listener.onError(EmvPinConstraints.VERIFY_NO_PASSWORD, 0);
				}
			} else {
				listener.onError(EmvPinConstraints.VERIFY_NO_PASSWORD, 0);
			}
			closeDialog();
		}

		@Override
		public void onPedPinBlockRet(POIHsmManage manage, int type, byte[] rspBuf) {
			if (rspBuf[0] != 0) {
				byte[] pinBlock = new byte[rspBuf[0]];
				System.arraycopy(rspBuf, 1, pinBlock, 0, rspBuf[0]);
				if (rspBuf.length > (rspBuf[0] + 1)) {
					byte[] ksn = new byte[rspBuf[rspBuf[0] + 1]];
					System.arraycopy(rspBuf, rspBuf[0] + 2, ksn, 0, rspBuf[rspBuf[0] + 1]);
					listener.onConfirm(0, pinBlock, ksn);
				} else {
					listener.onConfirm(0, pinBlock, null);
				}
			}
			closeDialog();
		}

		@Override
		public void onKeyboardShow(POIHsmManage manage, byte[] keys, int timeout) {

		}

		@Override
		public void onKeyboardInput(POIHsmManage manage, int numKeys) {
			String info = "";
			while (0 != (numKeys--))
				info += "*";
			if (info.length() <= 12)
				View.byoShow(info);
		}

		@Override
		public void onInfo(POIHsmManage manage, int what, int extra) {
			Log.e(TAG, "onInfo");
		}

		@Override
		public void onError(POIHsmManage manage, int what, final int extra) {
			Log.e(TAG, "onError:" + extra);

			switch (extra) {
				case 0xFFFF:
				case 0xFFFD:
					listener.onError(EmvPinConstraints.VERIFY_CANCELED, 0);
					closeDialog();
					return;
				case 0xFFFC:
					//tvMessage.setText("The terminal triggers a security check.");
					break;
				case 0xFED3:
					//tvMessage.setText("The terminal did not write the PIN key. Please check.");
					break;
				case 0XFECF:
					if (pinBypass) {
						listener.onError(EmvPinConstraints.VERIFY_NO_PASSWORD, 0);
					} else {
						listener.onError(EmvPinConstraints.VERIFY_ERROR, 0);
					}
					closeDialog();
					return;
				default:
					listener.onError(EmvPinConstraints.VERIFY_NO_PASSWORD, 0);
					closeDialog();
					return;
			}

			new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

				@Override
				public void run() {
					listener.onError(EmvPinConstraints.VERIFY_CANCELED, 0);
					closeDialog();
				}
			}, 2000);
		}

		@Override
		public void onHwSelfCheckRet(POIHsmManage manage, int type, int checkResult) {
			Log.e(TAG, "onHwSelfCheckRet");
		}

		@Override
		public void onHwSensorTriggered(POIHsmManage manage, int triggered, byte[] sensorValue, byte[] triggerTime) {
			Log.e(TAG, "onHwSensorTriggered");
		}

		@Override
		public void onPedKeyManageRet(POIHsmManage manage, int ret) {
			Log.e(TAG, "onPedKeyManageRet");
		}
	}

	static class CalcPinBlock {

		static String calcPinBlock(String accountNumber) {
			return "0000" + extractAccountNumberPart(accountNumber);
		}

		static String extractAccountNumberPart(String accountNumber) {
			String accountNumberPart;
			accountNumberPart = takeLastN(accountNumber, 13);
			accountNumberPart = takeFirstN(accountNumberPart, 12);
			return accountNumberPart;
		}

		static String takeLastN(String str, int n) {
			if (str.length() > n) {
				return str.substring(str.length() - n);
			} else {
				if (str.length() < n) {
					return zero(str, n);
				} else {
					return str;
				}
			}
		}

		static String takeFirstN(String str, int n) {
			if (str.length() > n) {
				return str.substring(0, n);
			} else {
				if (str.length() < n) {
					return zero(str, n);
				} else {
					return str;
				}
			}
		}

		static String zero(String str, int len) {
			str = str.trim();
			StringBuilder builder = new StringBuilder(len);
			int fill = len - str.length();
			while (fill-- > 0) {
				builder.append((char) 0);
			}
			builder.append(str);
			return builder.toString();
		}
	}
}*/
