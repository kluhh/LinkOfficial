package com.example.linkofficial.presentation.screens.landing_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.link2.R
import com.example.link2.presentation.navigation.Screen
import com.example.linkofficial.presentation.a_common_components.AnimatedBackground
import com.example.linkofficial.presentation.screens.a_common_components.AppLogo
import com.example.linkofficial.presentation.screens.a_common_components.ButtonLoginRegister
import com.example.linkofficial.ui.theme.BlueKl
import com.example.linkofficial.ui.theme.DarkColorScheme
import com.example.linkofficial.ui.theme.OrangeKl
import com.example.linkofficial.ui.theme.PinkKl
import com.example.linkofficial.ui.theme.YellowKl
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedBackground() // Add the animated background as the first child
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 217.dp)
        ) {
            AppLogo(
                logo = painterResource(id = R.drawable.link_logo),
                modifier = Modifier.size(301.dp),
                contentScale = ContentScale.Fit,
                contentDescription = "App Logo",

                )
            Spacer(
                modifier = Modifier.height(100.dp)
            )

            ButtonLoginRegister(
                onClick = { navController.navigate(Screen.RegisterScreen2) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                    .height(49.dp),
                text = "Sign Up",
                shape = ButtonDefaults.elevatedShape,
                colors = ButtonDefaults.buttonColors(containerColor = DarkColorScheme.secondary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 21.sp

            )
            val annotatedString = buildAnnotatedString {
                append("Already have an account? ")
                withStyle(style = SpanStyle(color = Color.White, textDecoration = TextDecoration.Underline)) {
                    append(" Log In")
                }
            }
            ClickableText(text = annotatedString,
                modifier = Modifier.padding(top = 17.dp),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
                onClick = { navController.navigate(Screen.LoginScreen) })

        }
    }

}





