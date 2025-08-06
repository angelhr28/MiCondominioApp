package org.angelhr28.micondominio.ui.feature.login

sealed class LoginEvent {
    object OnDataLoaded : LoginEvent()
}
