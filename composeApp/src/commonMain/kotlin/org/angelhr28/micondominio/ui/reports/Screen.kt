package org.angelhr28.micondominio.ui.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.angelhr28.micondominio.model.Report
import org.angelhr28.micondominio.theme.AppTheme
import org.angelhr28.micondominio.ui.reports.component.GenericBottomSheet
import org.angelhr28.micondominio.ui.reports.component.ReportCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@ExperimentalMaterial3Api
@Composable
fun ReportsScreen(onbackPress: () -> Unit) {
    val viewModel = remember { ReportsViewModelProvider().getViewModel() }

    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is ReportsEvent.ShowMessage -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    ReportsStateless(
        state = state,
        showSheet = showSheet,
        snackbarHostState = snackbarHostState,
        onAddClick = {
            viewModel.cancelEditing()
            showSheet = true
        },
        onEditClick = {
            viewModel.startEditing(it)
            showSheet = true
        },
        onDeleteClick = { viewModel.delete(it) },
        onDismissSheet = { showSheet = false },
        onSubmit = {
            scope.launch {
                viewModel.submit()
                showSheet = false
            }
        },
        onTitleChange = viewModel::updateTitle,
        onDescriptionChange = viewModel::updateDescription,
        onBackPress = onbackPress
    )
}


@ExperimentalMaterial3Api
@Composable
fun ReportsStateless(
    state: ReportsUiState,
    showSheet: Boolean,
    snackbarHostState: SnackbarHostState,
    onAddClick: () -> Unit,
    onEditClick: (Report) -> Unit,
    onDeleteClick: (Long) -> Unit,
    onDismissSheet: () -> Unit,
    onSubmit: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBackPress: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reportes") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Volver")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar reporte")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(state.reports) { report ->
                    ReportCard(
                        report = report,
                        onEdit = { onEditClick(report) },
                        onDelete = { onDeleteClick(report.id) }
                    )
                }
            }
        }
    }

    if (showSheet) {
        val isEditing = state.editingReport != null
        val titleInput = state.titleInput
        val descriptionInput = state.descriptionInput
        val onDismiss = onDismissSheet

        GenericBottomSheet(
            sheetState = sheetState,
            title = if (isEditing) "✏️ Editar Reporte" else "📝 Nuevo Reporte",
            onDismissRequest = onDismiss,
            primaryButtonText = if (isEditing) "Actualizar Reporte" else "Agregar Reporte",
            onPrimaryClick = onSubmit,
            isPrimaryEnabled = titleInput.isNotBlank() && descriptionInput.isNotBlank(),
            primaryButtonIcon = if (isEditing) Icons.Default.Edit else Icons.Default.Add,
        ) {
            OutlinedTextField(
                value = titleInput,
                onValueChange = onTitleChange,
                label = { Text("Título") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = state.descriptionInput,
                onValueChange = onDescriptionChange,
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun ReportsStatelessPreview() {
    val previewState = ReportsUiState(
        reports = listOf(
            Report(
                id = 1,
                title = "Luz pasillo",
                description = "No hay luz en el segundo piso",
                response = null,
            ),
            Report(
                id = 2,
                title = "Fuga agua",
                description = "Fuga en caño del baño común",
                response = "En revisión",
            )
        ),
        titleInput = "Título de prueba",
        descriptionInput = "Descripción larga de prueba para observar cómo se ve en el formulario.",
        editingReport = null
    )
    AppTheme(useDarkTheme = true) {
        ReportsStateless(
            state = previewState,
            showSheet = false,
            snackbarHostState = remember { SnackbarHostState() },
            onAddClick = {},
            onEditClick = {},
            onDeleteClick = {},
            onDismissSheet = {},
            onSubmit = {},
            onTitleChange = {},
            onDescriptionChange = {},
        ) {}
    }
}
