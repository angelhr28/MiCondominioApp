package org.angelhr28.micondominio.ui.feature.reports

sealed interface ReportsEvent {
    data class ShowMessage(val message: String) : ReportsEvent
}