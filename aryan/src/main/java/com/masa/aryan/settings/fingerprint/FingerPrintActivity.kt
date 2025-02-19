package com.masa.aryan.settings.fingerprint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Base64
import android.util.Log
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.masa.aryan.R
import com.masa.aryan.databinding.ActivityFingerPrintBinding
import com.nextbiometrics.biometrics.NBBiometricsContext
import com.nextbiometrics.biometrics.NBBiometricsExtractResult
import com.nextbiometrics.biometrics.NBBiometricsFingerPosition
import com.nextbiometrics.biometrics.NBBiometricsIdentifyResult
import com.nextbiometrics.biometrics.NBBiometricsSecurityLevel
import com.nextbiometrics.biometrics.NBBiometricsStatus
import com.nextbiometrics.biometrics.NBBiometricsTemplate
import com.nextbiometrics.biometrics.NBBiometricsTemplateType
import com.nextbiometrics.biometrics.NBBiometricsVerifyResult
import com.nextbiometrics.biometrics.event.NBBiometricsScanPreviewEvent
import com.nextbiometrics.biometrics.event.NBBiometricsScanPreviewListener
import com.nextbiometrics.devices.NBDevice
import com.nextbiometrics.devices.NBDeviceScanFormatInfo
import com.nextbiometrics.devices.NBDeviceSecurityModel
import com.nextbiometrics.devices.NBDevices
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.nio.IntBuffer
import java.util.AbstractMap
import java.util.LinkedList
import javax.inject.Inject

@AndroidEntryPoint
class FingerPrintActivity : AppCompatActivity() {
 private var binding: ActivityFingerPrintBinding? = null
 private var device: NBDevice? = null
 var scanFormatInfo: NBDeviceScanFormatInfo? = null

 @Inject
 lateinit var appContext: Context
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  binding = ActivityFingerPrintBinding.inflate(layoutInflater)
  setContentView(binding!!.getRoot())
  binding!!.btnScanAndExtract.setClickable(false)

  enableHost()
  initDevice()




/*
  binding!!.btnEnabledHost.setOnClickListener { v ->


   //Enabled USB HOST
   val result =
    FileUtil.writeFileByString(fingerFile, "2", false)
   showLog(
    if (result) "Enabled Host success" else "Enabled Host failed",
    Color.BLACK
   )
  }*/


/*  binding!!.btnInitDEvice.setOnClickListener { v ->
   Thread(Runnable {
    Thread(
     Runnable {
      try {
       Thread.sleep(2000)
      } catch (e: InterruptedException) {
       throw RuntimeException(e)
      }
      //init devices
      val initResult = initDevices()
      if (!initResult) {
       showLog("Init device failed.", Color.RED)
       return@Runnable
      }
      if (device != null) {
       showLog("DEVICE Model:" + device.toString(), Color.BLACK)
      }
      showLog("Init device success.", Color.GREEN)

     }).start()
   }).start()
  }*/
  binding!!.btnDisEnabledHost.setOnClickListener { v ->
   release()
   FileUtil.writeFileByString(fingerFile, "0", false)
  }
  binding!!.btnScanAndExtract.setClickable(true)
  binding!!.btnScanAndExtract.setOnClickListener { v ->
   Thread { scanAndExtract() }.start()
  }
 }

 private fun scanAndExtract() {
  //Check the status of init device
  showLog("Re Init devices.", Color.BLACK)
  if (device == null || device != null && !device!!.isSessionOpen) {

   val init = initDevices()
   if (init) {
    showLog("Re Init devices success.", Color.GREEN)
   } else {
    showLog("Re Init devices failed.", Color.RED)
    return
   }
  }
  //TODO
  val nbBiometricsContext = NBBiometricsContext(device!!)
  var extractResult: NBBiometricsExtractResult? = null
  showLog("Extracting fingerprint template,please put your finger on sensor.", Color.BLACK)
  extractResult = nbBiometricsContext.extract(
   NBBiometricsTemplateType.ISO,
   NBBiometricsFingerPosition.UNKNOWN,
   scanFormatInfo,
   object : NBBiometricsScanPreviewListener {
    var counter = 0
    override fun preview(event: NBBiometricsScanPreviewEvent) {
     val spoofScore: Int = event.spoofScoreValue
     val image: ByteArray = event.image
     showStatus(
      """
                  extract
                  Status:${event.scanStatus.toString()}
                  """.trimIndent()
     )
     val promote = java.lang.String.format(
      "PREVIEW #%d: Status: %s, Finger detect score: %d, Spoof Score: %d, image %d bytes",
      ++counter, event.getScanStatus().toString(), event.getFingerDetectValue(), spoofScore,
      image?.size ?: 0
     )
     showLog(promote, Color.BLACK)
     if (spoofScore <= MIN_ANTISPOOF_THRESHOLD || spoofScore >= MAX_ANTISPOOF_THRESHOLD) {
      showLog("spoofScore invalid:$spoofScore", Color.RED)
     }
     if (image != null && (device.toString().contains("65210") || device.toString()
       .contains("65200"))
     ) {
      Handler(Looper.getMainLooper()).post {
       binding!!.imageView.setImageBitmap(
        scanFormatInfo?.let { convertToBitmap(it, image) }
       )
      }
     } else {
      Handler(Looper.getMainLooper()).post {
       binding!!.imageView.setImageResource(
        R.mipmap.ic_launcher
       )
      }
     }
    }
   })
  showLog("result:$extractResult", Color.BLACK)
  if (extractResult.getStatus() !== NBBiometricsStatus.OK) {
   return
  }
  //get template info
  val template: NBBiometricsTemplate = extractResult.getTemplate()
  val quality: Int = template.getQuality()
  showLog("Last scan:$quality", Color.BLACK)
  //Start Verify the fingerprint
  val verifyResult = verify(template, device)
  if (!verifyResult) {
   return
  }
  //identify
  val identifyResult = identify(template, device)
  if (!identifyResult) {
   return
  }
  //save template
  val saveResult = saveTemplate(template, device)
  showStatus("SAVE ISO result:$saveResult")
 }

 private fun saveTemplate(template: NBBiometricsTemplate, device: NBDevice?): Boolean {
  val nbBiometricsContext = NBBiometricsContext(device)
  val binaryTemplate: ByteArray = nbBiometricsContext.saveTemplate(template)
  val base64Template = Base64.encodeToString(binaryTemplate, 0)
  Log.d("TEST", "--:$base64Template")
  showLog("Template base64:$base64Template", Color.BLACK)
  val path =
   Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/template.bin"
  showLog("Template store to path: $path", Color.BLACK)
  var fos: FileOutputStream? = null
  return try {
   fos = FileOutputStream(path)
   fos.write(binaryTemplate)
   fos.close()
   true
  } catch (e: FileNotFoundException) {
   showLog("Error:NEXT  Biometrics SDK error:" + e.message, Color.RED)
   e.printStackTrace()
   false
  } catch (e: IOException) {
   showLog("Save ISO error:" + e.message, Color.RED)
   e.printStackTrace()
   false
  } finally {
   if (nbBiometricsContext != null) {
    nbBiometricsContext.dispose()
   }
   if (fos != null) {
    try {
     fos.close()
    } catch (e: IOException) {
     throw RuntimeException(e)
    }
   }
  }
 }

 private fun verify(template: NBBiometricsTemplate, device: NBDevice?): Boolean {
  showLog("2:Verifying fingerprint,pls put your finger on sensor.", Color.BLACK)
  val nbBiometricsContext = NBBiometricsContext(device)
  val nbBiometricsVerifyResult: NBBiometricsVerifyResult = nbBiometricsContext.verify(
   NBBiometricsTemplateType.ISO,
   NBBiometricsFingerPosition.UNKNOWN,
   scanFormatInfo,
   object : NBBiometricsScanPreviewListener {
    var counter = 0
    override fun preview(event: NBBiometricsScanPreviewEvent) {
     val spoofScore: Int = event.spoofScoreValue
     val image: ByteArray = event.image
     showStatus(
      """
                  verify
                  Status:${event.scanStatus.toString()}
                  """.trimIndent()
     )
     val promote = java.lang.String.format(
      "PREVIEW #%d: Status: %s, Finger detect score: %d, Spoof Score: %d, image %d bytes",
      ++counter, event.getScanStatus().toString(), event.getFingerDetectValue(), spoofScore,
      image?.size ?: 0
     )
     showLog(promote, Color.BLACK)
     if (spoofScore <= MIN_ANTISPOOF_THRESHOLD || spoofScore >= MAX_ANTISPOOF_THRESHOLD) {
      showLog("spoofScore invalid:$spoofScore", Color.RED)
     }
     if (image != null && (device.toString().contains("65210") || device.toString()
       .contains("65200"))
     ) {
      Handler(Looper.getMainLooper()).post {
       binding!!.imageView.setImageBitmap(
        scanFormatInfo?.let { convertToBitmap(it, image) }
       )
      }
     } else {
      Handler(Looper.getMainLooper()).post {
       binding!!.imageView.setImageResource(
        R.mipmap.ic_launcher
       )
      }
     }
    }
   },
   template,
   NBBiometricsSecurityLevel.NORMAL
  )
  showLog("Verify result:" + nbBiometricsVerifyResult.getStatus().toString(), Color.BLACK)
  return nbBiometricsVerifyResult.getStatus() === NBBiometricsStatus.OK
 }

 private fun identify(template: NBBiometricsTemplate, device: NBDevice?): Boolean {
  val simpleEntries: MutableList<AbstractMap.SimpleEntry<Any, NBBiometricsTemplate>> =
   LinkedList<AbstractMap.SimpleEntry<Any, NBBiometricsTemplate>>()
  simpleEntries.add(AbstractMap.SimpleEntry<Any, NBBiometricsTemplate>("TEST", template))
  showLog("3:Identifying fingerprint, please put your finger on sensor!", Color.BLACK)
  val nbBiometricsContext = NBBiometricsContext(device)
  val identifyResult: NBBiometricsIdentifyResult = nbBiometricsContext.identify(
   NBBiometricsTemplateType.ISO,
   NBBiometricsFingerPosition.UNKNOWN,
   scanFormatInfo,
   object : NBBiometricsScanPreviewListener {
    var counter = 0
    override fun preview(event: NBBiometricsScanPreviewEvent) {
     val spoofScore: Int = event.getSpoofScoreValue()
     val image: ByteArray = event.getImage()
     showStatus(
      """
                  identify
                  Status:${event.getScanStatus().toString()}
                  """.trimIndent()
     )
     val promote = java.lang.String.format(
      "PREVIEW #%d: Status: %s, Finger detect score: %d, Spoof Score: %d, image %d bytes",
      ++counter, event.getScanStatus().toString(), event.getFingerDetectValue(), spoofScore,
      image?.size ?: 0
     )
     showLog(promote, Color.BLACK)
     if (spoofScore <= MIN_ANTISPOOF_THRESHOLD || spoofScore >= MAX_ANTISPOOF_THRESHOLD) {
      showLog("spoofScore invalid:$spoofScore", Color.RED)
     }
     if (image != null && (device.toString().contains("65210") || device.toString()
       .contains("65200"))
     ) {
      Handler(Looper.getMainLooper()).post {
       binding!!.imageView.setImageBitmap(
        scanFormatInfo?.let { convertToBitmap(it, image) }
       )
      }
     } else {
      Handler(Looper.getMainLooper()).post {
       binding!!.imageView.setImageResource(
        R.mipmap.ic_launcher
       )
      }
     }
    }
   },
   simpleEntries.iterator(),
   NBBiometricsSecurityLevel.NORMAL
  )
  showLog("identify Result:" + identifyResult.status.toString(), Color.BLACK)
  return identifyResult.status == NBBiometricsStatus.OK
 }

 private fun initDevices(): Boolean {
  // Initialize biometric coprocessor
  showLog("Start init devices.", Color.BLACK)
  NBDevices.initialize(applicationContext)
  //Obtain biometric coprocessor info
  showLog("Waiting for a USB device.", Color.BLACK)
  for (i in 0..49) {
   try {
    Thread.sleep(500)
   } catch (e: InterruptedException) {
    throw RuntimeException(e)
   }
   if (NBDevices.getDevices().size !== 0) {
    break
   }
  }
  val nbDevices: Array<NBDevice> = NBDevices.getDevices()
  if (nbDevices.isEmpty()) {
   //TODO no devices
   showLog("Length ==0,No USB devices found,try trying and SPI device.", Color.RED)
   val DEFAULT_SPI_NAME = "/dev/spidev0.0"
   val PIN_OFFSET = 902
   val DEFAULT_AWAKE_PIN_NUMBER = PIN_OFFSET + 69
   val DEFAULT_RESET_PIN_NUMBER = PIN_OFFSET + 12
   val DEFAULT_CHIP_SELECT_PIN_NUMBER = PIN_OFFSET + 18
   try {
    device = NBDevice.connectToSpi(
     DEFAULT_SPI_NAME,
     DEFAULT_AWAKE_PIN_NUMBER,
     DEFAULT_RESET_PIN_NUMBER,
     DEFAULT_CHIP_SELECT_PIN_NUMBER,
     NBDevice.DEVICE_CONNECT_TO_SPI_SKIP_GPIO_INIT_FLAG.toLong()
    )
    if (device == null) {
     showLog("NO SPI devices as well.", Color.RED)
     return false
    }
   } catch (e: Exception) {
    showLog("NO SPI devices as well." + e.message, Color.RED)
    return false
   }
  } else {
   device = nbDevices[0]
  }
  openSession()
  val scanFormatInfos: Array<NBDeviceScanFormatInfo> = device!!.supportedScanFormats
  if (scanFormatInfos.isEmpty()) {
   showLog("No support formats found.", Color.RED)
   return false
  }
  scanFormatInfo = scanFormatInfos[1]
  return true
 }

 override fun onPause() {
  super.onPause()
  release()
 }

 private fun release() {
  if (device != null) {
   device!!.dispose()
   device = null
  }
  NBDevices.terminate()
 }

 private fun openSession() {
  if (device != null && !device!!.isSessionOpen()) {
   val cakId = "DefaultCAKKey1\u0000".toByteArray()
   val cak = byteArrayOf(
    0x05.toByte(),
    0x4B.toByte(),
    0x38.toByte(),
    0x3A.toByte(),
    0xCF.toByte(),
    0x5B.toByte(),
    0xB8.toByte(),
    0x01.toByte(),
    0xDC.toByte(),
    0xBB.toByte(),
    0x85.toByte(),
    0xB4.toByte(),
    0x47.toByte(),
    0xFF.toByte(),
    0xF0.toByte(),
    0x79.toByte(),
    0x77.toByte(),
    0x90.toByte(),
    0x90.toByte(),
    0x81.toByte(),
    0x51.toByte(),
    0x42.toByte(),
    0xC1.toByte(),
    0xBF.toByte(),
    0xF6.toByte(),
    0xD1.toByte(),
    0x66.toByte(),
    0x65.toByte(),
    0x0A.toByte(),
    0x66.toByte(),
    0x34.toByte(),
    0x11.toByte()
   )
   val cdkId = "Application Lock\u0000".toByteArray()
   val cdk = byteArrayOf(
    0x6B.toByte(),
    0xC5.toByte(),
    0x51.toByte(),
    0xD1.toByte(),
    0x12.toByte(),
    0xF7.toByte(),
    0xE3.toByte(),
    0x42.toByte(),
    0xBD.toByte(),
    0xDC.toByte(),
    0xFB.toByte(),
    0x5D.toByte(),
    0x79.toByte(),
    0x4E.toByte(),
    0x5A.toByte(),
    0xD6.toByte(),
    0x54.toByte(),
    0xD1.toByte(),
    0xC9.toByte(),
    0x90.toByte(),
    0x28.toByte(),
    0x05.toByte(),
    0xCF.toByte(),
    0x5E.toByte(),
    0x4C.toByte(),
    0x83.toByte(),
    0x63.toByte(),
    0xFB.toByte(),
    0xC2.toByte(),
    0x3C.toByte(),
    0xF6.toByte(),
    0xAB.toByte()
   )
   val defaultAuthKey1Id = "AUTH1\u0000".toByteArray()
   val defaultAuthKey1 = byteArrayOf(
    0xDA.toByte(),
    0x2E.toByte(),
    0x35.toByte(),
    0xB6.toByte(),
    0xCB.toByte(),
    0x96.toByte(),
    0x2B.toByte(),
    0x5F.toByte(),
    0x9F.toByte(),
    0x34.toByte(),
    0x1F.toByte(),
    0xD1.toByte(),
    0x47.toByte(),
    0x41.toByte(),
    0xA0.toByte(),
    0x4D.toByte(),
    0xA4.toByte(),
    0x09.toByte(),
    0xCE.toByte(),
    0xE8.toByte(),
    0x35.toByte(),
    0x48.toByte(),
    0x3C.toByte(),
    0x60.toByte(),
    0xFB.toByte(),
    0x13.toByte(),
    0x91.toByte(),
    0xE0.toByte(),
    0x9E.toByte(),
    0x95.toByte(),
    0xB2.toByte(),
    0x7F.toByte()
   )
   val security: NBDeviceSecurityModel =
    NBDeviceSecurityModel.get(device!!.getCapabilities().securityModel.toInt())
   if (security === NBDeviceSecurityModel.Model65200CakOnly) {
    device!!.openSession(cakId, cak)
   } else if (security === NBDeviceSecurityModel.Model65200CakCdk) {
    try {
     device!!.openSession(cdkId, cdk)
     device!!.SetBlobParameter(NBDevice.BLOB_PARAMETER_SET_CDK, null)
     device!!.closeSession()
    } catch (ex: RuntimeException) {
    }
    device!!.openSession(cakId, cak)
    device!!.SetBlobParameter(NBDevice.BLOB_PARAMETER_SET_CDK, cdk)
    device!!.closeSession()
    device!!.openSession(cdkId, cdk)
   } else if (security === NBDeviceSecurityModel.Model65100) {
    device!!.openSession(defaultAuthKey1Id, defaultAuthKey1)
   }
  }
 }

 private fun showLog(message: String, @ColorInt color: Int) {
  runOnUiThread {
   Log.d("TEST", message)
   val log: String = binding!!.tvLog.getText().toString().trim()
   val stringBuilder = StringBuilder()
   val msg = "<font color='$color'>$message</font><br/>"
   stringBuilder.append(
    """
        $msg

        """.trimIndent()
   )
   stringBuilder.append(log)
   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    binding!!.tvLog.setText(
     Html.fromHtml(
      stringBuilder.toString(),
      Html.FROM_HTML_MODE_COMPACT
     )
    )
   } else {
    binding!!.tvLog.setText(Html.fromHtml(stringBuilder.toString()))
   }
  }
 }

 private fun showStatus(message: String) {
  val stringBuilder = StringBuilder()
  stringBuilder.append(
   """
          $message

          """.trimIndent()
  )
  // stringBuilder.append(log);
  this@FingerPrintActivity.runOnUiThread { binding!!.tvResult.setText(stringBuilder.toString()) }
 }


 private fun enableHost(){
  val result =
   FileUtil.writeFileByString(fingerFile, "2", false)
  showLog(
   if (result) "Enabled Host success" else "Enabled Host failed",
   Color.BLACK
  )
 }

 private fun initDevice(){
  Thread(Runnable {
   Thread(
    Runnable {
     try {
      Thread.sleep(2000)
     } catch (e: InterruptedException) {
      throw RuntimeException(e)
     }
     //init devices
     val initResult = initDevices()
     if (!initResult) {
      showLog("Init device failed.", Color.RED)
      return@Runnable
     }
     if (device != null) {
      showLog("DEVICE Model:" + device.toString(), Color.BLACK)
     }
     showLog("Init device success.", Color.GREEN)
    }).start()
  }).start()
 }

 companion object {
  //The path  of file which to enabled USB host
  // private static final File fingerFile = new File("/sys/class/fingerprint_ctrl", "fingerprint_en");
  private val fingerFile = File("/sys/class/otg_ctrl", "usb_mode")
  private const val MAX_ANTISPOOF_THRESHOLD = 1000
  private const val MIN_ANTISPOOF_THRESHOLD = 0


  private fun convertToBitmap(formatInfo: NBDeviceScanFormatInfo, image: ByteArray): Bitmap? {
   val buf = IntBuffer.allocate(image.size)
   for (pixel in image) {
    val grey = pixel.toInt() and 0x0ff
    buf.put(Color.argb(255, grey, grey, grey))
   }
   return Bitmap.createBitmap(
    buf.array(),
    formatInfo.width,
    formatInfo.height,
    Bitmap.Config.ARGB_8888
   )
  }


 }



}
