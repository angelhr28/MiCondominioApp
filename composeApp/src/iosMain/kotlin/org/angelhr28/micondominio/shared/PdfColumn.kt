package org.angelhr28.micondominio.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.NSUserDomainMask
import platform.Foundation.downloadTaskWithURL
import platform.PDFKit.PDFDocument
import platform.PDFKit.PDFView
import platform.UIKit.UIApplication
import platform.UIKit.UIDocumentInteractionController
import platform.UIKit.UIDocumentInteractionControllerDelegateProtocol
import platform.UIKit.UIViewController
import platform.darwin.NSObject
import kotlin.coroutines.resume

@Composable
actual fun PdfColumn(url: String) {
    var document by remember { mutableStateOf<PDFDocument?>(null) }

    LaunchedEffect(url) {
        withContext(Dispatchers.Default) {
            val nsUrl = NSURL(string = url)
            val pdfDoc = PDFDocument(uRL = nsUrl)
            document = pdfDoc
        }
    }

    if (document != null) {
        val view = remember(document) {
            PDFView().apply {
                this.document = document
                this.autoScales = true
                this.displayMode = 1
                this.displayDirection = 0
            }
        }

        UIKitView(
            factory = { view },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(12.dp))
                Text("Cargando PDF…")
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
actual suspend fun downloadPdfFromUrl(url: String): String? =
    suspendCancellableCoroutine { continuation ->
        val nsUrl = NSURL(string = url)
        val task = NSURLSession.sharedSession.downloadTaskWithURL(nsUrl) { location, _, error ->
            if (error != null || location == null) {
                continuation.resume(null)
                return@downloadTaskWithURL
            }

            val fileManager = NSFileManager.defaultManager
            val urls = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
            val directory = urls.firstOrNull() as? NSURL ?: run {
                continuation.resume(null)
                return@downloadTaskWithURL
            }

            val destination = directory.URLByAppendingPathComponent("document.pdf")!!

            fileManager.removeItemAtURL(destination, null)
            fileManager.moveItemAtURL(location, destination, null)

            continuation.resume(destination.path)
        }
        task.resume()
    }

actual fun openPdf(path: String) {
    val fileUrl = NSURL.fileURLWithPath(path)
    val controller = UIDocumentInteractionController.interactionControllerWithURL(fileUrl)
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

    controller.delegate = object : NSObject(), UIDocumentInteractionControllerDelegateProtocol {
        override fun documentInteractionControllerViewControllerForPreview(controller: UIDocumentInteractionController): UIViewController {
            return rootViewController!!
        }
    }

    controller.presentPreviewAnimated(true)
}