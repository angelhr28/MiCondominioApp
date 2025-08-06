package org.angelhr28.micondominio.ui.feature.login

data class LoginUiState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)