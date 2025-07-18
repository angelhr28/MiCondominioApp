package org.angelhr28.micondominio.ui.payments

sealed class PaymentsEvent {
    object OnDataLoaded : PaymentsEvent()
}
