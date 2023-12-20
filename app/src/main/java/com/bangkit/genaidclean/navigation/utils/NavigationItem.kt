package com.bangkit.genaidclean.navigation.utils

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector? = null,
    val screen: Screen,
)
