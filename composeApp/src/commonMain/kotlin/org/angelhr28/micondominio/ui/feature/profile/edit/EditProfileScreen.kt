package org.angelhr28.micondominio.ui.feature.profile.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.angelhr28.micondominio.ui.component.CustomTopAppBar
import org.angelhr28.micondominio.ui.feature.profile.ProfileImagePicker
import org.angelhr28.micondominio.ui.feature.profile.component.DocumentDropdown
import org.angelhr28.micondominio.ui.feature.profile.component.GenderDropdown
import org.angelhr28.micondominio.ui.feature.profile.component.RoleDropdown
import org.angelhr28.micondominio.ui.feature.profile.component.SectionTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(onBackPress: () -> Unit = {}) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Masculino") }
    var documentType by remember { mutableStateOf("DNI") }
    var documentNumber by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var building by remember { mutableStateOf("") }
    var apartment by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Propietario") }
    var hasPets by remember { mutableStateOf(false) }
    var residents by remember { mutableStateOf("1") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Edita tu información",
                onBackClick = onBackPress
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                ProfileImagePicker()
            }

            item {
                SectionTitle("Datos Personales")
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellido Paterno") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = middleName,
                        onValueChange = { middleName = it },
                        label = { Text("Apellido Materno") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    GenderDropdown(gender) { gender = it }
                }
            }

            item {
                SectionTitle("Información de Contacto")
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DocumentDropdown(documentType) { documentType = it }
                    OutlinedTextField(
                        value = documentNumber,
                        onValueChange = { documentNumber = it },
                        label = { Text("Número de Documento") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                SectionTitle("Relación con el Condominio")
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    RoleDropdown(role) { role = it }
                    OutlinedTextField(
                        value = building,
                        onValueChange = { building = it },
                        label = { Text("Torre / Edificio") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = apartment,
                        onValueChange = { apartment = it },
                        label = { Text("Departamento") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = residents,
                        onValueChange = { residents = it },
                        label = { Text("Habitantes") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = hasPets, onCheckedChange = { hasPets = it })
                        Text("¿Tiene mascotas?", modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Cambios guardados correctamente")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Cambios")
                }
            }
        }
    }
}
