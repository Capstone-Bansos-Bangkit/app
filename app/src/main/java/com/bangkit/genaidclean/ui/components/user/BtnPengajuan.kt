package com.bangkit.genaidclean.ui.components.user

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlue

@Composable
fun BtnPengajuan(
    navController: NavHostController,
    label: String,
) {
    Button(
        onClick = {navController.navigate("pengajuan/{Id}")
        },
        contentPadding = PaddingValues(20.dp, 12.dp, 20.dp, 12.dp),
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = navy,
            contentColor = whiteBlue
        )
    ) {
        Text(text = label)

    }
}