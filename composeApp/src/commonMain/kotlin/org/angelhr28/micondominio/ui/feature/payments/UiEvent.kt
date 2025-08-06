package org.angelhr28.micondominio.ui.feature.payments

sealed class PaymentsEvent {
    object OnDataLoaded : PaymentsEvent()
}
