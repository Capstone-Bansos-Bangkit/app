package com.bangkit.genaidclean.ui.screen.admin.bansos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue

@Preview
@Composable
fun DetailBansos(
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(color = whiteBlue),
    ){
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background) ,
                contentDescription = null,
                modifier = modifier
                    .clip(RoundedCornerShape(100))
                    .size(96.dp),
                contentScale = ContentScale.Crop
            )
            Divider(
                color = navy,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .height(88.dp)
                    .width(2.dp)
            )
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ){
                Text(
                    text = "Total Periode",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = black1
                    )
                )
                Text(
                    text = "12",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        color = navy,
                    ),
                    maxLines = 1,
                    modifier = modifier
                        .height(20.dp)
                )

                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = "Total Penerima",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = black1
                    )
                )
                Text(
                    text = "12",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        color = navy,
                    ),
                    maxLines = 1,
                    modifier = modifier
                        .height(20.dp)
                )
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Nama Bantuan Sosial",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = whiteBlue
            ),
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(navy)
                .padding(horizontal = 16.dp, vertical = 2.dp)
        )

        Text(
            text = "Bantuan Pangan Non Tunai ",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = navy
            ),
            modifier = modifier.padding(horizontal=16.dp),
        )

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Deskripsi",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = whiteBlue
            ),
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(navy)
                .padding(horizontal = 16.dp, vertical = 2.dp)

        )

        Text(
            text = stringResource(R.string.desc_bansos),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = navy,
                textAlign = TextAlign.Justify,
            ),
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                
        )
        Column (
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ){

            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = navy,
                ),
                border = BorderStroke(
                    width = 2.dp,
                    color = navy
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Daftar Penerima",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = navy,
                    contentColor = whiteBlue
                ),
                enabled = false,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Penyaluran",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

    }
}