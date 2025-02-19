/*
package com.masa.aryan.settings.fingerprint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.masa.aryan.R;
import com.masa.aryan.databinding.ActivityFingerPrintBinding;
import com.nextbiometrics.biometrics.NBBiometricsContext;
import com.nextbiometrics.biometrics.NBBiometricsExtractResult;
import com.nextbiometrics.biometrics.NBBiometricsFingerPosition;
import com.nextbiometrics.biometrics.NBBiometricsIdentifyResult;
import com.nextbiometrics.biometrics.NBBiometricsSecurityLevel;
import com.nextbiometrics.biometrics.NBBiometricsStatus;
import com.nextbiometrics.biometrics.NBBiometricsTemplate;
import com.nextbiometrics.biometrics.NBBiometricsTemplateType;
import com.nextbiometrics.biometrics.NBBiometricsVerifyResult;
import com.nextbiometrics.biometrics.event.NBBiometricsScanPreviewEvent;
import com.nextbiometrics.biometrics.event.NBBiometricsScanPreviewListener;
import com.nextbiometrics.devices.NBDevice;
import com.nextbiometrics.devices.NBDeviceScanFormatInfo;
import com.nextbiometrics.devices.NBDeviceSecurityModel;
import com.nextbiometrics.devices.NBDevices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Delay;


@AndroidEntryPoint
public class FingerPrintActivity extends AppCompatActivity {

@Inject
 Context appContext;

    private @NonNull ActivityFingerPrintBinding binding;
    //The path  of file which to enabled USB host
    // private static final File fingerFile = new File("/sys/class/fingerprint_ctrl", "fingerprint_en");
    private static final File fingerFile = new File("/sys/class/otg_ctrl", "usb_mode");
    private NBDevice device;
    NBDeviceScanFormatInfo scanFormatInfo = null;

    private static final int MAX_ANTISPOOF_THRESHOLD = 1000;
    private static final int MIN_ANTISPOOF_THRESHOLD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFingerPrintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnScanAndExtract.setClickable(false);

        binding.btnEnabledHost.setOnClickListener(v -> {
            //Enabled USB HOST
            boolean result = FileUtil.writeFileByString(fingerFile, "2", false);
            showLog(result ? "Enabled Host success" : "Enabled Host failed", Color.BLACK);
        });

        binding.btnInitDEvice.setOnClickListener(v -> new Thread(() -> {
            new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //init devices
                boolean initResult = initDevices();
                if (!initResult) {
                    showLog("Init device failed.", Color.RED);
                    return;
                }
                if(device!=null){
                    showLog("DEVICE Model:"+device.toString(),Color.BLACK);
                }
                showLog("Init device success.", Color.GREEN);
            }).start();
        }).start());

        binding.btnDisEnabledHost.setOnClickListener(v ->{
            release();
            FileUtil.writeFileByString(fingerFile, "0", false);
        });

        binding.btnScanAndExtract.setClickable(true);

        binding.btnScanAndExtract.setOnClickListener(v -> new Thread(() -> scanAndExtract()).start());


    }

    private void scanAndExtract() {
        //Check the status of init device
        if (device == null || (device != null && !device.isSessionOpen())) {
            showLog("Re Init devices.", Color.BLACK);
         */
/*   boolean init = initDevices();
            if (init) {
                showLog("Re Init devices success.", Color.GREEN);
            } else {
                showLog("Re Init devices failed.", Color.RED);
                return;
            }*//*

        }
        //TODO
        NBBiometricsContext nbBiometricsContext = new NBBiometricsContext(device);
        NBBiometricsExtractResult extractResult = null;
        showLog("Extracting fingerprint template,please put your finger on sensor.", Color.BLACK);
        extractResult = nbBiometricsContext.extract(NBBiometricsTemplateType.ISO, NBBiometricsFingerPosition.UNKNOWN, scanFormatInfo, new NBBiometricsScanPreviewListener() {
            int counter = 0;

            @Override
            public void preview(NBBiometricsScanPreviewEvent event) {
                int spoofScore = event.getSpoofScoreValue();
                byte[] image = event.getImage();
                showStatus("extract\nStatus:" + event.getScanStatus().toString());
                String promote = String.format("PREVIEW #%d: Status: %s, Finger detect score: %d, Spoof Score: %d, image %d bytes",
                        ++counter, event.getScanStatus().toString(), event.getFingerDetectValue(), spoofScore, image == null ? 0 : image.length);
                showLog(promote, Color.BLACK);
                if (spoofScore <= MIN_ANTISPOOF_THRESHOLD || spoofScore >= MAX_ANTISPOOF_THRESHOLD) {
                    showLog("spoofScore invalid:" + spoofScore, Color.RED);
                }

                if (image != null && (device.toString().contains("65210") || device.toString().contains("65200"))) {
                    new Handler(Looper.getMainLooper()).post(() -> binding.imageView.setImageBitmap(convertToBitmap(scanFormatInfo, image)));
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->binding.imageView.setImageResource(R.mipmap.ic_launcher));
                }
            }
        });
        showLog("result:" + extractResult, Color.BLACK);
        if (extractResult.getStatus() != NBBiometricsStatus.OK) {
            return;
        }
        //get template info
        NBBiometricsTemplate template = extractResult.getTemplate();
        int quality = template.getQuality();
        showLog("Last scan:" + quality, Color.BLACK);
        //Start Verify the fingerprint
        boolean verifyResult = verify(template, device);
        if (!verifyResult) {
            return;
        }
        //identify
        boolean identifyResult = identify(template, device);
        if (!identifyResult) {
            return;
        }
        //save template
        boolean saveResult = saveTemplate(template, device);
        showStatus("SAVE ISO result:" + saveResult);


    }

    private boolean saveTemplate(NBBiometricsTemplate template, NBDevice device) {
        NBBiometricsContext nbBiometricsContext = new NBBiometricsContext(device);
        byte[] binaryTemplate = nbBiometricsContext.saveTemplate(template);
        String base64Template = Base64.encodeToString(binaryTemplate, 0);
        Log.d("TEST","--:"+base64Template);
        showLog("Template base64:" + base64Template, Color.BLACK);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/template.bin";
        showLog("Template store to path: " + path, Color.BLACK);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(binaryTemplate);
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            showLog("Error:NEXT  Biometrics SDK error:" + e.getMessage(), Color.RED);
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            showLog("Save ISO error:" + e.getMessage(), Color.RED);
            e.printStackTrace();
            return false;
        } finally {
            if (nbBiometricsContext != null) {
                nbBiometricsContext.dispose();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    private boolean verify(NBBiometricsTemplate template, NBDevice device) {
        showLog("2:Verifying fingerprint,pls put your finger on sensor.", Color.BLACK);
        NBBiometricsContext nbBiometricsContext = new NBBiometricsContext(device);
        NBBiometricsVerifyResult nbBiometricsVerifyResult = nbBiometricsContext.verify(NBBiometricsTemplateType.ISO, NBBiometricsFingerPosition.UNKNOWN, scanFormatInfo, new NBBiometricsScanPreviewListener() {
            int counter = 0;

            @Override
            public void preview(NBBiometricsScanPreviewEvent event) {
                int spoofScore = event.getSpoofScoreValue();
                byte[] image = event.getImage();
                showStatus("verify\nStatus:" + event.getScanStatus().toString());
                String promote = String.format("PREVIEW #%d: Status: %s, Finger detect score: %d, Spoof Score: %d, image %d bytes",
                        ++counter, event.getScanStatus().toString(), event.getFingerDetectValue(), spoofScore, image == null ? 0 : image.length);
                showLog(promote, Color.BLACK);
                if (spoofScore <= MIN_ANTISPOOF_THRESHOLD || spoofScore >= MAX_ANTISPOOF_THRESHOLD) {
                    showLog("spoofScore invalid:" + spoofScore, Color.RED);
                }

                if (image != null && (device.toString().contains("65210") || device.toString().contains("65200"))) {
                    new Handler(Looper.getMainLooper()).post(() -> binding.imageView.setImageBitmap(convertToBitmap(scanFormatInfo, image)));
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> binding.imageView.setImageResource(R.mipmap.ic_launcher));
                }
            }
        }, template, NBBiometricsSecurityLevel.NORMAL);
        showLog("Verify result:" + nbBiometricsVerifyResult.getStatus().toString(), Color.BLACK);
        return nbBiometricsVerifyResult.getStatus() == NBBiometricsStatus.OK;
    }

    private boolean identify(NBBiometricsTemplate template, NBDevice device) {
        List<AbstractMap.SimpleEntry<Object, NBBiometricsTemplate>> simpleEntries = new LinkedList<>();
        simpleEntries.add(new AbstractMap.SimpleEntry<>("TEST", template));
        showLog("3:Identifying fingerprint, please put your finger on sensor!", Color.BLACK);
        NBBiometricsContext nbBiometricsContext = new NBBiometricsContext(device);
        NBBiometricsIdentifyResult identifyResult = nbBiometricsContext.identify(NBBiometricsTemplateType.ISO, NBBiometricsFingerPosition.UNKNOWN, scanFormatInfo, new NBBiometricsScanPreviewListener() {
            int counter = 0;

            @Override
            public void preview(NBBiometricsScanPreviewEvent event) {
                int spoofScore = event.getSpoofScoreValue();
                byte[] image = event.getImage();
                showStatus("identify\nStatus:" + event.getScanStatus().toString());
                String promote = String.format("PREVIEW #%d: Status: %s, Finger detect score: %d, Spoof Score: %d, image %d bytes",
                        ++counter, event.getScanStatus().toString(), event.getFingerDetectValue(), spoofScore, image == null ? 0 : image.length);
                showLog(promote, Color.BLACK);
                if (spoofScore <= MIN_ANTISPOOF_THRESHOLD || spoofScore >= MAX_ANTISPOOF_THRESHOLD) {
                    showLog("spoofScore invalid:" + spoofScore, Color.RED);
                }

                if (image != null && (device.toString().contains("65210") || device.toString().contains("65200"))) {
                    new Handler(Looper.getMainLooper()).post(() -> binding.imageView.setImageBitmap(convertToBitmap(scanFormatInfo, image)));
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->binding.imageView.setImageResource(R.mipmap.ic_launcher));
                }
            }
        }, simpleEntries.iterator(), NBBiometricsSecurityLevel.NORMAL);
        showLog("identify Result:" + identifyResult.getStatus().toString(), Color.BLACK);
        return identifyResult.getStatus() == NBBiometricsStatus.OK;

    }

    private boolean initDevices() {
        // Initialize biometric coprocessor
        showLog("Start init devices.", Color.GREEN);

        NBDevices.initialize(getApplicationContext());
        //Obtain biometric coprocessor info
        showLog("Waiting for a USB device.", Color.BLACK);
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (NBDevices.getDevices().length != 0) {
                break;
            }
        }
        NBDevice[] nbDevices = NBDevices.getDevices();
        if (nbDevices.length == 0) {
            //TODO no devices
            showLog("Length ==0,No USB devices found,try trying and SPI device.", Color.RED);
            final String DEFAULT_SPI_NAME = "/dev/spidev0.0";
            final int PIN_OFFSET = 902;
            final int DEFAULT_AWAKE_PIN_NUMBER = PIN_OFFSET + 69;
            int DEFAULT_RESET_PIN_NUMBER = PIN_OFFSET + 12;
            int DEFAULT_CHIP_SELECT_PIN_NUMBER = PIN_OFFSET + 18;
            try {
                device = NBDevice.connectToSpi(DEFAULT_SPI_NAME, DEFAULT_AWAKE_PIN_NUMBER, DEFAULT_RESET_PIN_NUMBER, DEFAULT_CHIP_SELECT_PIN_NUMBER, NBDevice.DEVICE_CONNECT_TO_SPI_SKIP_GPIO_INIT_FLAG);
                if (device == null) {
                    showLog("NO SPI devices as well.", Color.RED);
                    return false;
                }
            } catch (Exception e) {
                showLog("NO SPI devices as well." + e.getMessage(), Color.RED);
                return false;
            }

        } else {
            device = nbDevices[0];
        }
        openSession();
        NBDeviceScanFormatInfo[] scanFormatInfos = device.getSupportedScanFormats();
        if (scanFormatInfos.length == 0) {
            showLog("No support formats found.", Color.RED);
            return false;
        }
        scanFormatInfo = scanFormatInfos[1];
        return true;

    }


    @Override
    protected void onPause() {
        super.onPause();
        release();

    }

    private void release() {
        if (device != null) {
            device.dispose();
            device = null;
        }
        NBDevices.terminate();

    }


    private void openSession() {
        if (device != null && !device.isSessionOpen()) {
            byte[] cakId = "DefaultCAKKey1\0".getBytes();
            byte[] cak = {
                    (byte) 0x05, (byte) 0x4B, (byte) 0x38, (byte) 0x3A, (byte) 0xCF, (byte) 0x5B, (byte) 0xB8, (byte) 0x01, (byte) 0xDC, (byte) 0xBB, (byte) 0x85, (byte) 0xB4, (byte) 0x47, (byte) 0xFF, (byte) 0xF0, (byte) 0x79,
                    (byte) 0x77, (byte) 0x90, (byte) 0x90, (byte) 0x81, (byte) 0x51, (byte) 0x42, (byte) 0xC1, (byte) 0xBF, (byte) 0xF6, (byte) 0xD1, (byte) 0x66, (byte) 0x65, (byte) 0x0A, (byte) 0x66, (byte) 0x34, (byte) 0x11
            };
            byte[] cdkId = "Application Lock\0".getBytes();
            byte[] cdk = {
                    (byte) 0x6B, (byte) 0xC5, (byte) 0x51, (byte) 0xD1, (byte) 0x12, (byte) 0xF7, (byte) 0xE3, (byte) 0x42, (byte) 0xBD, (byte) 0xDC, (byte) 0xFB, (byte) 0x5D, (byte) 0x79, (byte) 0x4E, (byte) 0x5A, (byte) 0xD6,
                    (byte) 0x54, (byte) 0xD1, (byte) 0xC9, (byte) 0x90, (byte) 0x28, (byte) 0x05, (byte) 0xCF, (byte) 0x5E, (byte) 0x4C, (byte) 0x83, (byte) 0x63, (byte) 0xFB, (byte) 0xC2, (byte) 0x3C, (byte) 0xF6, (byte) 0xAB
            };
            byte[] defaultAuthKey1Id = "AUTH1\0".getBytes();
            byte[] defaultAuthKey1 = {
                    (byte) 0xDA, (byte) 0x2E, (byte) 0x35, (byte) 0xB6, (byte) 0xCB, (byte) 0x96, (byte) 0x2B, (byte) 0x5F, (byte) 0x9F, (byte) 0x34, (byte) 0x1F, (byte) 0xD1, (byte) 0x47, (byte) 0x41, (byte) 0xA0, (byte) 0x4D,
                    (byte) 0xA4, (byte) 0x09, (byte) 0xCE, (byte) 0xE8, (byte) 0x35, (byte) 0x48, (byte) 0x3C, (byte) 0x60, (byte) 0xFB, (byte) 0x13, (byte) 0x91, (byte) 0xE0, (byte) 0x9E, (byte) 0x95, (byte) 0xB2, (byte) 0x7F
            };
            NBDeviceSecurityModel security = NBDeviceSecurityModel.get(device.getCapabilities().securityModel);
            if (security == NBDeviceSecurityModel.Model65200CakOnly) {
                device.openSession(cakId, cak);
            } else if (security == NBDeviceSecurityModel.Model65200CakCdk) {
                try {
                    device.openSession(cdkId, cdk);
                    device.SetBlobParameter(NBDevice.BLOB_PARAMETER_SET_CDK, null);
                    device.closeSession();
                } catch (RuntimeException ex) {
                }
                device.openSession(cakId, cak);
                device.SetBlobParameter(NBDevice.BLOB_PARAMETER_SET_CDK, cdk);
                device.closeSession();
                device.openSession(cdkId, cdk);
            } else if (security == NBDeviceSecurityModel.Model65100) {
                device.openSession(defaultAuthKey1Id, defaultAuthKey1);
            }
        }
    }


    private static Bitmap convertToBitmap(NBDeviceScanFormatInfo formatInfo, byte[] image) {
        IntBuffer buf = IntBuffer.allocate(image.length);
        for (byte pixel : image) {
            int grey = pixel & 0x0ff;
            buf.put(Color.argb(255, grey, grey, grey));
        }
        return Bitmap.createBitmap(buf.array(), formatInfo.getWidth(), formatInfo.getHeight(), Bitmap.Config.ARGB_8888);
    }

    private void showLog(String message, @ColorInt int color) {

        FingerPrintActivity.this.runOnUiThread(() -> {
            Log.d("TEST",message);
            String log = binding.tvLog.getText().toString().trim();
            StringBuilder stringBuilder = new StringBuilder();
            String msg = "<font color='" + color + "'>" + message + "</font><br/>";
            stringBuilder.append(msg + "\n");
            stringBuilder.append(log);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvLog.setText(Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                binding.tvLog.setText(Html.fromHtml(stringBuilder.toString()));
            }
        });
    }

    private void showStatus(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message + "\n");
        // stringBuilder.append(log);
        FingerPrintActivity.this.runOnUiThread(() -> binding.tvResult.setText(stringBuilder.toString()));
    }


}
*/
