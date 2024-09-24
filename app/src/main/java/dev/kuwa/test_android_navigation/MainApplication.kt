package dev.kuwa.test_android_navigation

import android.app.Application
import android.util.Log
import dev.kuwa.test_android_navigation.notifications.GeneralNotification
import dev.kuwa.test_android_navigation.notifications.InfoNotification


class MainApplication : Application() {
    // グローバル変数のゲッターとセッター
    // グローバル変数
    private var isLoggedIn: Boolean = false

    override fun onCreate() {
        super.onCreate()

        // お知らせ通知チャンネル設定
        val infoNotification = InfoNotification(this)
        infoNotification.createChannel()
        // 通常通知チャンネル設定
        val generalNotification = GeneralNotification(this)
        generalNotification.createChannel()

        // アプリケーションの初期化処理
        Log.d("MainApplication", "Application Started")

        isLoggedIn = false
    }

    fun isLoggedIn(): Boolean {
        return isLoggedIn
    }

    fun setLoggedIn(loggedIn: Boolean) {
        isLoggedIn = loggedIn
    }
}