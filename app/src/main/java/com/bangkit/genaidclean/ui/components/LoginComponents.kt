package com.bangkit.genaidclean.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.genaidclean.R


@Composable
fun TextInput(
    inputTitle: String,
    inputTitleColor: Color,
    modifier: Modifier = Modifier,

    value: String = "",
    onValueChange: (String) -> Unit,
    colors: TextFieldColors,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column (
        modifier = modifier.padding(vertical = 16.dp)
    ){
        Text(
            text = inputTitle,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 8.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = inputTitleColor,
            ),
            modifier = modifier
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = modifier,
            leadingIcon = leadingIcon,
            isError = isError,
            colors = colors,
            keyboardOptions = keyboardOptions,
            placeholder = placeholder,
            shape = RoundedCornerShape(16.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    TextInput(
        modifier = Modifier,
        leadingIcon = {},
        isError = false,
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        inputTitle = "NIK",
        inputTitleColor = Color.Black,
        onValueChange = {},
        value = "Tes tes",
    )
}