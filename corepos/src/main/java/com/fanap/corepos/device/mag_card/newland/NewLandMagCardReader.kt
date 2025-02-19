package com.fanap.corepos.device.mag_card.newland

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.mag_card.MagCardInterface
import com.newland.nsdk.core.api.common.ModuleType
import com.newland.nsdk.core.api.common.card.contactless.ContactlessCardInfo
import com.newland.nsdk.core.api.common.card.contactless.ContactlessCardType
import com.newland.nsdk.core.api.common.card.magcard.MagCardInfo
import com.newland.nsdk.core.api.common.card.magcard.TrackStatus
import com.newland.nsdk.core.api.common.cardreader.CardReaderListener
import com.newland.nsdk.core.api.common.cardreader.CardReaderParameters
import com.newland.nsdk.core.api.common.cardreader.CardType
import com.newland.nsdk.core.api.common.utils.LogUtils
import com.newland.nsdk.core.api.internal.cardreader.CardReader
import java.nio.charset.Charset

class NewLandMagCardReader : MagCardInterface {

    private val cardReader: CardReader = NewLandSetup.moduleManager.getModule(ModuleType.CARD_READER) as CardReader

    private lateinit var cardTrack2LiveData: MutableLiveData<String>


    override fun read() {
        val parameter = CardReaderParameters()
        val cardTypes = arrayOf(CardType.MAG_CARD)
        parameter.contactlessCardTypes = arrayOf(
            ContactlessCardType.TYPE_F,
            ContactlessCardType.TYPE_A,
            ContactlessCardType.TYPE_B
        )
        LogUtils.d("CardReaderFragment", "********* open card reader.")
        cardReader.openCardReader(cardTypes, 30, parameter, cardReaderListener)
        LogUtils.d("CardReaderFragment", "********* Card reader opened.")
    }

    override fun getCardTrack2LiveData(): LiveData<String> {
        cardTrack2LiveData = MutableLiveData()
        return cardTrack2LiveData
    }

    override fun closeCardReader() {
        try {
            LogUtils.d("CardReaderFragment", "********* Cancel card reader.")
            cardReader.cancelCardReader()
            LogUtils.d("CardReaderFragment", "********* Card reader Cancelled.")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    private val cardReaderListener: CardReaderListener = object : CardReaderListener {
        override fun onTimeout() {
            LogUtils.d("CardReaderFragment", "********* Timeout in listener.")
            closeCardReader()
            read()
        }

        override fun onCancel() {
            LogUtils.d("CardReaderFragment", "********* Cancelled in listener.")
        }

        override fun onError(errorCode: Int, message: String) {
            closeCardReader()
            read()
        }

        override fun onFindMagCard(result: MagCardInfo) {
            try {
                val firstTrackData = result.track1Data
                val secondTrack = result.track2Data
                val thirdTrack = result.track3Data
                val serviceCode = result.serviceCode
                val expireDate = result.validDate
                val trackStatus = result.trackStatus

                if (trackStatus[1] == TrackStatus.GOOD) {
                    if (secondTrack == null)
                        cardTrack2LiveData.postValue("")
                    else cardTrack2LiveData.postValue(String(secondTrack, Charset.forName("gbk")))
                } else
                    cardTrack2LiveData.postValue("")


            } catch (e: Exception) {
                e.printStackTrace()
                cardTrack2LiveData.postValue("")
            }
            closeCardReader()
        }

        override fun onFindContactCard() {
        }

        override fun onFindContactlessCard(
            contactlessCardType: ContactlessCardType,
            contactlessCardInfo: ContactlessCardInfo
        ) {
        }
    }

}
