package org.angelhr28.micondominio.ui.feature.login

sealed class LoginIntent {
    object LoadData : LoginIntent()
}
