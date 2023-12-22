package com.bangkit.genaidclean.ui.components.admin

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.screen.admin.bansos.AdminBansosViewModel
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.VerifikasiViewModel
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.navyLight
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.ui.theme.yellow
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.launch
import com.bangkit.genaidclean.data.remote.response.ResultItem as BansosResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FabFilter(
    modifier: Modifier = Modifier,
    ctx: Context = LocalContext.current,
    viewModel: AdminBansosViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(ctx)
        )
    ),
) {
    var isFilterOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ExtendedFloatingActionButton(
        onClick = { isFilterOpen = true },
        containerColor = navy,
        contentColor = whiteBlueLight,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.rounded_filter_list_24),
            contentDescription = null,

            )
        Text(
            text = "Filter",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            ),
            modifier = modifier.padding(start = 16.dp)
        )
    }

    if (isFilterOpen) {
        LaunchedEffect(true) {
            viewModel.getBansos()
        }
        ModalBottomSheet(
            onDismissRequest = { isFilterOpen = false },
            shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
            containerColor = navy,
            modifier = modifier
        ) {

            val bansosResult by viewModel.state.collectAsState()
            when (bansosResult) {
                is State.Loading -> {}
                is State.Success -> {
                    SheetContent(
                        onDismissRequest = {
                            scope.launch { sheetState.hide() }
                                .invokeOnCompletion { isFilterOpen = false }
                        },
                        dataBansos = (bansosResult as State.Success<Bansos>).data.result,
                    )
                }

                is State.Error -> {}
            }


        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    dataBansos: List<BansosResult> = emptyList(),
    ctx: Context = LocalContext.current,
    viewModel: VerifikasiViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(ctx)
        )
    ),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 0.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically,
        ) {
            Text(
                text = "Filter",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    color = whiteBlueLight,
                ),
                modifier = modifier.weight(1f)
            )
            IconButton(
                onClick = { onDismissRequest() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = whiteBlueLight
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = null,
                    modifier = modifier.size(30.dp)
                )
            }
        }

        Divider(
            color = whiteBlueLight,
            modifier = modifier.padding(bottom = 16.dp, top = 8.dp),
            thickness = 2.dp
        )

        Text(
            text = "Jenis bantuan",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                color = whiteBlueLight,
            ),
            modifier = modifier.padding(bottom = 16.dp)
        )


        var selectedChip by remember { mutableStateOf<String?>(null) }

        FlowRow(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            dataBansos.map { it.name }.forEach { label ->
                Chips(
                    label = label,
                    selected = selectedChip == label,
                    onCLick = {
                        selectedChip = if (selectedChip == label) null else label
                    }
                )
            }
        }



        Row(
            modifier = modifier.padding(vertical = 16.dp)
        ) {
            OutlinedButton(
                onClick = {
                    selectedChip = null
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = whiteBlueLight,
                ),
                border = BorderStroke(2.dp, whiteBlueLight)
            ) {
                Text(
                    text = "Reset",
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
                    selectedChip?.let { selectedValue ->
                        val filter  = dataBansos.first { it.name == selectedValue }.bansosProviderId
                        viewModel.fetchData(filter)
                        onDismissRequest()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = whiteBlueLight,
                    contentColor = navy
                ),
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Terapkan",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    )
                )

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chips(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onCLick: () -> Unit = {},
    label: String = "",
) {
    FilterChip(
        onClick = { onCLick() },
        selected = selected,
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    textAlign = TextAlign.Center,
                )
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = navyLight,
            labelColor = whiteBlueLight,
            selectedContainerColor = yellow,
            selectedLabelColor = navy,
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderWidth = 0.dp
        )
    )
}

