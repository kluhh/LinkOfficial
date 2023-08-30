package com.example.link2.presentation.screens.login_screen2

import SocialButton
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.link2.R
import com.example.link2.presentation.navigation.Screen
import com.example.link2.presentation.screens.a_common_components.Header
import com.example.link2.presentation.screens.a_common_components.PasswordTextField
import com.example.linkofficial.data.repository.Repository
import com.example.linkofficial.presentation.a_common_components.AnimatedBackground
import com.example.linkofficial.presentation.screens.a_common_components.ButtonLoginRegister
import com.example.linkofficial.presentation.screens.a_common_components.CustomTextField
import com.example.linkofficial.presentation.screens.login_screen2.LoginViewModel
import com.example.linkofficial.ui.theme.DarkColorScheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreenPage2(
    navController: NavController,
    viewModel: LoginViewModel,
    context: Context?,
    repository: Repository
) {

    //region State variables
    val email by viewModel.email.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val emailError by viewModel.emailError.collectAsState(null)
    val passwordError by viewModel.passwordError.collectAsState(null)
    //endregion

    //region Google Sign In
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)
                    if (context != null) {
                        handleGoogleSignInResult(task, repository, navController, context)
                    }
                }
            }
        }

    //endregion

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedBackground() // Add the animated background as the first child

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Header(title = "Log In", color = Color.White)
            Spacer(
                modifier = Modifier
                    .height(35.dp)
            )


            CustomTextField(
                modifier = Modifier.padding(start = 35.dp, end = 35.dp),
                label = "Email",
                value = email,
                onValueChange = viewModel::onEmailChanged,
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Default.Email,
                error = emailError ?: "",
            )
            PasswordTextField(
                modifier = Modifier.padding(start = 35.dp, end = 35.dp),
                label = "Password",
                value = password,
                onValueChange = viewModel::onPasswordChanged,
                leadingIcon = Icons.Default.Lock,
//                error = passwordError ?: ""

            )

            ButtonLoginRegister(
                onClick = {
                    viewModel.onLoginClickedUsingLet()
                    navController.navigate(Screen.UploadPPicScreen)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                    .height(49.dp),
                text = "Log In",
                shape = ButtonDefaults.elevatedShape,
                colors = ButtonDefaults.buttonColors(containerColor = DarkColorScheme.secondary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 19.sp
            )
            SocialButton(
                onClick = {},
                text = "Sign in with Google",
                ButtonDefaults.buttonColors(
                    containerColor = Color.White, contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                    .height(49.dp)
                    .fillMaxWidth(),
                shape = ButtonDefaults.elevatedShape,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 19.sp,
                icon = painterResource(id = R.drawable.google_icon)

            )
            SocialButton(
                onClick = {},
                text = "Sign in with Facebook",
                ButtonDefaults.buttonColors(
                    containerColor = Color.White, contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                    .height(49.dp)
                    .fillMaxWidth(),
                shape = ButtonDefaults.elevatedShape,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 19.sp,
                icon = painterResource(id = R.drawable.facebook_icon)

            )

            val annotatedString = buildAnnotatedString {
                append("Don't have an account? ")
                withStyle(style = SpanStyle(color = Color.White, textDecoration = TextDecoration.Underline)) {
                    append(" Sign Up")
                }
            }

            ClickableText(text = annotatedString,
                modifier = Modifier.padding(top = 17.dp),
                style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Normal),
                onClick = { navController.navigate(Screen.RegisterScreen2) })
        }
    }
}

@Preview
@Composable
fun LoginPreview() {

}

fun handleGoogleSignInResult(
    task: Task<GoogleSignInAccount>,
    repository: Repository,
    navController: NavController,
    context: Context
) {
    try {
        val account = task.getResult(ApiException::class.java)
        println(account.idToken)
        CoroutineScope(Dispatchers.Main).launch {
            repository.googleLogin(account?.idToken)?.let {
                navController.navigate(Screen.UploadPPicScreen)
            }
        }
    } catch (e: ApiException) {
//        Toast.makeText(context, "Sign in failed:\n${e.message}", Toast.LENGTH_SHORT).show()
    }
}




