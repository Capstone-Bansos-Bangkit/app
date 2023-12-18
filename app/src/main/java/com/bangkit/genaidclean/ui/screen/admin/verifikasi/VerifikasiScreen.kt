package com.bangkit.genaidclean.ui.screen.admin.verifikasi

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.components.admin.BottomBarAdmin
import com.bangkit.genaidclean.ui.components.admin.FabFilter
import com.bangkit.genaidclean.ui.components.admin.ItemPengajuan
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.ui.theme.yellow

@Preview
@Composable
fun VerifikasiScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold (
        topBar = {
            SectionTitle()
        },
        floatingActionButton = {
            FabFilter()
        },
        bottomBar = {
            BottomBarAdmin()
        }
    ){innerPadding ->
        Column(
            modifier = modifier
                .background(whiteBlueLight)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(navy)
                    .padding(start = 16.dp, top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_groups_24),
                    contentDescription = null,
                    tint = whiteBlueLight,
                    modifier = modifier
                        .size(72.dp)
                )
                Column (
                    modifier = modifier.padding(start = 24.dp),
                    horizontalAlignment = Alignment.End,

                    ){
                    Text(
                        text = "Jumlah Antrian",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = yellow,
                            letterSpacing = 0.8.sp,
                        )
                    )
                    Text(
                        text = "1250",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            color = whiteBlueLight,
                            letterSpacing = 1.8.sp,
                        ),
                        modifier = modifier.height(48.dp)
                    )
                }
            }
            Text(
                text = "List Pengajuan",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    color = black1,
                ),
                modifier = modifier.padding(top = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                items(3) {
                    ItemPengajuan()
                }
            }
        }
    }
}






