package dev.kuwa.android_app_example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat

abstract class Notification(private val context: Context) {
    protected abstract val channelId: String
    protected abstract val channelName: String
    protected abstract val channelDescription: String
    protected abstract val importance: Int

    // 通知チャンネルの作成
    open fun createChannel() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (!isChannelExists(notificationManager)) {
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    // チャンネルが存在するか
    fun isChannelExists(notificationManager: NotificationManager): Boolean {
        return notificationManager.notificationChannels.any { it.id == channelId }
    }

    // NotificationBuilderの取得
    fun getNotificationBuilder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
    }

    // NotificationBuilderを使ってNotificationを送信
    fun sendNotificationUseNotificationBuilder(
        notificationId: Int,
        notificationBuilder: NotificationCompat.Builder
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    // 引数から簡単にNotificationを送信
    open fun sendNotification(
        notificationId: Int,
        title: String,
        content: String,
        largeIconResId: Int? = null,
        pendingIntent: PendingIntent? = null
    ) {
        val notificationBuilder = getNotificationBuilder()
//            .setSmallIcon(R.drawable.notificationlogo) // 通知ロゴ(白黒)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_SYSTEM)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .apply {
                largeIconResId?.let {
                    val largeIcon: Bitmap = BitmapFactory.decodeResource(context.resources, it)
                    setLargeIcon(largeIcon)
                }
                pendingIntent?.let {
                    setContentIntent(it)
                }
            }

        sendNotificationUseNotificationBuilder(notificationId, notificationBuilder)
    }

}
