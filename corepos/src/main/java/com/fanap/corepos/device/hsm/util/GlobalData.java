package com.fanap.corepos.device.hsm.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.fanap.corepos.device.hsm.dotin.xcheng.PinpadInterfaceVersion;

public class GlobalData {
	private static final String TAG = "GlobalData";
	private static final String PREFERENCES = "global_data";
	public Context mContext;
	private SharedPreferences mPrefs;
	private Editor mEditor;
	private static GlobalData instance = null;

	private static final String SETLOADPARAM = "setloadparam"; /* setloadparam */
	private static final String SETPINKEY = "setpinkey"; /* setpinkey */

	/**
	 * keys define
	 */
	private static final String PINPADVERSION = "pinpad_version"; /*
																 * version for
																 * pinpad
																 */
	private static final String TMKID = "tmkid"; /* tmk index */
	private static final String PINID = "pinid"; /* pin index */
	private static final String TDID = "tdid"; /* td index */

	private GlobalData() {

	}

	public void init(Context context) {
		if (instance == null) {
			instance = new GlobalData();
		}
		mContext = context;
		mPrefs = mContext.getSharedPreferences(PREFERENCES,
				Context.MODE_PRIVATE);
		mEditor = mPrefs.edit();
	}

	public static GlobalData getInstance() {
		if (instance == null) {
			instance = new GlobalData();
		}
		return instance;
	}

	/**
	 * set the load
	 * 
	 * @param ifLogin
	 */
	public void setLoadParamFlag(boolean ifLogin) {
		mEditor.putBoolean(SETLOADPARAM, ifLogin);
		mEditor.commit();
	}

	/**
	 * get the load status
	 * 
	 * @return
	 */
	public boolean getLoadParamFlag() {
		return mPrefs.getBoolean(SETLOADPARAM, false);
	}

	/**
	 * set the pinkey flag
	 * 
	 * @param ifset
	 */
	public void setPinkeyFlag(boolean ifset) {
		mEditor.putBoolean(SETPINKEY, ifset);
		mEditor.commit();
	}

	/**
	 * get the pinkey set status
	 * 
	 * @return
	 */
	public boolean getPinkeyFlag() {
		return mPrefs.getBoolean(SETPINKEY, false);
	}

	/**
	 * set the version which use for now
	 * 
	 * @param pinpadVersion
	 */
	public void setPinpadVersion(int pinpadVersion) {
		mEditor.putInt(PINPADVERSION, pinpadVersion);
		mEditor.commit();
	}

	/**
	 * get the version which use for now
	 * 
	 * @return
	 */
	public int getPinpadVersion() {
		return mPrefs.getInt(PINPADVERSION,
				PinpadInterfaceVersion.PINPAD_INTERFACE_VERSION);
	}

	/**
	 * set the tmkid which use for now
	 * 
	 * @param tmkid
	 */
	public void setTmkId(int tmkid) {
		mEditor.putInt(TMKID, tmkid);
		mEditor.commit();
	}

	/**
	 * get the tmkid which use for now
	 * 
	 * @return
	 */
	public int getTmkId() {
		return mPrefs.getInt(TMKID, 1);
	}

	/**
	 * set the pinid which use for now
	 * 
	 * @param tmkid
	 */
	public void setPinId(int tmkid) {
		mEditor.putInt(PINID, tmkid);
		mEditor.commit();
	}

	/**
	 * get the pinid which use for now
	 * 
	 * @return
	 */
	public int getPinId() {
		return mPrefs.getInt(PINID, 1);
	}

	/**
	 * set the tdid which use for now
	 * 
	 * @param tmkid
	 */
	public void setTdId(int tmkid) {
		mEditor.putInt(TDID, tmkid);
		mEditor.commit();
	}

	/**
	 * get the tdid which use for now
	 * 
	 * @return
	 */
	public int getTdId() {
		return mPrefs.getInt(TDID, 1);
	}
}
