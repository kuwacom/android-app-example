package dev.kuwa.test_android_navigation.data.api.timeInfo

import dev.kuwa.test_android_navigation.data.model.TimeInfo
import retrofit2.Response
import retrofit2.http.GET

interface TimeInfoService {

    // コルーチン方式
    @GET("timezone/Asia/Tokyo")
    suspend fun getTimeInfo(): Response<TimeInfo> // TimeInfoを返す

}