package org.angelhr28.micondominio.model

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Report @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val title: String,
    val description: String,
    val timestamp: String = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        .toString(),
    val status: ReportStatus = ReportStatus.PENDING,
    val response: String? = null
)


enum class ReportStatus {
    PENDING,
    DONE
}