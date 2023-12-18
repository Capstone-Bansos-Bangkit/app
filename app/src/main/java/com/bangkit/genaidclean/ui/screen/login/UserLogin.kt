package com.bangkit.genaidclean.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.components.ButtonBack
import com.bangkit.genaidclean.ui.components.TextInput
import com.bangkit.genaidclean.ui.theme.grey
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight


@Preview(showSystemUi = true)
@Composable
fun UserLogin(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(Color(whiteBlueLight.value))
            .padding(PaddingValues(horizontal = 24.dp, vertical = 16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        val containerColor = Color(whiteBlue.value)

        ButtonBack(
            onClick = { onNavigateBack() },
            containerTint = IconButtonDefaults.filledIconButtonColors(
                containerColor = whiteBlue,
                contentColor = Color(navy.value)
            )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text(
                text = "Halo!",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = Color(navy.value),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Untuk dapat masuk ke aplikasi, lengkapi datamu dulu ya!",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    color = Color(navy.value),
                    textAlign = TextAlign.Center,
                ),
                modifier = modifier.padding(horizontal = 64.dp)
            )
        }

        Column(
            modifier = modifier.padding(bottom = 120.dp)
        ) {
            TextInput(
                inputTitle = "NIK",
                inputTitleColor = Color(navy.value),
                modifier = modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.rounded_barcode_scanner_24),
                        tint = Color(grey.value),
                        contentDescription = null
                    )
                },
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                ),
            )
            TextInput(
                inputTitle = "Nama Ibu Kandung",
                inputTitleColor = Color(navy.value),
                modifier = modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_face_4_24),
                        tint = Color(grey.value),
                        contentDescription = null
                    )
                },
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                ),
                placeholder = { Text(text = "NAMA LENGKAP") }
            )


            TextInput(
                inputTitle = "Tanggal Lahir",
                inputTitleColor = Color(navy.value),
                modifier = modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_event_note_24),
                        tint = Color(grey.value),
                        contentDescription = null
                    )
                },
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                ),
                placeholder = { Text(text = "YYYY-MM-DD") }
            )

        }

        Box(
            modifier = modifier.fillMaxHeight().padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(navy.value)),
                contentPadding = PaddingValues(vertical = 12.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                Text(
                    text = "Submit",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    ),
                    color = Color(whiteBlueLight.value)
                )
            }
        }

    }
}