package com.bangkit.genaidclean.ui.screen.user.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.AlertAutoClose
import com.bangkit.genaidclean.ui.theme.black
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.utils.State


//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEmail(
    ctx: Context = LocalContext.current,
    viewModel: UserProfileViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(
                ctx
            )
        )
    ),
    navController: NavHostController,
) {

    var newEmail by rememberSaveable { mutableStateOf("") }
    val userProfile by viewModel.userProfile
    val updateEmailState by viewModel.updateEmailState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getUserProfile()
    }

    when (updateEmailState) {
        is State.Success -> {
            AlertAutoClose(msg = "Email Updated")
            navController.popBackStack(route = Screen.UserProfil.route, inclusive = false)
        }

        is State.Error -> {
            Toast.makeText(ctx, (updateEmailState as State.Error).error, Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }

    Column {

        //top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = {
//                navController.navigate(Screen.UserProfil.route)
                navController.navigateUp()
            }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(top = 14.dp)
                )
            }

            Text(
                text = "Edit Email",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 14.dp)
            )

        }


        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = whiteBlue,
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Email Terdaftar",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 8.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = black,
                    )
                )
                Text(
                    text = userProfile?.result?.email.orEmpty(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = navy,
                        letterSpacing = 1.28.sp,
                    )
                )
            }
        }

        // email field
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = "Email",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = navy,
            )
            OutlinedTextField(
                value = newEmail,
                onValueChange = { newEmail = it },
                placeholder = {
                    Text(text = "Masukkan Email Baru Anda")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                }

            )

            // Button to update user profile
            Button(
                onClick = {
                    viewModel.updateEmail(newEmail)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Update Email")
            }
        }
    }
}