package com.bangkit.genaidclean.ui.components.user

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.navigation.utils.Screen
import com.bangkit.genaidclean.ui.theme.navy


@Composable
fun BtnCekBansos(
    navController: NavHostController,
) {
    Button(
        onClick = {
            navController.navigate(Screen.CekBansos.route)
        },
        contentPadding = PaddingValues(20.dp, 12.dp, 20.dp, 12.dp),
        colors = ButtonDefaults.buttonColors(navy),
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = stringResource(id = R.string.cekbansos))

    }

}

@Composable
fun BtnCoba(navController: NavHostController) {
    Button(
        onClick = {navController.navigate("coba") },
        contentPadding = PaddingValues(20.dp, 12.dp, 20.dp, 12.dp),
        colors = ButtonDefaults.buttonColors(navy),
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier= Modifier.size(ButtonDefaults.IconSize),)
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = stringResource(id = R.string.cekbansos))

    }

}