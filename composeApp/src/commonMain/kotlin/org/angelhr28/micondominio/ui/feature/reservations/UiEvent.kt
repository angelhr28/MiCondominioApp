package org.angelhr28.micondominio.ui.feature.reservations

sealed class ReservationsEvent {
    object OnDataLoaded : ReservationsEvent()
}
