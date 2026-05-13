package com.example.si_mobile_demo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.si_mobile_demo.R
import com.example.si_mobile_demo.ui.theme.PrimitiveColors
import com.example.si_mobile_demo.ui.theme.PrimitiveIconSize
import com.example.si_mobile_demo.ui.theme.PrimitiveShape
import com.example.si_mobile_demo.ui.theme.PrimitiveSpacing
import com.example.si_mobile_demo.ui.theme.SiMobileDemoTheme


@Immutable
data class SiTextFieldUiState(
    val value: String,
    val onValueChange: (String) -> Unit,
    val label: String,
    val placeholder: String,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val isError: Boolean? = false,
    val errorMessage: String? = null,
    val isPassword: Boolean = false,
)

@Composable
fun SiTextField(
    modifier: Modifier = Modifier,
    uiState: SiTextFieldUiState,
) {
    var textState by remember { mutableStateOf(TextFieldValue(text = uiState.value)) }
    var passwordVisible by remember { mutableStateOf(false) }

    if (textState.text != uiState.value) {
        LaunchedEffect(uiState.value) {
            textState = textState.copy(
                text = uiState.value,
                selection = TextRange(uiState.value.length)
            )
        }
    }

    val borderColor = when {
        uiState.isError == true -> PrimitiveColors.Red
        else -> PrimitiveColors.BlueDeep
    }


    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = uiState.label,
            modifier = Modifier.padding(bottom = PrimitiveSpacing.Xs2),
            style = SiMobileDemoTheme.typography.label.copy(
                fontWeight = SiMobileDemoTheme.typography.subtitle1.fontWeight
            ),
            color = PrimitiveColors.BlueDeep,
        )
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(
                    color = borderColor,
                    width = 1.dp,
                    shape = PrimitiveShape.Lg
                )
                .background(color = PrimitiveColors.White, shape = PrimitiveShape.Lg)
                .padding(horizontal = PrimitiveSpacing.Md, vertical = PrimitiveSpacing.Sm),
            textStyle = SiMobileDemoTheme.typography.body1Bold.copy(color = PrimitiveColors.BlueDeep),
            cursorBrush = SolidColor(PrimitiveColors.BlueDeep),
            value = textState,
            onValueChange = {
                textState = it
                if (uiState.value != it.text) {
                    uiState.onValueChange(it.text)
                }
            },
            maxLines = 1,
            singleLine = true,
            visualTransformation = if (uiState.isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                        if (textState.text.isEmpty()) {
                            Text(
                                text = uiState.placeholder,
                                style = SiMobileDemoTheme.typography.body1Bold,
                                color = PrimitiveColors.Grey300
                            )
                        }
                        innerTextField()
                    }

                    if (uiState.isPassword) {
                        AsyncImage(
                            model = if (passwordVisible) R.drawable.login_password_display_visible
                            else R.drawable.login_password_display_invisible,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = PrimitiveSpacing.Xs)
                                .size(PrimitiveIconSize.Md)
                                .clickable { passwordVisible = !passwordVisible }
                        )
                    } else if (uiState.trailingIcon != null) {
                        uiState.trailingIcon()
                    }
                }
            }
        )
        if (uiState.isError == true) {
            Text(
                text = uiState.errorMessage ?: "",
                color = PrimitiveColors.Red,
                style = SiMobileDemoTheme.typography.body1,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun SiTextFieldPreview() {
    SiTextField(
        uiState = SiTextFieldUiState(
            value = "",
            onValueChange = {},
            label = "Password",
            placeholder = "Nhập mật khẩu",
            isPassword = true
        )
    )
}
