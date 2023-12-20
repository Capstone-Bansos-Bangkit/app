package com.bangkit.genaidclean.ui.screen.user.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.user.BtnCekBansos
import com.bangkit.genaidclean.ui.components.user.ItemProfileBansos
import com.bangkit.genaidclean.ui.theme.black
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Composable
fun UserHomeScreen(
    context: Context,
    //TODO: NAVIGATE TO DETAIL BANSOS
    navigateToDetailBansos: (Int) -> Unit,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Inject.provideRepository(context = context)),
    ),
) {

    val groupedBansos by viewModel.groupedBansos.collectAsState()
    viewModel.getUserProfile()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(whiteBlueLight),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.userProfile.value.let {
                if (it != null) {
                    Text(
                        text = "Welcome, ${it.result?.name}",
                        fontSize = 25.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                        color = black,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                    )
                }
            }
            Text(
                text = "Profile Bantuan Sosial",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Start,
                color = navy,
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            )
            Text(
                text = "Berikut adalah Jenis-jenis Bantuan Sosial yang dapat di dapatkan",
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = black
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp, end = 5.dp),
                ) {
                if (groupedBansos.isEmpty()) {
                    item {
                        Text(
                            text = "Belum ada data",
                        )
                    }
                } else {
                    groupedBansos.forEach { (init, data) ->
                        items(items = data, key = { it.bansosProviderId }) { data ->
                            ItemProfileBansos(
                                id = data.bansosProviderId,
                                name = data.name,
                                photo = data.logoUrl,
                                navigateToDetail = navigateToDetailBansos
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp, 8.dp, 20.dp, 8.dp)
        ) {
            BtnCekBansos(navController)
        }

    }
}