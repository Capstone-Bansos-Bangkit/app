package com.bangkit.genaidclean.ui.screen.admin.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bangkit.genaidclean.ui.theme.navy

@Composable
fun LoadingScreen(
    modifier: Modifier,
) {
    Column (
        modifier = modifier.size(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        CircularProgressIndicator(
            strokeWidth = 4.dp,
            color = navy
        )
    }
}