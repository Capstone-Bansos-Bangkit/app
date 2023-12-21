package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.FactCheck
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.navigation.utils.NavigationItem
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.components.CustomAlertDialog
import com.bangkit.genaidclean.ui.theme.grey
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.white


@Preview
@Composable
fun BottomBarAdmin(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    actionLogOut: () -> Unit = {},
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showAlert by remember { mutableStateOf(false) }

    if (showAlert) {
        CustomAlertDialog(
            onDismiss = { showAlert = false },
            onConfirm = { actionLogOut() },
            titleDialog = "Logout",
            msgDialog = "Apakah anda yakin ingin keluar?",
        )
    }

    BottomAppBar(
        containerColor = white,
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = "Dashboard",
                icon = Icons.Outlined.Dashboard,
                iconSelected = Icons.Rounded.Dashboard,
                screen = Screen.AdminDashboard
            ),
            NavigationItem(
                title = "Verifikasi",
                icon = Icons.Outlined.FactCheck,
                iconSelected = Icons.Rounded.FactCheck,
                screen = Screen.AdminVerifikasi,
            ),
            NavigationItem(
                title = "Bansos",
                icon = Icons.Outlined.FolderOpen,
                iconSelected = Icons.Filled.Folder,
                screen = Screen.AdminBansos
            ),
            NavigationItem(
                title = "Logout",
                icon = Icons.Outlined.Logout,
                screen = Screen.OnBoarding
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    if (currentRoute == item.screen.route && item.iconSelected != null) {
                        Icon(
                            imageVector = item.iconSelected,
                            contentDescription = item.title
                        )
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = navy,
                    selectedTextColor = navy,
                    unselectedIconColor = grey,
                    unselectedTextColor = grey,
                    indicatorColor = white
                ),
                selected = currentRoute == item.screen.route,
                onClick = {
                    if (item.title == "Logout") {
                        showAlert = true
//                        actionLogOut()
                    } else {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}