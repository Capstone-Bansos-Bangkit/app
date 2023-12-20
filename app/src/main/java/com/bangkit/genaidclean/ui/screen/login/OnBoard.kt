package com.bangkit.genaidclean.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.yellow
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showSystemUi = true)
@Composable
fun OnBoardScreen(
    modifier: Modifier = Modifier,
    onNavigateToUserLogin: () -> Unit = {},
    onNavigateToAdminLogin: () -> Unit = {},
) {

    rememberSystemUiController().apply {
        setSystemBarsColor(
            color = whiteBlue
        )
    }

    Column(
        modifier = modifier
            .background(Color(whiteBlue.value))
            .padding(PaddingValues(horizontal = 24.dp, vertical = 16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .size(width = 42.dp, height = 36.dp)
            )
            Column {
                Text(
                    text = "Genius",
                    color = Color(navy.value),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    modifier = modifier.height(20.dp)
                )
                Text(
                    text = "Aid",
                    fontWeight = FontWeight.Bold,
                    color = Color(yellow.value),
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.iv_onboard),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(280.dp)
        )

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Selamat datang",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    fontWeight = FontWeight(700),
                    color = Color(navy.value),
                    textAlign = TextAlign.Center
                ),
                modifier = modifier.height(32.dp)
            )
            Row {
                Text(
                    text = "di Genius ",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontWeight = FontWeight(700),
                        color = Color(navy.value),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = modifier.height(32.dp)
                )
                Text(
                    text = "Aid",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontWeight = FontWeight(700),
                        color = Color(yellow.value),
                        textAlign = TextAlign.Center
                    )
                )

            }
            Text(
                text = "Bantuan cerdas untuk kehidupan  masyarakat lebih sejahtera",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(400),
                    color = Color(navy.value),
                    textAlign = TextAlign.Center,
                ),
                modifier = modifier
                    .width(240.dp)
                    .padding(PaddingValues(vertical = 16.dp))
            )
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Button(
                onClick = { onNavigateToUserLogin() },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = navy,
                    contentColor = whiteBlue
                ),
                contentPadding = PaddingValues(vertical = 12.dp),

                ) {
                Text(
                    text = "Masuk",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            TextButton(
                onClick = { onNavigateToAdminLogin() }
            ) {
                Text(
                    text = "Masuk sebagai Admin",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        color = Color(navy.value),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

    }
}