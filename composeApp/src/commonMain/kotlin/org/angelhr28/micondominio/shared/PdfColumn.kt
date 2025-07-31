package org.angelhr28.micondominio.shared

import androidx.compose.runtime.Composable

@Composable
expect fun PdfColumn(url: String)

expect suspend fun downloadPdfFromUrl(url: String): String?

expect fun openPdf(path: String)
