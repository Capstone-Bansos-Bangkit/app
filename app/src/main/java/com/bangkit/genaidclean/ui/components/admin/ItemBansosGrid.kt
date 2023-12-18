package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.navyLight
import com.bangkit.genaidclean.ui.theme.whiteBlue

@Preview
@Composable
fun ItemBansosGrid(
    modifier: Modifier = Modifier,
) {
    Card (
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = whiteBlue,
            contentColor = navy
        ),
        modifier = modifier
            .width(160.dp)
            .clickable { } //TODO: navigate to detail
    ) {
        Column (
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ){

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = modifier
                    .size(144.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

//            AsyncImage(
//                model = ImageRequest.Builder(context = LocalContext.current)
//                    .data("")
//                    .crossfade(true),
//                contentDescription = null,
//                placeholder = painterResource(id = R.drawable.iv_profile_placeholder ),
//                contentScale = ContentScale.Crop,
//                modifier = modifier
//                    .size(width = 142.dp, height = 146.dp)
//            )

            Text(
                text = "Bantuan Pangan Non Tunai",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = navy,
                    textAlign = TextAlign.Center,
                ),
                modifier = modifier
                    .padding(top=4.dp)
                    .width(120.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}