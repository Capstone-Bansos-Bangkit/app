package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Preview
@Composable
fun Statistic(
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = navy,
            contentColor = whiteBlue
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = modifier.padding(24.dp, 16.dp)
        ) {
            Text(

                text = "Total Pengajuan Bantuan",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    letterSpacing = 1.sp
                )
            )
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    //TODO: Change text
                    text = "1.250.000",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        letterSpacing = 1.8.sp,
                    ),
                    maxLines = 1,
                    modifier = modifier
                        .padding(end = 8.dp)
                        .height(48.dp)
                )
                Text(
                    text = "keluarga",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        letterSpacing = 1.8.sp,
                    ),
                    maxLines = 1,
                    modifier = modifier.padding(bottom = 4.dp)
                )
            }

            Spacer(modifier = modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Eliglible",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                        )
                    )
                    Text(
                        //TODO: Change text
                        text = "1.050.000",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        ),
                        maxLines = 1,
                    )
                }
                Column {
                    Text(
                        text = "Non Eliglible",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                        )
                    )
                    Text(
                        //TODO: Change text
                        text = "1.050.000",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        ),
                        maxLines = 1,
                    )
                }
            }

        }
    }
}