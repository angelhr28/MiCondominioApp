package org.angelhr28.micondominio

import androidx.compose.ui.window.ComposeUIViewController
import org.angelhr28.micondominio.data.DatabaseDriverFactory
import org.angelhr28.micondominio.db.AppDatabase
import org.angelhr28.micondominio.di.appModule
import org.koin.core.context.startKoin

private var koinStarted = false

fun MainViewController() = ComposeUIViewController {
    initKoin() // 🔑 Esto debe ir antes
    App()
}

fun initKoin() {
    if (koinStarted) return
    koinStarted = true

    startKoin {
        modules(appModule(AppDatabase.invoke(DatabaseDriverFactory().createDriver())))
    }
}