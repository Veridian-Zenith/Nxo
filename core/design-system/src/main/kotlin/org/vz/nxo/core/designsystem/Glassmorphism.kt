package org.vz.nxo.core.designsystem

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.glassmorphism(
    blur: Float = 10f,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.White.copy(alpha = 0.2f)
): Modifier = this
    .graphicsLayer {
        this.alpha = 0.9f
        this.clip = true
    }
    .drawWithContent {
        drawContent()
        drawRect(
            color = borderColor,
            style = Stroke(width = borderWidth.toPx())
        )
    }
