package com.example.si_mobile_demo.ui.screen.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.si_mobile_demo.data.model.CurrentClassInfo
import com.example.si_mobile_demo.data.model.LogInPayload
import com.example.si_mobile_demo.domain.repository.AuthRepository
import com.example.si_mobile_demo.navigation.AppRoute
import com.example.si_mobile_demo.navigation.MainRoute
import kotlinx.coroutines.launch

sealed interface LoginStatus {
    data object Idle : LoginStatus
    data class NeedToSelectClass(val classes: List<CurrentClassInfo>) : LoginStatus
    data class Success(val route: AppRoute) : LoginStatus
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val status: LoginStatus = LoginStatus.Idle
)

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail, emailError = null)
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword, passwordError = null)
    }

    fun clearStatus() {
        uiState = uiState.copy(status = LoginStatus.Idle)
    }

    fun login() {
        if (uiState.isLoading) return

        val isEmailBlank = uiState.email.isBlank()
        val isEmailInvalid = !Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()
        val isPasswordInvalid = uiState.password.length < 6

        uiState = uiState.copy(
            emailError = when {
                isEmailBlank -> "Email không được để trống"
                isEmailInvalid -> "Định dạng email không hợp lệ"
                else -> null
            },
            passwordError = if (isPasswordInvalid) "Mật khẩu phải từ 6 ký tự" else null
        )

        if (uiState.emailError != null || uiState.passwordError != null) return

        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            repository.login(LogInPayload(uiState.email, uiState.password))
                .onSuccess { classes ->
                    when {
                        classes.size == 1 -> {
                            loginUserToClass(classes[0].classId)
                        }
                        classes.size > 1 -> {
                            uiState = uiState.copy(status = LoginStatus.NeedToSelectClass(classes), isLoading = false)
                        }
                        else -> {
                            uiState = uiState.copy(isLoading = false, emailError = "Người dùng không thuộc lớp nào")
                        }
                    }
                }
                .onFailure { e ->
                    Log.e("AuthRepo", "Lỗi login: ${e.message}")
                    uiState = uiState.copy(isLoading = false, emailError = "Sai email hoặc mật khẩu")
                }
        }
    }

    fun loginUserToClass(classId: String) {
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            repository.loginUserToClass(classId)
                .onSuccess {
                    uiState = uiState.copy(status = LoginStatus.Success(MainRoute))
                }
                .onFailure { e ->
                    Log.e("AuthRepo", "Lỗi lấy custom token: ${e.message}")
                    uiState = uiState.copy(emailError = e.message)
                }
            uiState = uiState.copy(isLoading = false)
        }
    }
}
