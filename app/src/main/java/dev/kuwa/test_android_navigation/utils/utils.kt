package dev.kuwa.test_android_navigation.utils

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.TypedValue
import java.time.Duration
import java.time.LocalDateTime

/**
 * 指定された年がうるう年かどうかを確認します。
 *
 * @param year 確認する年
 * @return 年がうるう年であれば true、そうでなければ false
 */
fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
}

/**
 * 指定された年と月に対して、月の最大に数を返します。
 *
 * @param year 確認する年
 * @param month 確認する月 (1 から 12 の間であるべき)
 * @return 月の日数
 */
fun getDaysInMonth(year: Int, month: Int): Int {
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31 // 31日の日数がある月
        4, 6, 9, 11 -> 30 // 30日の日数がある月
        2 -> if (isLeapYear(year)) 29 else 28 // 2月の日数（うるう年を考慮）
        else -> throw IllegalArgumentException("Invalid month: $month") // 無効な月
    }
}

/**
 * 通知設定画面へ転送します
 */
fun openNotificationSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
    }
    context.startActivity(intent)
}

/**
 * ログイン処理のモック
 */
fun loginLoadingMock(onLoginComplete: () -> Unit) {
    // 擬似的に2秒後に処理完了
    Handler(Looper.getMainLooper()).postDelayed({
        onLoginComplete()
    }, 2000) // 2秒待機
}

/**
 *  dp から px をもとめる
 */
fun dpToPx(context: Context, dp: Int): Int {
    val resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    ).toInt()
}

/**
 *  現在時刻からひと月前までの範囲でランダムな時間を返す
 */
fun getTimeDiffer(inputTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    val duration = Duration.between(inputTime, now)  // 時間差計算

    val seconds = duration.seconds
    val minutes = duration.toMinutes()
    val hours = duration.toHours()
    val days = duration.toDays()

    return when {
        seconds < 60 -> "$seconds 秒前"  // 60秒未満なら「秒前」
        minutes < 60 -> "$minutes 分前"  // 60分未満なら「分前」
        hours < 24 -> "$hours 時間前"  // 24時間未満なら「時間前」
        else -> "$days 日前"  // それ以上なら「日前」
    }
}


/**
 * Listをページングします
 *
 * ページ番号は0始まりと考える
 *
 * ページ番号が1始まりの場合は`pageNumber`に入れる値を`-1`すること
 *
 * 例
 * ```
 *  val stringItems = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew", "Kiwi", "Lemon")
 *  val pageSize = 3
 *  val pageNumber = 2
 *
 *  val pagedStringItems = getPagedItems(stringItems, pageNumber, pageSize)
 *  println("Page $pageNumber: $pagedStringItems")
 * ```
 *
 * @param items ページングしたいList
 * @param pageNumber 取得するページ番号
 * @param pageSize 1ページ当たりのitem数
 */
fun <ListType> getPagedItems(items: List<ListType>, pageNumber: Int, pageSize: Int): List<ListType> {
    val fromIndex = pageNumber * pageSize
    val toIndex = minOf(fromIndex + pageSize, items.size) // 上限をリストのサイズに設定

    // サブリストを取得
    return if (fromIndex < items.size) {
        items.subList(fromIndex, toIndex)
    } else {
        emptyList() // リストの範囲外の場合は空リストを返す
    }
}