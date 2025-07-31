package org.angelhr28.micondominio.shared

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

private lateinit var appContext: Context

fun initPdfHandlerContext(context: Context) {
    appContext = context.applicationContext
}

@Composable
actual fun PdfColumn(url: String) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(url),
        isZoomEnable = true,
        isAccessibleEnable = true
    )

    if (!pdfState.isLoaded) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier.fillMaxSize()
    )
}

actual suspend fun downloadPdfFromUrl(url: String): String? = withContext(Dispatchers.IO) {
    runCatching {
        val input = URL(url).openStream()
        val file =
            File(appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "document.pdf")
        input.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        file.absolutePath
    }.getOrElse {
        it.printStackTrace()
        null
    }
}

actual fun openPdf(path: String) {
    val file = File(path)
    val uri: Uri =
        FileProvider.getUriForFile(appContext, "${appContext.packageName}.provider", file)
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    appContext.startActivity(intent)
}
