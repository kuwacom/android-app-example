package dev.kuwa.android_app_example.data.model

import com.squareup.moshi.Json

data class TimeInfo(
    @Json(name = "utc_offset") val utcOffset: String,      // タイムゾーンのオフセット（例: "+09:00"）
    @Json(name = "timezone") val timezone: String,        // タイムゾーン名（例: "Asia/Tokyo"）
    @Json(name = "day_of_week") val dayOfWeek: Int,       // 週の日数（0:日曜日, 1:月曜日, ..., 6:土曜日）
    @Json(name = "day_of_year") val dayOfYear: Int,       // 年の日数（例: 269は年の269日目）
    @Json(name = "datetime") val datetime: String,        // 現在の日時（ISO8601形式）
    @Json(name = "utc_datetime") val utcDatetime: String,  // UTCの現在の日時（ISO8601形式）
    @Json(name = "unixtime") val unixtime: Long,          // Unixタイムスタンプ（例: 1727204703）
    @Json(name = "raw_offset") val rawOffset: Int,         // 生のオフセット（秒単位）
    @Json(name = "week_number") val weekNumber: Int,       // 週番号
    @Json(name = "dst") val dst: Boolean,                  // 夏時間の適用の有無
    @Json(name = "abbreviation") val abbreviation: String,  // タイムゾーンの略称
    @Json(name = "dst_offset") val dstOffset: Int,         // 夏時間のオフセット（秒単位）
    @Json(name = "dst_from") val dstFrom: String?,         // 夏時間開始の日時
    @Json(name = "dst_until") val dstUntil: String?,       // 夏時間終了の日時
    @Json(name = "client_ip") val clientIp: String         // クライアントのIPアドレス
)