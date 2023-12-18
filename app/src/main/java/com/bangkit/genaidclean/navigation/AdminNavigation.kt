package com.bangkit.genaidclean.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.components.admin.BottomBarAdmin
import com.bangkit.genaidclean.ui.screen.admin.bansos.BansosScreen
import com.bangkit.genaidclean.ui.screen.admin.dashboard.DashboardAdminScreen
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.VerifikasiScreen
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Preview
@Composable
fun AdminNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold (
        bottomBar = { BottomBarAdmin()},
        containerColor = whiteBlueLight,
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.AdminDashboard.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.AdminDashboard.route){
                DashboardAdminScreen()
            }

            composable(Screen.AdminVerifikasi.route){
                VerifikasiScreen()
            }

            composable(Screen.AdminBansos.route){
                BansosScreen()
            }
        }
    }
}
