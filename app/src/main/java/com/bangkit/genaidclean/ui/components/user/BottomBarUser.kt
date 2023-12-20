package com.bangkit.genaidclean.ui.components.user

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.navigation.utils.NavigationItem
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.navy


@Composable
fun BottomBarUser(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Outlined.Home,
                screen = Screen.UserHome
            ),
            NavigationItem(
                title = stringResource(id = R.string.pengajuan),
                icon = Icons.Default.List,
                screen = Screen.UserPengajuan
            ),
            NavigationItem(
                title = stringResource(id = R.string.profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.UserProfil
            ),
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = navy,
                    selectedTextColor = navy,
                    unselectedIconColor = black1,
                    unselectedTextColor = black1,
                    indicatorColor = Color.Transparent,
                ),
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}