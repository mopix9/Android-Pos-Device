package com.fanap.corepos.device;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import com.justtide.service.dev.aidl.DeviceProvider;
import com.justtide.service.dev.aidl.system.SystemConstant;
import com.justtide.service.dev.aidl.system.SystemProvider;
import com.justtider.justtideserver.DeviceDateProvider;

public class JusttideSetup {
    public static void startBindService(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("unbinding");
        Intent intent = new Intent();
        intent.setAction("com.justtide.service.dev.AIDL_SERVICE");
        intent.setPackage("com.justtide.service.dev");
        context.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);

        startBindDateService(context);
    }

    public static void startBindDateService(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Binder");
        Intent intent = new Intent();
        intent.setAction("com.justtide.service.dev.AIDL_SERVICE");
        intent.setPackage("com.justtider.justtideserver");
        context.bindService(intent, mConnectionDateTime, Service.BIND_AUTO_CREATE);
    }

    private static ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            DeviceConfig.deviceProvider = null;
            DeviceConfig.INSTANCE.getOnServiceConnected().postValue(false);
            Log.d("justtide_service", "disconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DeviceConfig.INSTANCE.getOnServiceConnected().postValue(true);
            DeviceConfig.deviceProvider = DeviceProvider.Stub.asInterface(service);
            try {
                DeviceConfig.deviceProvider.getSystemProvider().configFunKey(SystemConstant.FUNKEYMOED_HOME, true);
                DeviceConfig.deviceProvider.getSystemProvider().configFunKey(SystemConstant.FUNKEYMOED_APP_SWITCH, true);
                DeviceConfig.utils = DeviceConfig.deviceProvider.getUtilsProvider();
                Log.d("justtide_service", "connected");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };


    private static ServiceConnection mConnectionDateTime = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v("wuzhang", "----------onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.v("wuzhang", "----------onServiceConnected");
            DeviceConfig.deviceDateProvider = DeviceDateProvider.Stub.asInterface(service);
            try {
                SystemProvider systemProvider = SystemProvider.Stub.asInterface(service);
           //     systemProvider.configFunKey(SystemConstant.FUNKEYMOED_HOME, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
