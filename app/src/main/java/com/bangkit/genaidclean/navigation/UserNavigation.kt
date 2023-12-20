package com.bangkit.genaidclean.navigation

import android.content.Context
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
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.components.user.BottomBarUser
import com.bangkit.genaidclean.ui.screen.user.cekbansos.CekBansosScreen
import com.bangkit.genaidclean.ui.screen.user.detailbansos.UserDetailBansosScreen
import com.bangkit.genaidclean.ui.screen.user.home.UserHomeScreen
import com.bangkit.genaidclean.ui.screen.user.pengajuan.AskPengajuanScreen
import com.bangkit.genaidclean.ui.screen.user.pengajuan.UserPengajuanScreen
import com.bangkit.genaidclean.ui.screen.user.profile.UserProfileScreen
import com.bangkit.genaidclean.ui.screen.user.question.Question
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Preview
@Composable
fun UserNavigation(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    rememberSystemUiController().apply {
        setSystemBarsColor(color = whiteBlueLight)
    }

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Question.route && currentRoute != Screen.CekBansos.route
                && currentRoute != Screen.UserDetailBansos.route
            ) {
                BottomBarUser(navController = navController)
            }
        },
        containerColor = whiteBlueLight,
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
                    navigateToDetailBansos = {bansosId ->
                        navController.navigate(Screen.UserDetailBansos.createRoute(bansosId))
                    }
                )
            }

            composable(
                route = Screen.UserDetailBansos.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ){
                val id = it.arguments?.getInt("id") ?: 0
                UserDetailBansosScreen(
                    bansosId = id,
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
                UserPengajuanScreen(
                    context = context,
                    navigateToQuestion = { id ->
                        navController.navigate(Screen.Question.createRoute(id))
                    },
                    navController = navController
                )

            }
            composable(
                route = Screen.Question.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("id") ?: 1
                Question(Id = id, navController = navController, context = context)

            }
            composable(Screen.UserProfil.route) {
                UserProfileScreen(navController,context)
            }

        }

    }
}