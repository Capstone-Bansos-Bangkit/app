package com.bangkit.genaidclean.ui.screen.admin.verifikasi

import android.content.Context
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.admin.ResultItem
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.admin.FabFilter
import com.bangkit.genaidclean.ui.components.admin.ItemPengajuan
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.screen.admin.dashboard.DashboardAdminViewModel
import com.bangkit.genaidclean.ui.screen.admin.state.ErrorScreen
import com.bangkit.genaidclean.ui.screen.admin.state.LoadingScreen
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.ui.theme.yellow
import com.bangkit.genaidclean.utils.State

@Preview
@Composable
fun VerifikasiScreen(
    modifier: Modifier = Modifier,
    context:Context = LocalContext.current,
    viewModel: VerifikasiViewModel= viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    ),
    onNavigateToDetailPengajuan: (Int) -> Unit = {},
) {

    val dataState by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.fetchData()
    }

    //state
    when (dataState) {
        is State.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }
        is State.Success -> {
            val data = (dataState as State.Success<SubmissionListResponse>).data
            VerifikasiScreenContent(
                modifier = modifier,
                data = data,
                onNavigateToDetailPengajuan = onNavigateToDetailPengajuan
            )
        }
        is State.Error -> {
            (dataState as State.Error).error?.let {
                ErrorScreen(
                    modifier = modifier.fillMaxSize(),
                    retryAction = { viewModel.fetchData() },
                    error = it
                )
            }
        }
    }
}


@Composable
fun VerifikasiScreenContent(
    modifier: Modifier = Modifier,
    data: SubmissionListResponse,
    onNavigateToDetailPengajuan: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(whiteBlueLight)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SectionTitle(title = "Verifikasi Pengajuan Bantuan Sosial")

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(navy)
                .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_groups_24),
                contentDescription = null,
                tint = whiteBlueLight,
                modifier = modifier
                    .size(72.dp)
            )
            Column(
                modifier = modifier.padding(start = 24.dp, end = 24.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.End,

                ) {
                Text(
                    text = "Jumlah Antrian",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = yellow,
                        letterSpacing = 0.8.sp,
                    ),
                    modifier = modifier.height(14.dp)
                )
                Text(
                    text = data.total.toString(),
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
            items(data.result) {item: ResultItem ->
                ItemPengajuan(
                    onNavigateToDetailPengajuan = onNavigateToDetailPengajuan,
                    dataSubmission = item
                )
            }
        }
    }
}






