package org.angelhr28.micondominio.ui.reports

import org.angelhr28.micondominio.model.ReportStatus

sealed interface ReportsIntent {
    data object SubmitReport : ReportsIntent

    data class UpdateTitle(val title: String) : ReportsIntent
    data class UpdateDescription(val description: String) : ReportsIntent

    data class PreviewStatus(val status: ReportStatus) : ReportsIntent

    data class EditReport(val id: String) : ReportsIntent
    data class DeleteReport(val id: String) : ReportsIntent
    data class ToggleStatus(val id: String) : ReportsIntent
}