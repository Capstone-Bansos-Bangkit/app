package com.bangkit.genaidclean.ui.screen.admin.verifikasi

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.admin.AttachmentResultItem
import com.bangkit.genaidclean.data.remote.response.admin.DetailSubmissionResult
import com.bangkit.genaidclean.data.remote.response.admin.QuestionerResultItem
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionDetailResponse
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.AlertAutoClose
import com.bangkit.genaidclean.ui.components.ButtonBack
import com.bangkit.genaidclean.ui.components.CustomAlertDialog
import com.bangkit.genaidclean.ui.components.admin.SectionTitle
import com.bangkit.genaidclean.ui.screen.admin.state.ErrorScreen
import com.bangkit.genaidclean.ui.screen.admin.state.LoadingScreen
import com.bangkit.genaidclean.ui.theme.black
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.grey
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.ui.theme.yellow
import com.bangkit.genaidclean.utils.State


@Composable
fun DetailAjuan(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: VerifikasiViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    ),
    submissionId: Int,
    onNavigateBack: () -> Unit,
) {
    val result by viewModel.detailSubmis.collectAsState()

    val resultVerif by viewModel.verifSubmis.collectAsState()

    LaunchedEffect(true) {
        viewModel.getDetailSubmission(submissionId)
    }

    when (result) {
        is State.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }

        is State.Success -> {
            DetailAjuanContent(
                idSubmission = submissionId,
                modifier = modifier,
                dataPengajuan = (result as State.Success<SubmissionDetailResponse>).data.result,
                updateSubmissionStatus = { id, status -> viewModel.verifSubmission(id, status) },
                onNavigateBack = onNavigateBack,
            )
        }

        is State.Error -> {
            (result as State.Error).error?.let {
                ErrorScreen(
                    error = it,
                    retryAction = { viewModel.getDetailSubmission(submissionId) }
                )
            }
        }
    }


    when (resultVerif) {
        is State.Loading -> {} // loading dialog
        is State.Success -> {
            AlertAutoClose(
                msg = "Berhasil",
            )
            onNavigateBack()
        }
        is State.Error -> {
            (resultVerif as State.Error).error?.let {
                AlertAutoClose(msg = it)
            }
        }
    }
}


@Composable
fun DetailAjuanContent(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    dataPengajuan: DetailSubmissionResult,
    updateSubmissionStatus: (Int, String) -> Unit,
    idSubmission: Int,
) {

    val scrollState = rememberScrollState()

    var show by remember {
        mutableStateOf(false)
    }
    var status by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(whiteBlueLight)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(
                state = scrollState,
                enabled = true,
            )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            ButtonBack(
                onClick = { onNavigateBack() },
                containerTint = IconButtonDefaults.iconButtonColors(
                    contentColor = navy
                )
            )
            SectionTitle(title = "Detail Ajuan")
        }

        Text(
            text = dataPengajuan.bansosProviderName,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 8.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                color = black,
                letterSpacing = 1.92.sp,
            ),
            modifier = modifier
                .clip(RoundedCornerShape(32.dp))
                .background(yellow)
                .padding(vertical = 4.dp, horizontal = 24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(bottom = 24.dp, top = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(navy)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.genius_result),
                contentDescription = "",
                modifier = modifier.height(40.dp),
                contentScale = ContentScale.FillHeight
            )

            Divider(
                modifier = modifier
                    .clip(RoundedCornerShape(100))
                    .height(48.dp)
                    .width(4.dp),
                color = yellow
            )

            Image(
                painter = if (dataPengajuan.mlResult == "eligible") {
                    painterResource(id = R.drawable.eliglible_icon)
                } else {
                    painterResource(id = R.drawable.non_eliglible_icon)
                },
                contentDescription = "",
                modifier = modifier.width(100.dp),
                contentScale = ContentScale.FillHeight
            )
        }

        SectionSubtitle(title = "Profil Pengaju")
        CardProfilPengaju(dataPengajuan = dataPengajuan)
        Spacer(modifier = modifier.height(24.dp))

        SectionSubtitle(title = "Kuisioner")
        CardQuestionerResult(
            questionerResult = dataPengajuan.questionerResult
        )
        Spacer(modifier = modifier.height(24.dp))

        SectionSubtitle(title = "Lampiran")
        CardLampiran(
            lampiran = dataPengajuan.attachmentResult
        )
        Spacer(modifier = modifier.height(24.dp))

        Row(
            modifier = modifier.padding(vertical = 16.dp)
        ) {

            OutlinedButton(
                onClick = {
                    show = true
                    status = "tolak"
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = navy,
                ),
                border = BorderStroke(2.dp, navy)
            ) {
                Text(
                    text = "Tolak",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = modifier.width(16.dp))

            Button(
                onClick = {
                    show = true
                    status = "approved"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = navy,
                ),
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Verifikasi",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    )
                )

            }
        }

        //tambahan disni bos
        if (show && status == "tolak") {
            CustomAlertDialog(
                onDismiss = { show = false },
                onConfirm = { show  = false; updateSubmissionStatus(idSubmission, "rejected") },
                titleDialog = "Tolak Ajuan",
                msgDialog = "Apakah anda yakin ingin menolak ajuan ini?",
            )
        } else if ( show && status == "approved") {
            CustomAlertDialog(
                onDismiss = { show = false },
                onConfirm = { show  = false; updateSubmissionStatus(idSubmission, "approved") },
                titleDialog = "Verifikasi Ajuan",
                msgDialog = "Apakah anda yakin ingin menyetujui ajuan ini?",
            )
        }
    }
}


//@Preview
@Composable
fun CardProfilPengaju(
    modifier: Modifier = Modifier,
    dataPengajuan: DetailSubmissionResult,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = whiteBlue
        ),
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iv_profile_placeholder),
                    contentDescription = null,
                    modifier = modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = modifier.width(24.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Nomor KK",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 8.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            color = black1,
                        ),
                        modifier = modifier.height(16.dp)
                    )
                    Text(
                        text = dataPengajuan.noKk,
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 10.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = navy,
                            letterSpacing = 1.28.sp,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = modifier.height(4.dp))

                    Text(
                        text = "Kepala Keluarga",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 8.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            color = black1,
                        ),
                        modifier = modifier.height(16.dp)
                    )
                    Text(
                        text = dataPengajuan.name,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 10.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = navy,
                            letterSpacing = 1.28.sp,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = modifier.height(16.dp))

            FieldData(title = "No. Telepon", value = dataPengajuan.phoneNumber ?: "-")
            FieldData(title = "Email", value = dataPengajuan.email ?: "-")

            Divider(thickness = 2.dp, modifier = modifier.padding(vertical = 12.dp), color = grey)

            FieldData(title = "Alamat", value = dataPengajuan.alamat)
            FieldData(title = "Desa", value = dataPengajuan.desa)
            FieldData(title = "Kecamatan", value = dataPengajuan.kec)
            FieldData(title = "Kabupaten/Kota", value = dataPengajuan.kab)
            FieldData(title = "Provinsi", value = dataPengajuan.prov)

        }
    }
}

@Composable
fun SectionSubtitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            color = navy,
            textAlign = TextAlign.Start,
        ),
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun FieldData(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = black1,
            )
        )

        Text(
            text = value,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = navy,
                textAlign = TextAlign.Right,
            ),

            )
    }
}


//@Preview
@Composable
fun CardQuestionerResult(
    modifier: Modifier = Modifier,
    questionerResult: List<QuestionerResultItem>,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = whiteBlue
        ),
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            for (item in questionerResult) {
                DataQuestionResult(
                    question = item.question,
                    answer = item.answer
                )
            }
        }
    }
}

@Composable
fun DataQuestionResult(
    modifier: Modifier = Modifier,
    question: String,
    answer: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = question,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = black1,
            )
        )

        Text(
            text = answer,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = navy,
                textAlign = TextAlign.Right,
            ),
        )
    }
    Divider(thickness = 1.dp, modifier = modifier.padding(vertical = 16.dp), color = grey)
}

@Composable
fun CardLampiran(
    modifier: Modifier = Modifier,
    lampiran: List<AttachmentResultItem>,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = whiteBlue
        ),
    ) {
        for (item in lampiran) {
            DataLampiran(
                question = item.question,
                src = item.answer
            )
        }
    }
}

@Composable
fun DataLampiran(
    modifier: Modifier = Modifier,
    question: String,
    src: String,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = question,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = black1,
            )
        )

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(src)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.iv_profile_placeholder),
            contentScale = ContentScale.Inside,
            modifier = modifier
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(8.dp))
        )

    }
}