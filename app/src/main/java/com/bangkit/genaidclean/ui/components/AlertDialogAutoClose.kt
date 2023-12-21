package com.bangkit.genaidclean.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Preview
@Composable
fun AlertAutoClose(
    durationMillis: Long = 3000,
    onConfirm: () -> Unit = {},
    msg: String = "",
) {

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {

        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.CheckCircleOutline,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        },
        title = {
            Text(
                text = msg,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontSize = 24.sp
                )
            )
        },
        containerColor = whiteBlue,
        iconContentColor = navy,
        titleContentColor = navy,
        textContentColor = whiteBlueLight,
    )
}
