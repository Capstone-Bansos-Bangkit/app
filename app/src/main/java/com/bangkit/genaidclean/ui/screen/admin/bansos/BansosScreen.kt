package com.bangkit.genaidclean.ui.screen.admin.bansos

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.admin.ItemBansosGrid
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.screen.admin.state.ErrorScreen
import com.bangkit.genaidclean.ui.screen.admin.state.LoadingScreen
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.VerifikasiViewModel
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.utils.State

@Preview
@Composable
fun BansosScreen(
    modifier : Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: AdminBansosViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    ),
    onNavigateToDetailbansos : (Int) -> Unit = {}
) {

    val dataState by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.getBansos()
    }

    //state
    when (dataState) {
        is State.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }
        is State.Success -> {
            val data = (dataState as State.Success<Bansos>).data
            BansosScreenContent(
                modifier = modifier,
                databansos = data,
                onNavigateToDetailbansos = onNavigateToDetailbansos
            )
        }
        is State.Error -> {
            (dataState as State.Error).error?.let {
                ErrorScreen(
                    modifier = modifier.fillMaxSize(),
                    retryAction = { viewModel.getBansos() },
                    error = it
                )
            }
        }
    }
}

@Composable
fun BansosScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToDetailbansos: (Int) -> Unit,
    databansos: Bansos,
) {
    Column(
        modifier = modifier
            .background(whiteBlueLight)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        SectionTitle(title = "Data & Penyaluran Bantuan Sosial")

        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(databansos.result) {bansos: ResultItem ->
                ItemBansosGrid(
                    onNavigateToDetailBansos = onNavigateToDetailbansos,
                    dataBansos = bansos
                )
            }
        }
    }
}