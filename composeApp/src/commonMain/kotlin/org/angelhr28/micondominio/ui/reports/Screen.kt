package org.angelhr28.micondominio.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import org.angelhr28.micondominio.model.ReportStatus
import org.angelhr28.micondominio.ui.reports.ReportsIntent.DeleteReport
import org.angelhr28.micondominio.ui.reports.ReportsIntent.EditReport
import org.angelhr28.micondominio.ui.reports.component.ReportCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(viewModel: ReportsViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is ReportsEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Mis Reportes") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onIntent(ReportsIntent.UpdateTitle(""))
                viewModel.onIntent(ReportsIntent.UpdateDescription(""))
                viewModel.onIntent(ReportsIntent.PreviewStatus(ReportStatus.PENDING))
                showSheet = true
            }) {
                Icon(Icons.Outlined.Add, contentDescription = "Agregar reporte")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.reports) { report ->
                    ReportCard(
                        report = report,
                        onEdit = {
                            viewModel.onIntent(EditReport(report.id))
                            showSheet = true
                        },
                        onDelete = {
                            viewModel.onIntent(DeleteReport(report.id))
                        }
                    )
                }
            }
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = if (state.editingReportId != null) "✏️ Editar Reporte" else "📝 Nuevo Reporte",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = state.titleInput,
                    onValueChange = { viewModel.onIntent(ReportsIntent.UpdateTitle(it)) },
                    label = { Text("Título") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.descriptionInput,
                    onValueChange = { viewModel.onIntent(ReportsIntent.UpdateDescription(it)) },
                    label = { Text("Descripción") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 5,
                )

                Spacer(Modifier.height(12.dp))

                AssistChip(
                    enabled = true,
                    onClick = {},
                    label = {
                        Text(
                            text = if (state.statusPreview == ReportStatus.DONE) "✅ Atendido" else "⏳ Pendiente",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.padding(top = 4.dp),
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = MaterialTheme.colorScheme.primary
                    )
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        scope.launch {
                            viewModel.onIntent(ReportsIntent.SubmitReport)
                            showSheet = false // cierre inmediato
                        }
                    },
                    enabled = state.isFormValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Icon(
                        imageVector = if (state.editingReportId != null) Icons.Outlined.Edit else Icons.Outlined.Add,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (state.editingReportId != null) "Actualizar Reporte" else "Agregar Reporte"
                    )
                }

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
