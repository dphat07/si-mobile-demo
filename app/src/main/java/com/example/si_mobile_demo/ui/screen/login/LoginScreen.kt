package com.example.si_mobile_demo.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.si_mobile_demo.R
import com.example.si_mobile_demo.navigation.LoginRoute
import com.example.si_mobile_demo.navigation.SelectClassRoute
import com.example.si_mobile_demo.ui.components.SiButton
import com.example.si_mobile_demo.ui.components.SiButtonUiState
import com.example.si_mobile_demo.ui.components.SiTextField
import com.example.si_mobile_demo.ui.components.SiTextFieldUiState
import com.example.si_mobile_demo.ui.theme.PrimitiveColors
import com.example.si_mobile_demo.ui.theme.PrimitiveElevation
import com.example.si_mobile_demo.ui.theme.PrimitiveIconSize
import com.example.si_mobile_demo.ui.theme.PrimitiveShape
import com.example.si_mobile_demo.ui.theme.PrimitiveSpacing
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current
    val uiState = viewModel.uiState

    LaunchedEffect(uiState.status) {
        when (val status = uiState.status) {
            is LoginStatus.NeedToSelectClass -> {
                navController.navigate(SelectClassRoute)
                viewModel.clearStatus()
            }
            is LoginStatus.Success -> {
                navController.navigate(status.route) {
                    popUpTo(LoginRoute) { inclusive = true }
                }
                viewModel.clearStatus()
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .padding(horizontal = PrimitiveSpacing.Sm2)
            .background(PrimitiveColors.Blue25)

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .shadow(
                    elevation = PrimitiveElevation.Low,
                    shape = PrimitiveShape.Lg
                )
                .background(PrimitiveColors.White, PrimitiveShape.Lg)
                .padding(PrimitiveSpacing.Sm),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            AsyncImage(
                model = R.drawable.logo,
                contentDescription = null,
                modifier = Modifier.size(PrimitiveIconSize.Giant)
            )

            Spacer(modifier = Modifier.height(PrimitiveSpacing.Sm))

            Text(
                modifier = Modifier,
                text = "Đăng Nhập",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = Color(0xFF102A43),
            )

            Spacer(modifier = Modifier.height(PrimitiveSpacing.Xl))
            
            SiTextField(
                modifier = Modifier,
                uiState = SiTextFieldUiState(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = "Email",
                    placeholder = "Nhập email",
                    isError = uiState.emailError != null,
                    errorMessage = uiState.emailError
                )
            )
            
            Spacer(modifier = Modifier.height(PrimitiveSpacing.Sm2))
            
            SiTextField(
                modifier = Modifier,
                uiState = SiTextFieldUiState(
                    value = uiState.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = "Password",
                    placeholder = "Nhập mật khẩu",
                    isPassword = true,
                    isError = uiState.passwordError != null,
                    errorMessage = uiState.passwordError
                )
            )
            
            Spacer(modifier = Modifier.height(PrimitiveSpacing.Md))
            
            SiButton(
                modifier = Modifier,
                uiState = SiButtonUiState(
                    text = "ĐĂNG NHẬP",
                    isLoading = uiState.isLoading,
                    isEnable = !uiState.isLoading
                ),
                onClick = {
                    viewModel.login()
                }
            )
        }
    }
}
