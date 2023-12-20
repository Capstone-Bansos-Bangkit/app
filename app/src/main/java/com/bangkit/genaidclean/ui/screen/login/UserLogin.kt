package com.bangkit.genaidclean.ui.screen.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.genaidclean.AuthViewModel
import com.bangkit.genaidclean.MainActivity
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.components.ButtonBack
import com.bangkit.genaidclean.ui.components.TextInput
import com.bangkit.genaidclean.ui.theme.black1
import com.bangkit.genaidclean.ui.theme.grey
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue
import com.bangkit.genaidclean.ui.theme.whiteBlueLight
import com.bangkit.genaidclean.utils.State
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Preview(showSystemUi = true)
@Composable
fun UserLogin(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onNavigateBack: () -> Unit = {},
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(
            Inject.provideRepository(context)
        )
    )
) {

    rememberSystemUiController().apply {
        setSystemBarsColor(
            color = whiteBlueLight
        )
    }

    val loginResult = viewModel.loginResult.collectAsState()

    var nik by rememberSaveable { mutableStateOf("") }
    var motherName by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(loginResult){
        when(loginResult.value){
            is State.Loading -> {}
            is State.Success -> {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                Toast.makeText(context, "Login Success. Welcome ${(loginResult.value as State.Success<UserModel>).data.name}", Toast.LENGTH_SHORT).show()
            }
            is State.Error -> {

                Toast.makeText(context, (loginResult.value as State.Error).error, Toast.LENGTH_SHORT).show()
                (loginResult.value as State.Error).error?.let { Log.d("UserLogin", it) }
            }
        }
    }

    Column(
        modifier = modifier
            .background(Color(whiteBlueLight.value))
            .padding(PaddingValues(horizontal = 24.dp, vertical = 16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        ButtonBack(
            onClick = { onNavigateBack() },
            containerTint = IconButtonDefaults.filledIconButtonColors(
                containerColor = whiteBlue,
                contentColor = Color(navy.value)
            )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Halo!",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = Color(navy.value),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Untuk dapat masuk ke aplikasi, lengkapi datamu dulu ya!",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    color = Color(navy.value),
                    textAlign = TextAlign.Center,
                ),
                modifier = modifier.padding(horizontal = 64.dp)
            )
        }

        Column(
            modifier = modifier.padding(bottom = 120.dp)
        ) {

            Column (
                modifier = modifier.padding(vertical = 16.dp)
            ){
                Text(
                    text = "NIK",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 8.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = navy,
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = nik,
                    onValueChange = {nik = it} ,
                    modifier = modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_barcode_scanner_24),
                            contentDescription = null,
                            tint = grey
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = whiteBlue,
                        unfocusedContainerColor = whiteBlue,

                        cursorColor = black1,
                        focusedTextColor = black1,
                        unfocusedTextColor = black1,

                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }

            Column (
                modifier = modifier.padding(vertical = 16.dp)
            ){
                Text(
                    text = "Nama Ibu kandung",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 8.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = navy,
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = motherName,
                    onValueChange = {motherName = it} ,
                    modifier = modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_face_4_24),
                            contentDescription = null,
                            tint = grey
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = whiteBlue,
                        unfocusedContainerColor = whiteBlue,

                        focusedTextColor = black1,
                        unfocusedTextColor = black1,

                        cursorColor = black1,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,

                        focusedPlaceholderColor = whiteBlue,
                        unfocusedPlaceholderColor = grey
                    ),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Characters
                    ),
                    placeholder = { Text(text = "NAMA LENGKAP") }
                )
            }

            Column (
                modifier = modifier.padding(vertical = 16.dp)
            ){
                Text(
                    text = "Tanggal Lahir",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 8.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = navy,
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = birthDate,
                    onValueChange = {birthDate = it} ,
                    modifier = modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_event_note_24),
                            contentDescription = null,
                            tint = grey
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = whiteBlue,
                        unfocusedContainerColor = whiteBlue,

                        cursorColor = black1,
                        focusedTextColor = black1,
                        unfocusedTextColor = black1,

                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,

                        focusedPlaceholderColor = whiteBlue,
                        unfocusedPlaceholderColor = grey
                    ),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    placeholder = { Text(text = "yyyy-mm-dd") }
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
                onClick = { viewModel.loginUser(nik, motherName, birthDate) },
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(navy.value)),
                contentPadding = PaddingValues(vertical = 12.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                Text(
                    text = "Submit",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                        textAlign = TextAlign.Center,
                    ),
                    color = Color(whiteBlueLight.value)
                )
            }
        }

    }
}