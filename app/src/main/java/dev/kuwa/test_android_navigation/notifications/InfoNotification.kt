package dev.kuwa.test_android_navigation.notifications

import android.app.NotificationManager
import android.content.Context

class InfoNotification(private val context: Context) : Notification(context) {
    override val channelId = "InfoNotification"
    override val channelName = "TEST - Info"
    override val channelDescription = "お知らせ通知"
    override val importance = NotificationManager.IMPORTANCE_LOW
}