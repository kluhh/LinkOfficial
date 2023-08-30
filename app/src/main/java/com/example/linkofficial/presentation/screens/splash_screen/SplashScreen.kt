package com.example.link2.presentation.screens.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.link2.R
import com.example.linkofficial.presentation.screens.a_common_components.AppLogo
import com.example.linkofficial.ui.theme.DarkColorScheme
import com.example.linkofficial.ui.theme.LinkOfficialTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DarkColorScheme.primary
            ),
        contentAlignment = Alignment.Center
    ) {
        AppLogo(
            logo = painterResource(id = R.drawable.link_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(301.dp)
                .padding(top = 7.dp),
            contentScale = ContentScale.Fit,

        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    LinkOfficialTheme {
        SplashScreen()
    }
}
