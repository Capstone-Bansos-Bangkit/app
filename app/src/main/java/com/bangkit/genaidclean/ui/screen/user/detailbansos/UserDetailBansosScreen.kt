package com.bangkit.genaidclean.ui.screen.user.detailbansos

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.ui.ViewModelFactory
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bangkit.genaidclean.navigation.utils.Screen

//@Preview
@Composable
fun UserDetailBansosScreen(
    bansosId: Int,
    context: Context,
    navController: NavHostController,
) {
    val viewModel: UserDetailBansosViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    )
    var bansosState by remember { mutableStateOf<List<ResultItem>>(emptyList()) }

    LaunchedEffect(bansosId) {
        // Menggunakan viewModel untuk mendapatkan data produk berdasarkan ID
        bansosState = viewModel.getBansosById(bansosId)
    }

    bansosState.forEach { selectedBansos ->
        ContentDetailProfileBansos(
            modifier = Modifier,
            navController = navController,
            logoUrl = selectedBansos.logoUrl,
            description = selectedBansos.description,
            alias = selectedBansos.alias
        )
    }

}

@Composable
fun ContentDetailProfileBansos(
    modifier: Modifier,
    navController: NavHostController,
    logoUrl: String,
    description: String,
    alias: String,

    ) {

    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = { navController.navigate(Screen.UserHome.route) }) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier
                        .size(40.dp)
                        .padding(top = 14.dp)
                )

            }

            Text(
                text = "Detail Bantuan Sosial",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 14.dp)
            )

        }
        Column(modifier = modifier) {
            Column(
                modifier = modifier
                    .padding(32.dp, 0.dp, 32.dp, 16.dp)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Surface(
                    modifier = modifier
                        .size(154.dp)
                        .padding(5.dp),
                    shape = CircleShape,
                    border = BorderStroke(0.5.dp, Color.LightGray)
                ) {
                    AsyncImage(
                        model = logoUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .padding(14.dp)
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = alias,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(bottom = 16.dp)
                )
                Divider()
                Text(
                    text = description,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}