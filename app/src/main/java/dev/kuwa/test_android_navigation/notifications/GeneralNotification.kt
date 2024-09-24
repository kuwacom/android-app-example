package dev.kuwa.test_android_navigation.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import dev.kuwa.test_android_navigation.R

class GeneralNotification(private val context: Context) : Notification(context) {
    override val channelId = "GeneralNotification"
    override val channelName = "TEST"
    override val channelDescription = "一般の通知"
    override val importance = NotificationManager.IMPORTANCE_HIGH

    // 通知を設定して送信するメソッド
    override fun sendNotification(
        notificationId: Int,
        title: String,
        content: String,
        largeIconResId: Int?, // 画像リソースID（オプション）
        pendingIntent: PendingIntent?
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