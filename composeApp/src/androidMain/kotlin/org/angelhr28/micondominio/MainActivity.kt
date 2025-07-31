package org.angelhr28.micondominio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.angelhr28.micondominio.shared.initPdfHandlerContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        initPdfHandlerContext(this)
        setContent {
            App()
        }
    }
}