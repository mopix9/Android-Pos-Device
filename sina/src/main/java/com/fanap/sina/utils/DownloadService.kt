package com.fanap.sina.utils

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.io.*
import java.lang.Exception
import java.net.URL
import java.util.*


class DownloadService : IntentService("DownloadService") {

    private val URL = "PARAM1"

    companion object{
        var onProgress: MutableLiveData<Int>? = null
        var onFinish: MutableLiveData<Boolean>? = null

        fun startDownload(context: Context, url: String?) {
            onProgress = MutableLiveData()
            onFinish = MutableLiveData()
            val intent = Intent(context, DownloadService::class.java)
            intent.putExtra("PARAM1", url)
            context.startService(intent)
        }

    }

    private var shared: SharedPreferences? = null

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            shared = applicationContext.getSharedPreferences("DATA", MODE_PRIVATE)
            val url = intent.getStringExtra(URL)
            try {
                doInBackground(url)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    private fun doInBackground(ur: String?) {
        val file = getFile(ur)
        var fileLength = 0
        var input: InputStream? = null
        var output: OutputStream? = null
        try {
            val url = URL(ur)
            val connection = url.openConnection()
            connection.setRequestProperty("Range", "bytes=" + getRange(file) + "-")
            connection.connectTimeout = 30000
            connection.readTimeout = 30000
            connection.connect()
            Log.d(
                "DOW",
                (connection.getHeaderField("Accept-Ranges") == "bytes").toString()
            )
            fileLength = connection.contentLength
            Log.d("DOW", "All: $fileLength")
            input = BufferedInputStream(connection.getInputStream())
            output = FileOutputStream(file, true) // for multi part download set true
            val data = ByteArray(1024)
            var total: Long = 0
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                total += count.toLong()
                Log.d("DOW", total.toString())
                onProgress!!.postValue((total * 100 / fileLength).toInt())
                output.write(data, 0, count)
            }
            val editor = shared!!.edit()
            editor.putString("Apk", file.path)
            editor.apply()
            onFinish!!.postValue(true)
        } catch (e: Exception) {
            e.printStackTrace()
            e.printStackTrace()
            output?.flush()
            if (fileLength != 0) onFinish!!.postValue(file.length() == fileLength.toLong()) else onFinish!!.postValue(
                false
            )
        } finally {
            if (output != null) {
                output.flush()
                output.close()
            }
            input?.close()
        }
    }

    private fun getFile(url: String?): File {
        val words = url!!.split("/".toRegex()).toTypedArray()
        val fileName = words[words.size - 1] + ".apk"
        val directory = File(Environment.getExternalStorageDirectory(), "/apps")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return File(directory, fileName)
    }

    private fun getRange(file: File): Long {
        return file.length()
    }
}