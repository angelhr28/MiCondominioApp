package org.angelhr28.micondominio.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.angelhr28.micondominio.ui.feature.banners.BannersScreen
import org.angelhr28.micondominio.ui.feature.forgotpassword.ForgotPasswordScreen
import org.angelhr28.micondominio.ui.feature.home.HomeScreen
import org.angelhr28.micondominio.ui.feature.login.LoginScreen
import org.angelhr28.micondominio.ui.feature.notifications.NotificationsScreen
import org.angelhr28.micondominio.ui.feature.payments.PaymentsScreen
import org.angelhr28.micondominio.ui.feature.profile.ProfileScreen
import org.angelhr28.micondominio.ui.feature.profile.edit.EditProfileScreen
import org.angelhr28.micondominio.ui.feature.regulations.RegulationsScreen
import org.angelhr28.micondominio.ui.feature.reports.ReportsScreen
import org.angelhr28.micondominio.ui.feature.reservations.ReservationsScreen
import org.angelhr28.micondominio.ui.feature.services.ServicesScreen
import org.angelhr28.micondominio.ui.feature.settings.SettingsScreen
import org.angelhr28.micondominio.ui.feature.splash.SplashScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) },
        popExitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        profileNavGraph(navController)

        composable(Routes.Splash) { SplashScreen { navController.popBackStack() } }
        composable(Routes.Login) { LoginScreen(navigator = navController) }
        composable(Routes.ForgotPassword) { ForgotPasswordScreen { navController.popBackStack() } }
        composable(Routes.Home) { HomeScreen(navigator = navController) }
        composable(Routes.Payments) { PaymentsScreen { navController.popBackStack() } }
        composable(Routes.Reservations) { ReservationsScreen { navController.popBackStack() } }
        composable(Routes.Notifications) { NotificationsScreen { navController.popBackStack() } }
        composable(Routes.Reports) { ReportsScreen { navController.popBackStack() } }
        composable(Routes.Regulations) { RegulationsScreen { navController.popBackStack() } }
        composable(Routes.Services) { ServicesScreen { navController.popBackStack() } }
        composable(Routes.Banners) { BannersScreen { navController.popBackStack() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Routes.Profile.Main,
        route = "profile_graph"
    ) {
        composable(Routes.Profile.Main) {
            ProfileScreen(
                onSettingsPress = { navController.navigate(Routes.Profile.Settings) },
                onEditPress = { navController.navigate(Routes.Profile.Edit) },
                onLogoutPress = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Home) { inclusive = true }
                    }
                },
                onBackPress = { navController.popBackStack() }
            )
        }
        composable(Routes.Profile.Edit) { EditProfileScreen { navController.popBackStack() } }
        composable(Routes.Profile.Settings) { SettingsScreen { navController.popBackStack() } }
    }
}
