package org.angelhr28.micondominio.ui.login

sealed class LoginEvent {
    object OnDataLoaded : LoginEvent()
}
