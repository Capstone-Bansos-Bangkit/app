package com.bangkit.genaidclean.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Preview
@Composable
fun LoadingBox(
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(whiteBlueLight)
            .padding(8.dp)
    ) {
        CircularProgressIndicator(
            color = navy,
        )
    }
}