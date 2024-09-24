package dev.kuwa.android_app_example.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class VoiceVoxApiClient {
    // エンドポイントのベースURL 一時的にハードコード
    private val baseUrl = "https://api.kuwa.app/v1/voicevox/"

    // Moshi JSONパーサー
    private val moshiConverter = MoshiConverterFactory.create()

    // Retrofitインスタンス生成
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .addConverterFactory(moshiConverter)
//        .build()

    // lazy 使ってキャッシュしてもいいのかも
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