package com.bangkit.genaidclean.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.navyLight
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AdminLogin(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(Color(navy.value))
            .padding(PaddingValues(horizontal = 24.dp, vertical = 16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        ButtonBack(
            onClick = { onNavigateBack() },
            containerTint = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(navyLight.value),
                contentColor = Color(whiteBlueLight.value)
            )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Hi, Admin!",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = Color(whiteBlueLight.value),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Semangat menjalankan amanah, ya!",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    color = Color(whiteBlueLight.value),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Box(modifier = modifier.padding(vertical = 32.dp)) {

            Image(
                painter = painterResource(id = R.drawable.iv_admin),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp),
                alignment = Alignment.Center
            )
        }

        Column(
        ) {
            TextInput(
                inputTitle = "Username",
                inputTitleColor = Color(whiteBlueLight.value),
                modifier = modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_badge_24),
                        tint = Color(whiteBlueLight.value),
                        contentDescription = null
                    )
                },
                onValueChange = {},
                //TODO : DEPRECATED
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(navyLight.value),
                ),
            )
            TextInput(
                inputTitle = "Password",
                inputTitleColor = Color(whiteBlueLight.value),
                modifier = modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_lock_24),
                        tint = Color(whiteBlueLight.value),
                        contentDescription = null
                    )
                },
                onValueChange = {},

                //TODO : DEPRECATED
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(navyLight.value),
                ),
            )
        }

        Box(
            modifier = modifier
                .fillMaxHeight()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(whiteBlueLight.value)),
                contentPadding = PaddingValues(vertical = 12.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                Text(
                    text = "Masuk",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    ),
                    color = Color(navy.value)
                )
            }
        }

    }
}