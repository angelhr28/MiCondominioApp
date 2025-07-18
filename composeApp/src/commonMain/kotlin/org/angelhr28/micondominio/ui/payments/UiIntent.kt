package org.angelhr28.micondominio.ui.payments

sealed class PaymentsIntent {
    object LoadData : PaymentsIntent()
}
