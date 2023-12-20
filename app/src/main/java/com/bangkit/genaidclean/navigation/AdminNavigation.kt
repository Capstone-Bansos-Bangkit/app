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
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.VerifikasiScreen
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

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

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.AdminDetailPengajuan.route ||
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
        containerColor = whiteBlueLight,
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
                VerifikasiScreen()
            }

            composable(Screen.AdminBansos.route) {
                BansosScreen(
                    onNavigateToDetailbansos = {
                        navController.navigate(Screen.AdminDetailBansos.route)
                    }
                )
            }

            composable(
                route = Screen.AdminDetailBansos.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailBansos(
                    // dan pritilannya
                )
            }

            composable(
                route = Screen.AdminDetailPengajuan.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType }
                )
            ) {
                val id = it.arguments?.getLong("id") ?: 0L
                //TODO: buat screen detail pengajuan
//                DetailPengajuan(
//
//                )
            }
        }
    }
}
