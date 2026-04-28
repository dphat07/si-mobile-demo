package com.example.si_mobile_demo.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.si_mobile_demo.ui.theme.PrimitiveColors
import com.example.si_mobile_demo.ui.theme.PrimitiveIconSize
import com.example.si_mobile_demo.ui.theme.PrimitiveShape
import com.example.si_mobile_demo.ui.theme.PrimitiveSpacing
import com.example.si_mobile_demo.ui.theme.SiMobileDemoTheme

@Immutable
data class SiButtonUiState(
    val text: String,
    val iconUrl: String? = null,
    val iconRes: Int? = null,
    val isLoading: Boolean = false,
    val isEnable: Boolean = true,
    val colors: List<Color> = listOf(PrimitiveColors.Blue46, PrimitiveColors.Blue66)
)

@Composable
fun SiButton(
    modifier: Modifier = Modifier,
    uiState: SiButtonUiState,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        label = "button_scale"
    )

    val backgroundBrush = if (uiState.isEnable) {
        Brush.linearGradient(colors = uiState.colors)
    } else {
        Brush.linearGradient(colors = listOf(PrimitiveColors.Grey300, PrimitiveColors.Grey300))
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(PrimitiveShape.Lg)
            .background(backgroundBrush)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = uiState.isEnable && !uiState.isLoading
            ) { 
                onClick() 
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(end = PrimitiveSpacing.Sm2)
                        .size(PrimitiveIconSize.Sm),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }
            Text(
                text = uiState.text,
                style = SiMobileDemoTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                color = Color.White
            )

            if (uiState.iconUrl?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.width(PrimitiveSpacing.Sm))
                AsyncImage(uiState.iconUrl, null, modifier = Modifier.size(20.dp))
            } else if (uiState.iconRes != null) {
                Spacer(modifier = Modifier.width(PrimitiveSpacing.Sm))
                AsyncImage(uiState.iconRes, null, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun SiButtonPreview() {
    Box(
        Modifier
            .background(PrimitiveColors.White)
            .padding(20.dp)
    ) {
        SiButton(
            uiState = SiButtonUiState(
                text = "Login"
            )
        )
    }
}
