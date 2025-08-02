package org.angelhr28.micondominio.model

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class Report(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val status: ReportStatus = ReportStatus.PENDING,
    val createAt: String = kotlin.time.Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toString(),
    val updateAt: String = kotlin.time.Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toString(),
    val response: String? = null
)


enum class ReportStatus {
    PENDING,
    DONE;

    companion object {
        fun findFromKey(key: String): ReportStatus {
            return ReportStatus.entries.find { key == it.name } ?: PENDING
        }
    }
}