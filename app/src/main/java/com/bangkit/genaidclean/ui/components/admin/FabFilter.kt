package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.navy
import com.bangkit.genaidclean.ui.theme.whiteBlueLight

@Composable
fun FabFilter(
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
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
}