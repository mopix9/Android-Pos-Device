package com.fanap.corepos.device.hsm.sina.xcheng;

public class PedRsaPinData {
	private byte[] iccRandomData;
	private byte[] modData;
	private byte[] expData;
	private int iccRandomDataLen;
	private int modDataLen;
	private int expDataLen;

	public byte[] getModData() {
		return modData;
	}

	public void setModData(byte[] modData) {
		this.modData = modData;
	}

	public byte[] getExpData() {
		return expData;
	}

	public void setExpData(byte[] expData) {
		this.expData = expData;
	}

	public byte[] getIccRandomData() {
		return iccRandomData;
	}

	public void setIccRandomData(byte[] iccRandomData) {
		this.iccRandomData = iccRandomData;
	}

	public int getIccRandomDataLen() {
		return iccRandomDataLen;
	}

	public void setIccRandomDataLen(int iccRandomDataLen) {
		this.iccRandomDataLen = iccRandomDataLen;
	}

	public int getModDataLen() {
		return modDataLen;
	}

	public void setModDataLen(int modDataLen) {
		this.modDataLen = modDataLen;
	}

	public int getExpDataLen() {
		return expDataLen;
	}

	public void setExpDataLen(int expDataLen) {
		this.expDataLen = expDataLen;
	}

}
