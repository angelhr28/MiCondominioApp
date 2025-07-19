package org.angelhr28.micondominio.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.angelhr28.micondominio.ui.banners.BannersScreen
import org.angelhr28.micondominio.ui.forgotpassword.ForgotPasswordScreen
import org.angelhr28.micondominio.ui.home.HomeScreen
import org.angelhr28.micondominio.ui.login.LoginScreen
import org.angelhr28.micondominio.ui.notifications.NotificationsScreen
import org.angelhr28.micondominio.ui.payments.PaymentsScreen
import org.angelhr28.micondominio.ui.profile.ProfileScreen
import org.angelhr28.micondominio.ui.regulations.RegulationsScreen
import org.angelhr28.micondominio.ui.reports.ReportsScreen
import org.angelhr28.micondominio.ui.reservations.ReservationsScreen
import org.angelhr28.micondominio.ui.services.ServicesScreen
import org.angelhr28.micondominio.ui.splash.SplashScreen


@Composable
fun AppNavGraph() {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) },
        popExitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {

        composable<Screen.Splash> { SplashScreen() }
        composable<Screen.Login>(
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) { LoginScreen(navigator = navController) }

        composable<Screen.ForgotPassword> { ForgotPasswordScreen() }
        composable<Screen.Home> { HomeScreen(navigator = navController) }
        composable<Screen.Profile> { ProfileScreen() }
        composable<Screen.Payments> { PaymentsScreen() }
        composable<Screen.Reservations> { ReservationsScreen() }
        composable<Screen.Notifications> { NotificationsScreen() }
        composable<Screen.Reports> { ReportsScreen { navController.popBackStack() } }
        composable<Screen.Regulations> { RegulationsScreen() }
        composable<Screen.Services> { ServicesScreen() }
        composable<Screen.Banners> { BannersScreen() }
    }
}