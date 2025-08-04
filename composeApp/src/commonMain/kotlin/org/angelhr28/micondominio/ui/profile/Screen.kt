package org.angelhr28.micondominio.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(onBackPress: () -> Unit = {}) {
     Scaffold(
          topBar = {
               TopAppBar(
                    title = { Text("Mi Perfil") },
                    navigationIcon = {
                         IconButton(onClick = onBackPress) {
                              Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Volver")
                         }
                    }
               )
          }
     ) { innerPadding ->

     }
}