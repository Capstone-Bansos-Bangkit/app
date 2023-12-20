package com.bangkit.genaidclean.ui.components.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.ui.screen.user.cekbansos.CekBansosViewModel
import com.bangkit.genaidclean.ui.theme.black
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue

@Composable
fun ItemHasilCekBansos(
    viewModel: CekBansosViewModel
) {
    Row(
        Modifier
            .background(color = whiteBlue)
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        viewModel.getUserAdress()
        viewModel.userAdress.value.let {

            Column {
                Text(
                    text = "Provinsi",
                    color = navy,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(4.dp)
                )

                if (it != null) {
                    Text(
                        text = it.result?.prov ?: "",
                        color = black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Kecamatan",
                    color = navy,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(4.dp)
                )
                if (it != null) {
                    Text(
                        text = it.result?.kec ?: "",
                        color = black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
            Column(modifier = Modifier.padding(start = 60.dp)) {
                Text(
                    text = "Kabupaten",
                    color = navy,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(4.dp)
                )

                if (it != null) {
                    Text(
                        text = it.result?.kab ?: "",
                        color = black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Kelurahan",
                    color = navy,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(4.dp)
                )

                if (it != null) {
                    Text(
                        text = it.result?.desa ?: "",
                        color = black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }

        }


    }

}


@Composable
fun CardStatusUmur(
    viewModel: CekBansosViewModel
) {


    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = whiteBlue,
        ),
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
    ){

        Column {

            viewModel.getStatusName()
            viewModel.userStatusName.value.let {
                Row (
                    Modifier
                        .background(color = whiteBlue)
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Column (modifier = Modifier.padding(4.dp)){
                        Text(
                            text = "Nama",
                            color = navy,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(4.dp)
                        )

                        if (it != null) {
                            Text(
                                text = it.result?.name?: "",
                                color = black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }

                    Column (modifier = Modifier.padding(4.dp)){
                        Text(
                            text = "Umur",
                            color = navy,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(45.dp, 4.dp, 4.dp, 4.dp)
                        )

                        if (it != null) {
                            Text(
                                text = it.result?.age.toString(),
                                color = black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(45.dp, 4.dp, 4.dp, 4.dp)
                            )
                        }
                    }

                }

            }
            CardStatusBansos(viewModel = viewModel)

        }


    }



}

@Composable
fun CardStatusBansos(
    viewModel: CekBansosViewModel
) {
    val statusListBansos = viewModel.statusListBansos.value

    LaunchedEffect(viewModel) {
        viewModel.fetchStatusList()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(statusListBansos) { statusListItem ->
            statusListItem?.let { item ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item.bansosName ?: "",
                        color = navy,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(4.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Status : ${item.status}",
                            color = black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 11.sp
                        )

                        Text(
                            text = "Periode : ${item.periode}",
                            color = black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}