package com.fanap.corepos.tms.utils

import com.fanap.corepos.tms.repository.TmsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object TmsRetrofitBuilder {

    fun getTmsServices(token: String = "", url : String = ""): TmsService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(token)).build()
            .create(TmsService::class.java)
    }

    private fun getClient(token: String = "", timeout: Long = 60000): OkHttpClient {
        return if (token == "") {
            val builder = OkHttpClient.Builder().connectTimeout(timeout, TimeUnit.MILLISECONDS).readTimeout(timeout, TimeUnit.MILLISECONDS).writeTimeout(timeout, TimeUnit.MILLISECONDS)

//            val headerInterceptor = Interceptor { chain: Interceptor.Chain ->
//                val original = chain.request()
//                val request = original.newBuilder().method(original.method, original.body).build()
//                chain.proceed(request)
//            }


            val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

            //builder.addInterceptor(headerInterceptor)
            builder.build()
        } else {
            val builder = OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)

            builder.addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").addHeader("Accept", "application/json").build()
                chain.proceed(request)
            }

//            val headerInterceptor = Interceptor { chain: Interceptor.Chain ->
//                val original = chain.request()
//                val request = original.newBuilder().header("Authorization", "Bearer $token").method(original.method, original.body).build()
//                Log.e("TAG_REQUEST", request.body.toString())
//                chain.proceed(request)
//            }

            val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

            //builder.addInterceptor(headerInterceptor)
            builder.build()
        }
    }
}