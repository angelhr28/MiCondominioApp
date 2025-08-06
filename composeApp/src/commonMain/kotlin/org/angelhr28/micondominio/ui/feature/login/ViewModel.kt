package org.angelhr28.micondominio.ui.feature.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModel {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun onLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _state.update { it.copy(loading = true, error = null) }

            if (email.isBlank() || password.isBlank()) {
                _state.update {
                    it.copy(
                        loading = false,
                        error = "Correo y contraseña son obligatorios."
                    )
                }
                return@launch
            }

            if (email == "admin@admin.com" && password == "123456") {
                _state.update { it.copy(loading = false, success = true) }
            } else {
                _state.update { it.copy(loading = false, error = "Credenciales inválidas.") }
            }
        }
    }
}