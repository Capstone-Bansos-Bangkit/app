package com.bangkit.genaidclean.ui.screen.user.profile

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.genaidclean.AuthActivity
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.user.Email
import com.bangkit.genaidclean.ui.components.user.ImageAdd
import com.bangkit.genaidclean.ui.components.user.Info
import com.bangkit.genaidclean.ui.components.user.NoTlp
import com.bangkit.genaidclean.ui.theme.red
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    context: Context
) {
    val viewModel: UserProfileViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    )

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(14.dp)
            )

        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .background(whiteBlueLight),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            ImageAdd(viewModel = viewModel, context = context)
            Info(navController = navController, viewModel = viewModel)
            Email(navController = navController, viewModel = viewModel)
            NoTlp(navController = navController, viewModel = viewModel)

            Button(
                onClick = {
//                    val intent = Intent(context, AuthActivity::class.java)
//                    intent.flags =
//                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    context.startActivity(intent)
////                    Utils.setLoginStatus(context, false)
                    viewModel.logout()

                },
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(red),

                ) {
                Text(
                    text = "LogOut",
                )
            }

        }
    }
}