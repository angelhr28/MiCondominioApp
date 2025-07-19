package org.angelhr28.micondominio.ui.reports

import org.angelhr28.micondominio.model.Report
import org.angelhr28.micondominio.model.ReportStatus

data class ReportsUiState(
    val reports: List<Report> = emptyList(),
    val editingReport: Report? = null,
    val titleInput: String = "",
    val descriptionInput: String = ""
)
