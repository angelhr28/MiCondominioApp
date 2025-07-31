package org.angelhr28.micondominio.ui.regulations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.angelhr28.micondominio.shared.PdfColumn
import org.angelhr28.micondominio.shared.downloadPdfFromUrl
import org.angelhr28.micondominio.shared.openPdf
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegulationsScreen(onBackPress: () -> Unit = {}) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isDownloading by remember { mutableStateOf(false) }
    val pdfUrl = "https://farugby.com/wp-content/uploads/Test-de-Reglamento-Mayo-02-1.pdf"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reglamentos") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Volver")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            DownloadPdfFab(
                enabled = !isDownloading,
                isLoading = isDownloading
            ) {
                scope.launch {
                    isDownloading = true
                    val path = downloadPdfFromUrl(pdfUrl)
                    isDownloading = false
                    if (path != null) {
                        openPdf(path)
                    } else {
                        snackbarHostState.showSnackbar("Error al descargar el PDF")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            PdfColumn(url = pdfUrl)
        }
    }
}

@Composable
fun DownloadPdfFab(
    enabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val alpha = if (enabled) 1f else 0.4f

    FloatingActionButton(
        onClick = { if (enabled) onClick() },
        modifier = Modifier.alpha(alpha)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Icon(Icons.Default.Download, contentDescription = "Descargar PDF")
        }
    }
}

@Preview
@Composable
fun PreviewRegulationsScreen() {
    RegulationsScreen()
}
