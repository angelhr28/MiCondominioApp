package org.angelhr28.micondominio.ui.reports

sealed interface ReportsEvent {
    data class ShowMessage(val message: String) : ReportsEvent
}