package com.example.linkofficial.presentation.a_common_components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.linkofficial.ui.theme.BlueKl
import com.example.linkofficial.ui.theme.OrangeKl
import com.example.linkofficial.ui.theme.PinkKl
import kotlinx.coroutines.delay

@Composable
fun AnimatedBackground() {
    val colorList = listOf(
        BlueKl,
        OrangeKl,
        PinkKl
    )
    var colorIndex by remember { mutableStateOf(0) }

    val bgColor by animateColorAsState(
        targetValue = colorList[colorIndex],
        animationSpec = tween(durationMillis = 1000, easing = FastOutLinearInEasing)
    )

    // Update the colorIndex after each color transition
    LaunchedEffect(bgColor) {
        delay(5000) // Wait for the duration of the color transition
        colorIndex = (colorIndex + 1) % colorList.size // Move to the next color in the list
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        // Other content within the Box
    }
}
