package com.example.link2.presentation.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.link2.presentation.screens.login_screen2.LoginScreenPage2
import com.example.link2.presentation.screens.splash_screen.SplashScreen
import com.example.linkofficial.data.repository.Repository
import com.example.linkofficial.presentation.screens.landing_screen.LandingScreen
import com.example.linkofficial.presentation.screens.login_screen2.LoginViewModel
import com.example.linkofficial.presentation.screens.register_screen_2.RegisterScreenPage2
import com.example.linkofficial.presentation.screens.register_screen_2.RegisterViewModel
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun AppNavigator(repository: Repository, context: Context)
{
//    val viewModel: UserProfileViewModel = hiltViewModel()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen) {
        composable(Screen.SplashScreen) {
            // Navigate to the login page after a delay
            LaunchedEffect(Unit) {
                delay(3000)  // delay for 3 seconds
                navController.navigate(Screen.LandingScreen)
            }
            SplashScreen()
        }
        composable(Screen.LandingScreen) {
            Crossfade(
                targetState = Screen.LandingScreen,
                animationSpec = tween(durationMillis = 1000),
                label = ""
            ) {
                LandingScreen(navController)
            }
        }
        composable(Screen.RegisterScreen2) {
            RegisterScreenPage2(viewModel = RegisterViewModel(repository), navController)
        }
        composable(Screen.LoginScreen) {
            LoginScreenPage2(navController, viewModel = LoginViewModel(repository), context = context, repository = repository)
        }
//        composable(Screen.DashboardScreen) {
//
//        }

//        composable(Screen.NearMeScreen){
//            NearMeScreen(navController)
//
//        }
//        composable(Screen.UploadPPicScreen) {
//            ProfilePictureUpload(navController)
//        }
//        composable(Screen.ProfileScreen) {
//            ProfileScreen(navController, viewModel)
//        }
    }
}
