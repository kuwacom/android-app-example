package dev.kuwa.android_app_example.data.api.voicevox

import dev.kuwa.android_app_example.data.model.VoiceVoxSpeaker
import retrofit2.Response
import retrofit2.http.GET

interface VoiceVoxService {

    // コールバック方式
//    @GET("speakers")
//    fun getSample(): Call<List<SampleResponse>> // List<SampleResponse>を返す
//
    // コルーチン方式
    @GET("speakers")
    suspend fun getSpeakers(): Response<List<VoiceVoxSpeaker>> // List<SampleResponse>を返す


}