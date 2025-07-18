package org.angelhr28.micondominio.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.angelhr28.micondominio.navigation.Screen

@Composable
fun HomeScreen(navigator: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        val screens = listOf(
            Screen.Profile to "Perfil",
            Screen.Payments to "Pagos",
            Screen.Reservations to "Reservas",
            Screen.Notifications to "Notificaciones",
            Screen.Reports to "Reportes",
            Screen.Regulations to "Reglamentos",
            Screen.Services to "Servicios"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            screens.forEach { (pantalla, texto) ->
                Button(
                    onClick = { navigator.navigate(pantalla) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = texto,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
