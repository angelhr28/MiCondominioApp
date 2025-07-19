package org.angelhr28.micondominio.model

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Report(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val status: ReportStatus = ReportStatus.PENDING,
    val createAt: String = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toString(),
    val updateAt: String = Clock.System.now()
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