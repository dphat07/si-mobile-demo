package com.example.si_mobile_demo.ui.screen.select_class

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.si_mobile_demo.data.model.CurrentClassInfo
import com.example.si_mobile_demo.domain.repository.AuthRepository
import kotlinx.coroutines.launch

data class SelectClassUiState(
    val classes: List<CurrentClassInfo> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class SelectClassViewModel(private val repository: AuthRepository) : ViewModel() {
    
    var uiState by mutableStateOf(SelectClassUiState())
        private set

    init {
        fetchClasses()
    }

    private fun fetchClasses() {
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            repository.getClasses()
                .onSuccess { classes ->
                    uiState = uiState.copy(classes = classes, isLoading = false)
                }
                .onFailure { e ->
                    uiState = uiState.copy(isLoading = false, error = e.message)
                }
        }
    }

    fun selectClass(classId: String) {
        if (uiState.isLoading) return
        
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            repository.loginUserToClass(classId)
                .onSuccess {
                    uiState = uiState.copy(isSuccess = true)
                }
                .onFailure { e ->
                    uiState = uiState.copy(error = e.message)
                }
            uiState = uiState.copy(isLoading = false)
        }
    }
}
