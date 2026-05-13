package com.example.si_mobile_demo.ui.screen.select_class

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.si_mobile_demo.navigation.LoginRoute
import com.example.si_mobile_demo.navigation.MainRoute
import com.example.si_mobile_demo.ui.components.SiButton
import com.example.si_mobile_demo.ui.components.SiButtonUiState
import com.example.si_mobile_demo.ui.theme.PrimitiveColors
import com.example.si_mobile_demo.ui.theme.PrimitiveSpacing
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SelectClassScreen(
    navController: NavController,
    viewModel: SelectClassViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            navController.navigate(MainRoute) {
                popUpTo(LoginRoute) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PrimitiveSpacing.Sm2)
            .background(PrimitiveColors.Blue25)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "CHỌN LỚP CỦA BẠN",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(PrimitiveSpacing.Sm2))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(PrimitiveSpacing.Sm)
            ) {
                items(uiState.classes) { item ->
                    SiButton(
                        uiState = SiButtonUiState(
                            text = item.title ?: "Lớp ${item.unionId}",
                            isLoading = uiState.isLoading,
                            isEnable = !uiState.isLoading
                        ),
                        onClick = {
                            viewModel.selectClass(item.classId)
                        }
                    )
                }
            }

            // Hiển thị lỗi nếu có
            uiState.error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
