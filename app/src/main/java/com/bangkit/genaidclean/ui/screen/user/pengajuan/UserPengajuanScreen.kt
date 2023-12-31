package com.bangkit.genaidclean.ui.screen.user.pengajuan

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight


@Composable
fun UserPengajuanScreen(
    navController: NavHostController,
    navigateToQuestion: (Int) -> Unit,
    context: Context = LocalContext.current,
    viewModel: UserPengajuanViewModel = viewModel(
        factory = ViewModelFactory(Inject.provideRepository(context))
    ),
) {
    val groupedBansos by viewModel.groupedBansos.collectAsState()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Pengajuan",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(14.dp)
            )

        }
        Column(
            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .fillMaxSize()
                .padding(8.dp)
                .background(color = whiteBlueLight),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pilih Jenis Bansos",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(20.dp)
            )

//            BtnPengajuan(navController = navController, label = "BPNT")
//            BtnPengajuan(navController = navController, label = "PKH")
//            BtnPengajuan(navController = navController, label = "PBIJK")

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp, end = 5.dp)
            ) {
                groupedBansos.forEach { (init,data) ->
                    items(data, key = { it.bansosProviderId }) { data ->
                        BansosItem(
                            id = data.bansosProviderId,
                            name = data.name,
                            navigateToQuestion = navigateToQuestion
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BansosItem(
    id: Int,
    name: String,
    navigateToQuestion: (Int) -> Unit,
) {
    // You can customize this part based on your UI requirements
    Column {
        Button(
            onClick = { navigateToQuestion(id) },
            contentPadding = PaddingValues(20.dp, 12.dp, 20.dp, 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = navy,
                contentColor = whiteBlue
            )
        ) {
            Text(text = name, color = whiteBlueLight)
        }

    }


}


// TODO: ERASE, NOTE USED
@Composable
fun AskPengajuanScreen(navController: NavHostController) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = { navController.navigate("pengajuan") }) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    Modifier
                        .size(40.dp)
                        .padding(top = 14.dp)
                )

            }
            Text(
                text = "Question",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 14.dp)
            )

        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(color = whiteBlueLight)
                .padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BPNT",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Text(
                text = "Isilah data dibawah ini secara jujur dan benar",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )

            //TODO: implements question api

//            Ask1()
//            Ask2()
//            Ask3()
//            Ask4()
//            Ask5()
//            Ask6()
//            Ask7()
//            Ask8()
//            Ask9()
//            Ask10()
//            Ask11()
//            Ask12()
//            AskImage()
            BtnSubmitAjuan(navController = navController)
        }

    }
}

@Composable
fun BtnSubmitAjuan(
    navController: NavHostController,
) {
    Column {
        Button(
            onClick = { navController.navigate("home") },
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = navy,
                contentColor = whiteBlue,
            ),

            ) {
            Text(
                text = "Submit",
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,

                )

        }
    }
}