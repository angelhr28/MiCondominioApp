package org.angelhr28.micondominio.ui.feature.reservations

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.angelhr28.micondominio.ui.component.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationsScreen(onBackPress: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Mis Reservas",
                onBackClick = onBackPress
            )
        }
    ) { innerPadding ->

    }
}