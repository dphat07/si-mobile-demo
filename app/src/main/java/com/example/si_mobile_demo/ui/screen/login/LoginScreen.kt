package com.example.si_mobile_demo.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.si_mobile_demo.R
import com.example.si_mobile_demo.ui.components.SiButton
import com.example.si_mobile_demo.ui.components.SiButtonUiState
import com.example.si_mobile_demo.ui.theme.PrimitiveColors
import com.example.si_mobile_demo.ui.theme.PrimitiveElevation
import com.example.si_mobile_demo.ui.theme.PrimitiveIconSize
import com.example.si_mobile_demo.ui.theme.PrimitiveShape
import com.example.si_mobile_demo.ui.theme.PrimitiveSpacing

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
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

            Text(
                modifier = Modifier,
                text = "Đăng Nhập",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = Color(0xFF102A43),
            )
            SiButton(
                modifier = Modifier
                    .width(300.dp)
                    .height(45.dp),
                SiButtonUiState(
                    text = "ĐĂNG NHẬP",
                )
            )
        }

    }

}
