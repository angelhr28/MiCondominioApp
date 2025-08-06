package org.angelhr28.micondominio.ui.feature.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.angelhr28.micondominio.domain.usecase.AddReportUseCase
import org.angelhr28.micondominio.domain.usecase.DeleteReportUseCase
import org.angelhr28.micondominio.domain.usecase.EditReportUseCase
import org.angelhr28.micondominio.domain.usecase.GetAllReportsUseCase
import org.angelhr28.micondominio.model.Report
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ReportsViewModel(
    private val getAllReportsUseCase: GetAllReportsUseCase,
    private val addReportUseCase: AddReportUseCase,
    private val editReportUseCase: EditReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportsUiState())
    val uiState: StateFlow<ReportsUiState> = _uiState.asStateFlow()

    private val _event = Channel<ReportsEvent>()
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch {
            getAllReportsUseCase.invoke().collect { reports ->
                _uiState.update { it.copy(reports = reports) }
            }
        }
    }

    fun startEditing(report: Report) {
        _uiState.update {
            it.copy(
                editingReport = report,
                titleInput = report.title,
                descriptionInput = report.description
            )
        }
    }

    fun cancelEditing() {
        _uiState.update {
            it.copy(
                editingReport = null,
                titleInput = "",
                descriptionInput = ""
            )
        }
    }

    fun updateTitle(title: String) {
        _uiState.update { it.copy(titleInput = title) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(descriptionInput = description) }
    }

    fun submit() {
        val state = _uiState.value

        val report = if (state.editingReport == null) {
            Report(
                title = state.titleInput,
                description = state.descriptionInput,
            )
        } else {
            state.editingReport.copy(
                title = state.titleInput,
                description = state.descriptionInput,
            )
        }

        viewModelScope.launch {
            if (state.editingReport == null) {
                addReportUseCase.invoke(report)
                emitMessage("Reporte agregado")
            } else {
                editReportUseCase.invoke(report)
                emitMessage("Reporte actualizado")
            }
            cancelEditing()
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            deleteReportUseCase.invoke(id)
            emitMessage("Reporte eliminado")
        }
    }

    private fun emitMessage(msg: String) {
        viewModelScope.launch {
            _event.send(ReportsEvent.ShowMessage(msg))
        }
    }
}

class ReportsViewModelProvider : KoinComponent {
    fun getViewModel(): ReportsViewModel = get()
}
