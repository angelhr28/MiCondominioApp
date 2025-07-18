package org.angelhr28.micondominio.ui.reports

import org.angelhr28.micondominio.model.Report
import org.angelhr28.micondominio.model.ReportStatus

data class ReportsUiState(
    val reports: List<Report> = emptyList(),
    val titleInput: String = "",
    val descriptionInput: String = "",
    val editingReportId: String? = null,
    val statusPreview: ReportStatus = ReportStatus.PENDING
) {
    val isFormValid: Boolean
        get() = titleInput.isNotBlank() && descriptionInput.isNotBlank()
}
