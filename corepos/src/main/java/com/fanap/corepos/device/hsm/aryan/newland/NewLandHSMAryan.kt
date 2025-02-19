package com.fanap.corepos.device.hsm.aryan.newland

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.hsm.HSMInterface
import com.newland.nsdk.core.api.common.ModuleType
import com.newland.nsdk.core.api.common.crypto.*
import com.newland.nsdk.core.api.common.exception.NSDKException
import com.newland.nsdk.core.api.common.keymanager.*
import com.newland.nsdk.core.api.common.utils.ISOUtils
import com.newland.nsdk.core.api.internal.crypto.Crypto
import com.newland.nsdk.core.api.internal.keymanager.KeyManager

class NewLandHSMAryan : HSMInterface , KeyBoardNumberActivity.PinResult {
    private val mKeyManager: KeyManager =
        NewLandSetup.moduleManager.getModule(ModuleType.KEY_MANAGER) as KeyManager

    private val mCrypto: Crypto = NewLandSetup.moduleManager.getModule(ModuleType.CRYPTO) as Crypto

    private lateinit var passwordMutableLiveData : MutableLiveData<String>


    companion object{

        private lateinit var newLandHsm: NewLandHSMAryan

        @JvmStatic
        fun provideListener(): KeyBoardNumberActivity.PinResult {
            return newLandHsm
        }
    }

    init {
        newLandHsm = this@NewLandHSMAryan
    }

    override fun loadMasterKey(masterKey: String?): Boolean {

        val desKey = SymmetricKey()

        desKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_MK
        desKey.keyType = KeyType.DES
        desKey.keyUsage = KeyUsage.KEK

        desKey.keyLen = 16
        val keyBuffer: ByteArray = ISOUtils.hex2byte(masterKey)// Aryan C17BA2392A43A5EAA4AD06A470EDA267// Aryan operational 3272A9DB5E07D848337D00C275CCC58A    FanAva 2A5BA7203EFBD562AB10BAA1FE2334BA //Sayan 1C1C1C1C1C1C1C1C1010101010101010
        desKey.keyData = keyBuffer
        desKey.kcvMode = KCVMode.NONE

        val algorithmParameters = AlgorithmParameters()
        algorithmParameters.cipherMode = CipherMode.ECB

        return try {
            mKeyManager.generateKey(KeyGenerateMethod.CLEAR, algorithmParameters, null, desKey)
            true
        } catch (e: NSDKException) {
            e.printStackTrace()
            false
        }

    }

    override fun loadPinKey(pinKey: String?): Boolean {
        val desKey = SymmetricKey()
        desKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_WK_PIN
        desKey.keyData = ISOUtils.hex2byte(pinKey)
        desKey.keyUsage = KeyUsage.PIN

        val algorithmParameters = AlgorithmParameters()
        algorithmParameters.cipherMode = CipherMode.ECB

        val sourceKey = SymmetricKey()
        sourceKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_MK
        sourceKey.keyType = KeyType.DES
        sourceKey.keyUsage = KeyUsage.KEK
        desKey.keyLen = 16
        desKey.kcvMode = KCVMode.NONE

        return try {
            mKeyManager.generateKey(
                KeyGenerateMethod.CIPHER,
                algorithmParameters,
                sourceKey,
                desKey
            )
            true
        } catch (e: NSDKException) {
            e.printStackTrace()
            false
        }
    }

    override fun loadMacKey(macKey: String?): Boolean {
        val algorithmParameters = AlgorithmParameters()
        algorithmParameters.cipherMode = CipherMode.ECB

        val sourceKey = SymmetricKey()
        sourceKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_MK
        sourceKey.keyType = KeyType.DES
        sourceKey.keyUsage = KeyUsage.KEK

        val desKey = SymmetricKey()
        desKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_WK_MAC
        desKey.keyUsage = KeyUsage.MAC
        desKey.keyLen = 16
        desKey.keyData = ISOUtils.hex2byte(macKey)
        desKey.kcvMode = KCVMode.NONE
        return try {
            mKeyManager.generateKey(
                KeyGenerateMethod.CIPHER,
                algorithmParameters,
                sourceKey,
                desKey
            )
            true
        } catch (e: NSDKException) {
            e.printStackTrace()
            false
        }
    }

    override fun loadDataKey(dataKey: String?): Boolean {

        val sourceKey = SymmetricKey()
        sourceKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_MK
        sourceKey.keyType = KeyType.DES
        sourceKey.keyUsage = KeyUsage.KEK

        val desKey = SymmetricKey()
        desKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_WK_DATA
        desKey.keyType = KeyType.DES
        desKey.keyUsage = KeyUsage.DATA
        desKey.keyLen = 16
        desKey.keyData = ISOUtils.hex2byte(dataKey)
        desKey.kcvMode = KCVMode.NONE

        val algorithmParameters = AlgorithmParameters()
        algorithmParameters.cipherMode = CipherMode.ECB

        return try {
            mKeyManager.generateKey(
                KeyGenerateMethod.CIPHER,
                algorithmParameters,
                sourceKey,
                desKey
            )
            true
        } catch (e: NSDKException) {
            e.printStackTrace()
            false
        }

    }

    override fun calcMac(bytes: ByteArray?): ByteArray {

        var macOutput: MACOutput? = null
        try {
            macOutput = mCrypto.generateMAC(AppConfig.Keys.MKSK_DES_INDEX_WK_MAC, MACType.TDES_X919, null, bytes)
        } catch (e: NSDKException) {
            e.printStackTrace()
        }

        return macOutput!!.data
    }

    override fun inputPin(cardNumber: String?, context: Context?) {
        val pinKey = SymmetricKey()
        pinKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_WK_PIN
        pinKey.keyType = KeyType.DES
        pinKey.keyUsage = KeyUsage.PIN

        try {
            mKeyManager.getKeyInfo(KeyInfoID.KCV, pinKey)
        } catch (e: NSDKException) {
            e.printStackTrace()
            return
        }

        val intent = Intent(context, KeyBoardNumberActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val card = cardNumber?.split("=")?.get(0)
        intent.putExtra("CARD", card)
        context!!.startActivity(intent)
    }


    override fun decryptionData(encryptedData: String?): String {
        var result = ""
        val desKey = SymmetricKey()
        desKey.keyID = AppConfig.Keys.MKSK_DES_INDEX_WK_DATA
        desKey.keyUsage = KeyUsage.DATA
        val datain = ISOUtils.hex2byte(encryptedData)
        val cipherOutput: CipherOutput?
        try {
            cipherOutput =
                mCrypto.decrypt(desKey, CipherType.DES_ECB, PaddingMode.NONE, null, datain)
            result = ISOUtils.hexString(cipherOutput.data)
        } catch (e: NSDKException) {
            e.printStackTrace()
        }

        return result
    }

    override fun encryptionData(data: String?): String {
        TODO("Not yet implemented")
    }

    override fun getMutablePassword(): MutableLiveData<String> {
        passwordMutableLiveData = MutableLiveData<String>()
        return passwordMutableLiveData
    }

    override fun onPinResult(pinBlock: String?) {
        passwordMutableLiveData.postValue(pinBlock)
    }
}
