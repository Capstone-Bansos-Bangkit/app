package com.bangkit.genaidclean.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.navyLight
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    titleDialog: String ="Title",
    msgDialog: String = "pesan anda",
) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = whiteBlue,
                    contentColor = navy,

                    )
            ) {
                Text(
                    text = "Konfirmasi",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    )
                )
            }
        },

        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = navyLight,
                    contentColor = whiteBlueLight,

                    )
            ) {
                Text(
                    text = "Kembali",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    )
                )
            }
        },

        title = {
            Text(
                text = titleDialog,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    fontSize = 20.sp
                )
            )
        },
        text = {
            Text(
                text = msgDialog,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontSize = 16.sp
                )
            )
        },
        containerColor = navy,
        iconContentColor = black1,
        titleContentColor = whiteBlueLight,
        textContentColor = whiteBlueLight,
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomAlertDialog(onDismiss = {}, onConfirm = {})
}