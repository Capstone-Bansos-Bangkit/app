package com.bangkit.genaidclean.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun ButtonBack(
    onClick: () -> Unit,
    containerTint: IconButtonColors,
    modifier: Modifier = Modifier
){
    FilledIconButton(
        onClick = onClick,
        colors = containerTint,
    ) {
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowLeft,
            contentDescription = null,
            modifier = modifier.size(32.dp)
        )
    }
}

@Preview
@Composable
fun ButtonBackPreview() {
    ButtonBack(onClick = {}, containerTint = IconButtonDefaults.filledIconButtonColors())
}