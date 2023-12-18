package com.bangkit.genaidclean.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.components.user.BottomBarUser
import com.bangkit.genaidclean.ui.screen.user.cekbansos.CekBansosScreen
import com.bangkit.genaidclean.ui.screen.user.detailbansos.UserDetailBansosScreen
import com.bangkit.genaidclean.ui.screen.user.home.UserHomeScreen
import com.bangkit.genaidclean.ui.screen.user.pengajuan.AskPengajuanScreen
import com.bangkit.genaidclean.ui.screen.user.pengajuan.UserPengajuanScreen
import com.bangkit.genaidclean.ui.screen.user.profile.UserProfileScreen


//@Preview
@Composable
fun UserNavigation(
    context: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.AskPengajuan.route && currentRoute != Screen.CekBansos.route
                && currentRoute != Screen.UserDetailBansos.route
            ) {
                BottomBarUser(navController = navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.UserHome.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.UserHome.route) {
                UserHomeScreen(
                    context = context,
                    navController = navController,
                    navigateToDetailBansos = {name ->
                        navController.navigate(Screen.UserDetailBansos.createRoute(name))
                    }
                )
            }

            composable(
                route = Screen.UserDetailBansos.route,
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType }
                )
            ){
                val name = it.arguments?.getString("name") ?: ""
                UserDetailBansosScreen(
                    name = name,
                    context = context,
                    navController = navController
                )
            }

            composable(Screen.CekBansos.route) {
                CekBansosScreen(
                    context = context,
                    navController = navController
                )
            }
            composable(Screen.UserPengajuan.route) {
                UserPengajuanScreen(navController = navController)
                //ContentPengajuan(navController)

            }
            composable(Screen.AskPengajuan.route) {
                AskPengajuanScreen(navController = navController)

            }
            composable(Screen.UserProfil.route) {
                UserProfileScreen(navController,context)
            }

        }

    }
}