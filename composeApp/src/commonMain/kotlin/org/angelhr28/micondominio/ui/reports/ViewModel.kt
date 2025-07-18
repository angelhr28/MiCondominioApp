package org.angelhr28.micondominio.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock.System
import org.angelhr28.micondominio.model.Report
import org.angelhr28.micondominio.model.ReportStatus

class ReportsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReportsUiState())
    val uiState: StateFlow<ReportsUiState> = _uiState.asStateFlow()

    private val _event = Channel<ReportsEvent>()
    val event = _event.receiveAsFlow()

    fun onIntent(intent: ReportsIntent) {
        when (intent) {
            is ReportsIntent.UpdateTitle -> {
                _uiState.update { it.copy(titleInput = intent.title) }
            }

            is ReportsIntent.UpdateDescription -> {
                _uiState.update { it.copy(descriptionInput = intent.description) }
            }

            is ReportsIntent.PreviewStatus -> {
                _uiState.update { it.copy(statusPreview = intent.status) }
            }

            is ReportsIntent.EditReport -> {
                val report = _uiState.value.reports.find { it.id == intent.id } ?: return
                _uiState.update {
                    it.copy(
                        titleInput = report.title,
                        descriptionInput = report.description,
                        editingReportId = report.id,
                        statusPreview = report.status
                    )
                }
            }

            is ReportsIntent.DeleteReport -> {
                _uiState.update {
                    it.copy(reports = it.reports.filterNot { r -> r.id == intent.id })
                }
                emitMessage("Reporte eliminado")
            }

            is ReportsIntent.ToggleStatus -> {
                _uiState.update { current ->
                    val updatedList = current.reports.map {
                        if (it.id == intent.id) {
                            it.copy(
                                status = if (it.status == ReportStatus.PENDING)
                                    ReportStatus.DONE else ReportStatus.PENDING
                            )
                        } else it
                    }
                    current.copy(reports = updatedList)
                }
            }

            is ReportsIntent.SubmitReport -> {
                val current = _uiState.value
                val title = current.titleInput.trim()
                val description = current.descriptionInput.trim()

                if (title.isBlank() || description.isBlank()) {
                    emitMessage("Completa todos los campos")
                    return
                }

                if (current.editingReportId != null) {
                    _uiState.update {
                        val updated = it.reports.map { report ->
                            if (report.id == current.editingReportId) {
                                report.copy(
                                    title = title,
                                    description = description
                                )
                            } else report
                        }
                        it.copy(
                            reports = updated,
                            titleInput = "",
                            descriptionInput = "",
                            editingReportId = null,
                        )
                    }
                    emitMessage("Reporte actualizado")
                } else {
                    val newReport = Report(
                        title = title,
                        description = description,
                        status = current.statusPreview,
                        response = null
                    )
                    _uiState.update {
                        it.copy(
                            reports = it.reports + newReport,
                            titleInput = "",
                            descriptionInput = "",
                            editingReportId = null,
                        )
                    }
                    emitMessage("Reporte agregado")
                }
            }
        }
    }

    private fun emitMessage(message: String) {
        viewModelScope.launch {
            _event.send(ReportsEvent.ShowMessage(message))
        }
    }
}
