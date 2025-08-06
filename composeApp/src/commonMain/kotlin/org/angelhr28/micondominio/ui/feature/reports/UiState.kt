package org.angelhr28.micondominio.ui.feature.reports

import org.angelhr28.micondominio.model.Report

data class ReportsUiState(
    val reports: List<Report> = emptyList(),
    val editingReport: Report? = null,
    val titleInput: String = "",
    val descriptionInput: String = ""
)
