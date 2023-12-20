package com.bangkit.genaidclean.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.genaidclean.AdminActivity
import com.bangkit.genaidclean.AuthActivity
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.screen.login.AdminLogin
import com.bangkit.genaidclean.ui.screen.login.OnBoardScreen
import com.bangkit.genaidclean.ui.screen.login.UserLogin

//@Preview
@Composable
fun AuthNavigation(
    context: Context = LocalContext.current,
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
                onNavigateBack = { navController.popBackStack() },
                context = context,
            )
        }

        composable(Screen.AdminLogin.route){
            AdminLogin(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAdminActivity = {
                    navController.navigate(Screen.AdminDashboard.route)
                }
            )
        }

        composable(Screen.AdminDashboard.route){
            val intent = Intent(context, AdminActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }
}

