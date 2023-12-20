package com.bangkit.genaidclean.ui.screen.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
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
import com.bangkit.genaidclean.AdminActivity
import com.bangkit.genaidclean.AuthViewModel
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.ButtonBack
import com.bangkit.genaidclean.ui.components.TextInput
import com.bangkit.genaidclean.ui.screen.admin.dashboard.DashboardAdminViewModel
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.navyLight
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.utils.State
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showSystemUi = true)
@Composable
fun AdminLogin(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    context:Context = LocalContext.current,
    onNavigateToAdminActivity: () -> Unit = {},
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    ),
) {

    rememberSystemUiController().apply {
        setSystemBarsColor(
            color = navy
        )
    }

    val loginResult = viewModel.loginResult.collectAsState()

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(loginResult) {
        when(loginResult.value) {
            is State.Loading -> {

            }
            is State.Success -> {
                onNavigateToAdminActivity()
                val intent = Intent(context, AdminActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                Toast.makeText(context, "Login Success. Welcome ${(loginResult.value as State.Success<UserModel>).data.name}", Toast.LENGTH_SHORT).show()
            }
            is State.Error -> {

                Toast.makeText(context, (loginResult.value as State.Error).error, Toast.LENGTH_SHORT).show()
                (loginResult.value as State.Error).error?.let { Log.d("AdminLogin", it) }
            }
        }
    }

    Column(
        modifier = modifier
            .background(Color(navy.value))
            .padding(PaddingValues(horizontal = 24.dp, vertical = 16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        ButtonBack(
            onClick = { onNavigateBack() },
            containerTint = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(navyLight.value),
                contentColor = Color(whiteBlueLight.value)
            )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Hi, Admin!",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = Color(whiteBlueLight.value),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Semangat menjalankan amanah, ya!",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    color = Color(whiteBlueLight.value),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Box(modifier = modifier.padding(vertical = 32.dp)) {

            Image(
                painter = painterResource(id = R.drawable.iv_admin),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp),
                alignment = Alignment.Center
            )
        }

        Column(
        ) {

            Column (
                modifier = modifier.padding(vertical = 16.dp)
            ){
                Text(
                    text = "Username",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 8.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = whiteBlueLight,
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = {username = it} ,
                    modifier = modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Badge,
                            contentDescription = null,
                            tint = whiteBlueLight
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = navyLight,
                        unfocusedContainerColor = navyLight,

                        cursorColor = whiteBlueLight,
                        focusedTextColor = whiteBlueLight,
                        unfocusedTextColor = whiteBlueLight,

                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(16.dp),
                )
            }

            Column (
                modifier = modifier.padding(vertical = 16.dp)
            ){
                Text(
                    text = "Password",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 8.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = whiteBlueLight,
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it} ,
                    modifier = modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Lock,
                            contentDescription = null,
                            tint = whiteBlueLight
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = navyLight,
                        unfocusedContainerColor = navyLight,

                        cursorColor = whiteBlueLight,
                        focusedTextColor = whiteBlueLight,
                        unfocusedTextColor = whiteBlueLight,

                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(16.dp),
                )
            }
        }

        Box(
            modifier = modifier
                .fillMaxHeight()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { viewModel.loginAdmin(username, password) },
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(whiteBlueLight.value)),
                contentPadding = PaddingValues(vertical = 12.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                Text(
                    text = "Masuk",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    ),
                    color = Color(navy.value)
                )
            }
        }

    }
}