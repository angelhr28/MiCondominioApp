package org.angelhr28.micondominio.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
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
    val nsUrl = NSURL(string = url)
    val document = PDFDocument(uRL = nsUrl)

    val view = PDFView().apply {
        this.document = document
        this.autoScales = true
        this.displayMode =
            1 // Represent Enum entry 1 ( Single Page Continuous ) from PDFDisplayMode
        this.displayDirection = 0 // Represent Enum entry 1 ( Horizontal ) from PDFDisplayDirection
    }

    UIKitView(
        factory = { view },
        modifier = Modifier.fillMaxSize().background(Color.White),
    )
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