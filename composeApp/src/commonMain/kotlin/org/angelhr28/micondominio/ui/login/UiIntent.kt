package org.angelhr28.micondominio.ui.login

sealed class LoginIntent {
    object LoadData : LoginIntent()
}
