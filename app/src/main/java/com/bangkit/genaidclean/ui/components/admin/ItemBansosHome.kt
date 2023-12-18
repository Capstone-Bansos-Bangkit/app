package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.bangkit.genaidclean.ui.theme.grey
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.navyLight
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.yellow

@Preview
@Composable
fun ItemBansosHome(
//    onClick: () -> Unit
    modifier: Modifier = Modifier
) {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = whiteBlue
        )
    ){
        Column (
            modifier = modifier
                .padding(16.dp, 8.dp)
                .width(160.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ){
            Text(
                text = "Bantuan Pangan Non Tunai",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    color = navy,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Divider(
                color = grey,
                thickness = 2.dp,
                modifier = modifier
                    .alpha(0.5f)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(vertical = 4.dp)
            )
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "123.000",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = navy,
                            letterSpacing = 0.96.sp,
                        ),
                        modifier = modifier.height(18.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "penerima",
                        style = TextStyle(
                            fontSize = 8.sp,
                            lineHeight = 8.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            color = black1,
                        )
                    )
                }
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .clip(RoundedCornerShape(100))
                        .background(yellow)
                        .size(24.dp)
                ){
                    Text(
                        text = "24",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 8.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            color = navy,
                        ),
                    )
                }
            }


        }
    }
}