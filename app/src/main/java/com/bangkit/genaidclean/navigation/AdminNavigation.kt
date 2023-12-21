package com.bangkit.genaidclean.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.genaidclean.AuthActivity
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.components.admin.BottomBarAdmin
import com.bangkit.genaidclean.ui.components.admin.FabFilter
import com.bangkit.genaidclean.ui.screen.admin.bansos.BansosScreen
import com.bangkit.genaidclean.ui.screen.admin.bansos.DetailBansos
import com.bangkit.genaidclean.ui.screen.admin.dashboard.DashboardAdminScreen
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.DetailAjuan
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.DetailAjuanContent
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.VerifikasiScreen
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun AdminNavigation(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    actionLogOut: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val containerColor = whiteBlueLight

    rememberSystemUiController().apply {
        setStatusBarColor(containerColor)
    }

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.AdminDetailPengajuan.route &&
                currentRoute != Screen.AdminDetailBansos.route
            ) {
                BottomBarAdmin(
                    navController = navController,
                    actionLogOut = actionLogOut
                )
            }
        },
        floatingActionButton = {
            if (currentRoute == Screen.AdminVerifikasi.route) {
                FabFilter()
            }
        },
        containerColor = containerColor,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.AdminDashboard.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.AdminDashboard.route) {
                DashboardAdminScreen()
            }

            composable(Screen.AdminVerifikasi.route) {
                VerifikasiScreen(
                    onNavigateToDetailPengajuan = { pengajuanId ->
                        navController.navigate(Screen.AdminDetailPengajuan.createRoute(pengajuanId))
                    }
                )
            }
            composable(
                route = Screen.AdminDetailPengajuan.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailAjuan(
                    submissionId = id,
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Screen.AdminBansos.route) {
                BansosScreen(
                    onNavigateToDetailbansos = {bansosId ->
                        navController.navigate(Screen.AdminDetailBansos.createRoute(bansosId))
                    }
                )
            }
            composable(
                route = Screen.AdminDetailBansos.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailBansos(
                    bansosId = id,
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }
}
