package com.bangkit.genaidclean.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.screen.login.AdminLogin
import com.bangkit.genaidclean.ui.screen.login.OnBoardScreen
import com.bangkit.genaidclean.ui.screen.login.UserLogin

@Composable
fun AuthNavigation(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoarding.route,
    ){
        composable(Screen.OnBoarding.route){
            OnBoardScreen(
                onNavigateToUserLogin = {
                    navController.navigate(Screen.UserLogin.route)
                },
                onNavigateToAdminLogin = {
                    navController.navigate(Screen.AdminLogin.route)
                }
            )
        }

        composable(Screen.UserLogin.route){
            UserLogin(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.AdminLogin.route){
            AdminLogin(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

