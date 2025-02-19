package com.fanap.corepos.device.hsm.util;

import com.pos.sdk.security.POIHsmManage;
import com.pos.sdk.security.PedKcvInfo;
import com.pos.sdk.security.PedKeyInfo;
import com.pos.sdk.security.PosSecurityManager;
import com.pos.sdk.utils.PosByteArray;

public class SpFunctions {

	public static final int PED_TLK = 0X01;
	public static final int PED_TMK = 0X02;
	public static final int PED_TPK = 0X03;
	public static final int PED_TAK = 0X04;
	public static final int PED_TDK = 0X05;
	public static final int PED_TEK = 0X06;
	public static final int PED_TTK = 0X09;

	public static final int PED_DES = 0x00;
	public static final int PED_TDES = 0x00; // 3DES
	public static final int PED_AES = 0x10;
	public static final int PED_SM4 = 0x20;

	public static final int PED_ECB_ENC = 0x00;
	public static final int PED_ECB_DEC = 0x01;
	public static final int PED_CBC_ENC = 0x02;
	public static final int PED_CBC_DEC = 0x03;

	public static final int PIN_KEY_INDEX = 0x0a;
	public static final int MAC_KEY_INDEX = 0x0b;
	public static final int TRACK_KEY_INDEX = 0x0c;

	public static final int PROTECTKEYINDEX = 0x09;
	public static final int TMPKEYINDEX = 0x0C;

	private PosSecurityManager mPosSecurityManager = PosSecurityManager.getDefault();

	// ////////////////////////////////////////////////////////////////////////////
	// Ped
	/**
	 *
	 * @param KeyIdx
	 * @param DataInLen
	 * @param DataIn
	 * @param DataOut
	 * @param Mode
	 * @return
	 */
	public int PedDes(int KeyIdx, int DataInLen, byte[] DataIn, byte[] DataOut,
					  int Mode) {
		int iret = -1;
		PosByteArray posByteArray = new PosByteArray(DataOut, DataOut.length);
		iret = mPosSecurityManager
				.PedCalDes(KeyIdx, Mode, DataIn, posByteArray);

		int i = 0;
		while (iret < 0) {

			try {
				Thread.currentThread().sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i >= 3)
				break;
			iret = mPosSecurityManager.PedCalDes(KeyIdx, Mode, DataIn,
					posByteArray);
			i++;
		}
		if (iret == 0) {
			System.arraycopy(posByteArray.buffer, 0, DataOut, 0,
					posByteArray.buffer.length);
		}
		return iret;
	}

	/**
	 *
	 * @param GroupIdx
	 * @param KeyVarType
	 * @param IvLen
	 * @param pucIV
	 * @param DataInLen
	 * @param DataIn
	 * @param DataOut
	 * @param KsnOut
	 * @param Mode
	 * @return
	 */
	public int PedDukptDes(int GroupIdx, int KeyVarType, int IvLen,
						   byte[] pucIV, int DataInLen, byte[] DataIn, byte[] DataOut,
						   byte[] KsnOut, int Mode) {
		int ret = -1;

		PosByteArray rspKsn = new PosByteArray(KsnOut, KsnOut.length);
		PosByteArray rspBuf = new PosByteArray(DataOut, DataOut.length);

		ret = mPosSecurityManager.PedDukptDes(GroupIdx, KeyVarType, pucIV,
				Mode, DataIn, rspKsn, rspBuf);
		if (ret == 0) {
			System.arraycopy(rspKsn.buffer, 0, KsnOut, 0, rspKsn.buffer.length);
			System.arraycopy(rspBuf.buffer, 0, DataOut, 0, rspBuf.buffer.length);
		}
		return ret;
	}

	/**
	 *
	 * @param GroupIdx
	 * @return
	 */
	public int PedDukptIncreaseKsn(int GroupIdx) {
		return mPosSecurityManager.PedDukptIncreaseKsn(GroupIdx);
	}

	/**
	 *
	 * @return
	 */
	public int PedErase() {
		return mPosSecurityManager.PedErase();
	}

	/**
	 *
	 * @param GroupIdx
	 * @param KsnOut
	 * @return
	 */
	public int PedGetDukptKSN(int GroupIdx, byte[] KsnOut) {
		int ret = -1;
		PosByteArray rspBuf = new PosByteArray(KsnOut, KsnOut.length);
		ret = mPosSecurityManager.PedGetDukptKsn(GroupIdx, rspBuf);
		if (ret == 0) {
			System.arraycopy(rspBuf.buffer, 0, KsnOut, 0, rspBuf.buffer.length);
		}
		return ret;
	}

	/**
	 *
	 * @param KeyType
	 * @param KeyIdx
	 * @param iCheckMode
	 * @param szCheckBuf
	 * @return
	 */
	public String PedGetKcv(int KeyType, int KeyIdx, int iCheckMode,
							byte[] szCheckBuf) {
		int ret = -1;
		PedKcvInfo kcvInfo = new PedKcvInfo(iCheckMode, szCheckBuf);
		PosByteArray rspBuf = new PosByteArray();

		ret = mPosSecurityManager.PedGetKcv(KeyType, KeyIdx, kcvInfo, rspBuf);
		if (ret == 0) {
			return BCDHelper.bcdToString(rspBuf.buffer, 0, rspBuf.len);
		}
		return null;
	}

	/**
	 *
	 * @param GroupIdx
	 * @param DataInLen
	 * @param DataIn
	 * @param MacOut
	 * @param KsnOut
	 * @param Mode
	 * @return
	 */
	public int PedGetDukptMac(int GroupIdx, int DataInLen, byte[] DataIn,
							  byte[] MacOut, byte[] KsnOut, int Mode) {
		int ret = -1;
		PosByteArray macBuf = new PosByteArray(MacOut, MacOut.length);
		PosByteArray ksnBuf = new PosByteArray(KsnOut, KsnOut.length);
		ret = mPosSecurityManager.PedGetMacDukpt(GroupIdx, Mode, DataIn,
				macBuf, ksnBuf);
		if (ret == 0) {
			System.arraycopy(macBuf.buffer, 0, MacOut, 0, macBuf.buffer.length);
			System.arraycopy(ksnBuf.buffer, 0, KsnOut, 0, ksnBuf.buffer.length);
		}
		return ret;
	}

	public int PedWriteKey(int srcKeyType, int srcKeyIdx, int dstKeyType,
						   int dstKeyIdx, int algthflag, int dstKeyLen, byte[] bufIn,
						   int mode, byte[] aucCheckBufIn) {
		byte[] kcv = null;
		if (mode == 0) {
			kcv = new byte[5];
		} else {
			kcv = new byte[aucCheckBufIn.length + 1];
			kcv[0] = (byte) aucCheckBufIn.length;
			System.arraycopy(aucCheckBufIn, 0, kcv, 1, aucCheckBufIn.length);
		}
		PedKeyInfo pedKeyInfo = new PedKeyInfo(srcKeyType, srcKeyIdx,
				dstKeyType, dstKeyIdx, algthflag, dstKeyLen, bufIn);
		PedKcvInfo pedKcvInfo = new PedKcvInfo(mode, kcv);
		int ret = this.mPosSecurityManager.PedWriteKey(pedKeyInfo, pedKcvInfo);
		return ret;
	}
}
