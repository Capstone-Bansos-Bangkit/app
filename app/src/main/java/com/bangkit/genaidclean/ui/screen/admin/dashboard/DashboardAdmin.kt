package com.bangkit.genaidclean.ui.screen.admin.dashboard

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionSummaryResponse
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.admin.ItemBansosHome
import com.bangkit.genaidclean.ui.components.admin.ItemPengajuan
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.components.admin.Statistic
import com.bangkit.genaidclean.ui.screen.admin.state.ErrorScreen
import com.bangkit.genaidclean.ui.screen.admin.state.LoadingScreen
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.utils.State

@Preview
@Composable
fun DashboardAdminScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: DashboardAdminViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    ),
) {
    val result by viewModel.data.collectAsState(initial = State.Loading)

    LaunchedEffect(true) {
        viewModel.fetchData()
    }

    //state
    when (result) {
        is State.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }
        is State.Success -> {
            DashBoardContent(
                submissionSummary = (result as State.Success<DashboardData>).data.submissionSummary,
                dataSubmission = (result as State.Success<DashboardData>).data.dataSubmission,
            )
        }

        is State.Error -> {
            (result as State.Error).error?.let {
                ErrorScreen(
                    modifier = modifier.fillMaxSize(),
                    retryAction = { viewModel.fetchData() },
                    error = it
                )
            }
        }

        else -> {}
    }

}

@Composable
fun DashBoardContent(
    modifier: Modifier = Modifier,
    submissionSummary: SubmissionSummaryResponse,
    dataSubmission: SubmissionListResponse,
) {
    Column(
        modifier = modifier
            .background(whiteBlueLight)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SectionTitle(title = "Hi, Selamat Datang Admin!")

        Statistic(dataResponse = submissionSummary)

        Text(
            text = "Bantuan Sosial",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = black1,
            ), modifier = modifier.padding(top = 24.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(submissionSummary.result.perBansos) {bansosItem ->
                ItemBansosHome(item = bansosItem)
            }
        }

        Text(
            text = "Riwayat Verifikasi", style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = black1,
            ), modifier = modifier.padding(top = 16.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(dataSubmission.result) {approvedSubmission ->
                ItemPengajuan(dataSubmission = approvedSubmission)
            }
        }

    }
}

