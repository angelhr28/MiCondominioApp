package org.angelhr28.micondominio.ui.feature.payments

sealed class PaymentsIntent {
    object LoadData : PaymentsIntent()
}
