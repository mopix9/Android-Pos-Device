package com.fanap.corepos.device.mag_card;

import androidx.lifecycle.LiveData;

public interface MagCardInterface {
     void read();
     LiveData<String> getCardTrack2LiveData();
     void closeCardReader();
}
