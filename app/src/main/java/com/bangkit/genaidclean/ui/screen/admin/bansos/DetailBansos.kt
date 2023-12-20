package com.bangkit.genaidclean.ui.screen.admin.bansos

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.ButtonBack
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.screen.admin.state.ErrorScreen
import com.bangkit.genaidclean.ui.screen.admin.state.LoadingScreen
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.utils.State

@Preview
@Composable
fun DetailBansos(
    modifier: Modifier = Modifier,
    bansosId: Int = 0,
    context: Context = LocalContext.current,
    viewModel: AdminBansosViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    ),
    onNavigateBack: () -> Unit = {},
) {

    LaunchedEffect(true) {
        viewModel.getBansosById(bansosId)
    }

    val bansosState by viewModel.bansosDetail.collectAsState()

    when (bansosState) {
        is State.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }
        is State.Success -> {
            BansosDetailContent(
                bansosData = ((bansosState as State.Success<ResultItem>).data),
                onNavigateBack = onNavigateBack
            )
        }

        is State.Error -> {
            (bansosState as State.Error).error?.let {
                ErrorScreen(
                    modifier = modifier.fillMaxSize(),
                    retryAction = { viewModel.getBansosById(bansosId) },
                    error = it
                )
            }
        }

        else -> {}
    }




}

@Composable
fun BansosDetailContent(
    modifier: Modifier = Modifier
        .background(whiteBlue),
    bansosData: ResultItem,
    onNavigateBack: () -> Unit = {},
) {

    Column (
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(color = whiteBlue)
            .padding(horizontal = 16.dp),

    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ){
            ButtonBack(
                onClick = { onNavigateBack() },
                containerTint = IconButtonDefaults.iconButtonColors(
                    containerColor = whiteBlue,
                    contentColor = navy
                )
            )
            SectionTitle(title = "Data Bantuan Sosial")
        }

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, top = 16.dp)
        ){

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(bansosData.logoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.iv_profile_placeholder),
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .clip(RoundedCornerShape(100))
                    .size(96.dp)
            )


            Divider(
                color = navy,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .height(88.dp)
                    .width(2.dp)
            )

            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ){
                Text(
                    text = "Total Periode",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = black1
                    )
                )
                Text(
                    text = bansosData.totalPeriode.toString(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        color = navy,
                    ),
                    maxLines = 1,
                    modifier = modifier
                        .height(20.dp)
                )

                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = "Total Penerima",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = black1
                    )
                )
                Text(
                    text = bansosData.totalPenerima.toString(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        color = navy,
                    ),
                    maxLines = 1,
                    modifier = modifier
                        .height(20.dp)
                )
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Nama Bantuan Sosial",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = whiteBlue
            ),
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(navy)
                .padding(horizontal = 16.dp, vertical = 2.dp)
        )

        Text(
            text = bansosData.alias,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = navy
            ),
            modifier = modifier.padding(horizontal=16.dp, vertical = 4.dp),
        )

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Deskripsi",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = whiteBlue
            ),
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(navy)
                .padding(horizontal = 16.dp, vertical = 2.dp)

        )

        Text(
            text = bansosData.description,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = navy,
                textAlign = TextAlign.Justify,
            ),
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)

        )
        Column (
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ){

            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = navy,
                ),
                border = BorderStroke(
                    width = 2.dp,
                    color = navy
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Daftar Penerima",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = navy,
                    contentColor = whiteBlue
                ),
                enabled = false,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Penyaluran",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

    }
}