package com.bangkit.genaidclean.ui.screen.admin.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.components.admin.ItemBansosHome
import com.bangkit.genaidclean.ui.components.admin.ItemPengajuan
import com.bangkit.genaidclean.ui.components.admin.Statistic
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Preview
@Composable
fun DashboardAdminScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(whiteBlueLight)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        SectionTitle(title = "Hi, Selamat Datang Admin!")

        Statistic()

        Text(
            text = "Bantuan Sosial",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = black1,
            ),
            modifier = modifier.padding(top = 24.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(3) {
                ItemBansosHome()
            }
        }

        Text(
            text = "Riwayat Verifikasi",
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

