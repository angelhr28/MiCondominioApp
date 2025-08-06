package org.angelhr28.micondominio.ui.feature.reports

import org.angelhr28.micondominio.model.Report

sealed interface ReportsIntent {
    data class CreatedReport(val report: Report) : ReportsIntent
    data class EditReport(val report: Report) : ReportsIntent
    data class DeleteReport(val id: Long) : ReportsIntent
}