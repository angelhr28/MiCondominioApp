package org.angelhr28.micondominio.navigation

import kotlinx.serialization.Serializable


sealed class Screen {
    @Serializable
    object Splash

    @Serializable
    object Login

    @Serializable
    object ForgotPassword

    @Serializable
    object Home

    @Serializable
    object Profile

    @Serializable
    object Payments

    @Serializable
    object Reservations

    @Serializable
    object Notifications

    @Serializable
    object Reports

    @Serializable
    object Regulations

    @Serializable
    object Banners

    @Serializable
    object Services
}