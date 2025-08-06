package org.angelhr28.micondominio.ui.feature.services

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.angelhr28.micondominio.ui.component.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(onBackPress: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Mis Servicios",
                onBackClick = onBackPress
            )
        }
    ) { innerPadding ->

    }
}
