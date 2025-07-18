package org.angelhr28.micondominio.ui.reservations

sealed class ReservationsEvent {
    object OnDataLoaded : ReservationsEvent()
}
