package dev.kuwa.test_android_navigation.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TimeInfoApiClient {
    // エンドポイントのベースURL 一時的にハードコード
    private val baseUrl = "https://worldtimeapi.org/api/"

    // Moshi JSONパーサー
    private val moshiConverter = MoshiConverterFactory.create()

    // lazy 使ってキャッシュ
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverter)
            .build()
    }

    fun <ServiceType> createService(service: Class<ServiceType>): ServiceType {
        return retrofit.create(service)
    }
}