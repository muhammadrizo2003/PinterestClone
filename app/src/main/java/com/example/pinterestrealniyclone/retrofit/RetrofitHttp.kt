package com.example.pinterestrealniyclone.retrofit

import com.example.pinterestrealniyclone.retrofit.services.RetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHttp {
    val IS_TESTER = true
    const val SERVER_DEVELOPMENT = "https://api.unsplash.com/"
    val SERVER_PRODUCTION = "https://62219d1bafd560ea69b4e0dd.mockapi.io/"

    private val client = getClient()
    private val retrofit = getRetrofit(client)

    private fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SERVER_DEVELOPMENT)
            .client(client)
            .build()
    }

//    fun <T> createService(service: Class<T>): T {
//        return retrofit.create(service)
//    }

    private fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Authorization", "Client-ID PztvxxoIp2C4oRujcX8IyYu82848vfYzy66Pqa5uLx0")
            chain.proceed(builder.build())
        })
        .build()

//    fun <T> createServiceWithAuth(service: Class<T>?): T {
//
//        val newClient =
//            client.newBuilder().addInterceptor(Interceptor { chain ->
//                var request = chain.request()
//                val builder = request.newBuilder()
//                builder.addHeader(
//                    "Authorization",
//                    "Client-ID PztvxxoIp2C4oRujcX8IyYu82848vfYzy66Pqa5uLx0"
//                )
//                request = builder.build()
//                chain.proceed(request)
//            }).build()
//        val newRetrofit = retrofit.newBuilder().client(newClient).build()
//        return newRetrofit.create(service!!)
//    }

    val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)

    //...

}

