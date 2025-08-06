package org.angelhr28.micondominio.ui.feature.forgotpassword

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.angelhr28.micondominio.ui.component.CustomTopAppBar

@ExperimentalMaterial3Api
@Composable
fun ForgotPasswordScreen(onBackPress: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Recuperar contraseña",
                onBackClick = onBackPress
            )
        }
    ) { innerPadding ->

    }
}