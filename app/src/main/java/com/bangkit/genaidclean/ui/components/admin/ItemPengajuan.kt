package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.black
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.yellow


@Preview
@Composable
fun ItemPengajuan(
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(whiteBlue.value)
        ),
        modifier = modifier
            .height(120.dp)
            .clickable { } //TODO: navigate to detail
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //TODO: replace with async image
                Image(
                    painter = painterResource(id = R.drawable.iv_profile_placeholder),
                    contentDescription = null,
                    modifier = modifier
                        .clip(RoundedCornerShape(8.dp))
                        .size(64.dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(100))
                        .background(Color(yellow.value))
                        .padding(vertical = 2.dp, horizontal = 12.dp)
                ) {
                    Text(
                        //TODO: ganti dengan data
                        text = "BPNT",
                        style = TextStyle(
                            fontSize = 8.sp,
                            lineHeight = 8.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            color = Color(black.value),
                            letterSpacing = 1.28.sp,
                        )
                    )
                }
            }

            Spacer(
                modifier = modifier
                    .width(16.dp)
            )

            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    //TODO: ganti dengan data
                    text = "330801300772809",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        color = Color(black.value),
                        letterSpacing = 1.5.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(
                    modifier = modifier
                        .height(4.dp)
                )

                Text(
                    //TODO: ganti dengan data
                    text = "Ahmad Abdul Hariz",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = Color(black.value),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    //TODO: ganti dengan data
                    text = "JL. Mulawarman No.3, 3 / 39 , Kali Segoro, Kec. Hilir, Kot. New York, Prov. Texas",
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = Color(black1.value),
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}