package dev.kuwa.test_android_navigation.data.repository

import dev.kuwa.test_android_navigation.data.api.TimeInfoApiClient
import dev.kuwa.test_android_navigation.data.api.timeInfo.TimeInfoService
import dev.kuwa.test_android_navigation.data.model.TimeInfo

class TimeInfoRepository() {
    private val apiClient = TimeInfoApiClient()

    private val service = apiClient.createService(TimeInfoService::class.java)

    suspend fun getTimeInfo(): Result<TimeInfo> {
        return try {
            // RetrofitのAPI呼び出しを非同期で実行
            val response = service.getTimeInfo()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
                // 中身が空だったりした時
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            } else {
                // エラー時、エラーメッセージを含むResultを返す
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            // ネットワークエラーなどの例外を捕捉
            Result.failure(e)
        }
    }

}
