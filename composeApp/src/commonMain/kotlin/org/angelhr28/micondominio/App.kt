package org.angelhr28.micondominio

import androidx.compose.runtime.Composable
import org.angelhr28.micondominio.navigation.AppNavGraph
import org.angelhr28.micondominio.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        AppTheme {
            AppNavGraph()
        }
    }
}

