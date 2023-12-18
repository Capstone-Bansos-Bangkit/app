package com.bangkit.genaidclean.ui.components.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R
import com.bangkit.genaidclean.ui.theme.navy

@Preview
@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String = "Lorem Ipsum Dolor Sit Amet"
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            color = navy,
        ),
        modifier = modifier
            .width(200.dp)
            .padding(vertical = 24.dp)
    )
}