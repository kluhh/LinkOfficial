package com.example.linkofficial.presentation.screens.register_screen_2

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.link2.presentation.navigation.Screen
import com.example.link2.presentation.screens.a_common_components.Header
import com.example.link2.presentation.screens.a_common_components.PasswordTextField
import com.example.linkofficial.presentation.a_common_components.AnimatedBackground
import com.example.linkofficial.presentation.screens.a_common_components.ButtonLoginRegister
import com.example.linkofficial.presentation.screens.a_common_components.CustomTextField
import com.example.linkofficial.presentation.screens.register_screen_2.components.AgreeCheckbox
import com.example.linkofficial.presentation.screens.register_screen_2.components.DateOfBirthPicker
import com.example.linkofficial.presentation.screens.register_screen_2.components.GenderSelection
import com.example.linkofficial.ui.theme.DarkColorScheme
import java.time.LocalDate

@Composable
fun RegisterScreenPage2(viewModel: RegisterViewModel, navController: NavController) {
    //region State observer variables

    val firstName by viewModel.firstName.collectAsState("")
    val lastName by viewModel.lastName.collectAsState("")
    val email by viewModel.email.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val dateOfBirth by viewModel.dateOfBirth.collectAsState(LocalDate.now())
    val gender by viewModel.selectedGender.collectAsState(null)
    val agreeToTerms by viewModel.agreeToTerms.collectAsState()
    val registrationResult by viewModel.registrationResult.collectAsState("")


    val firstNameError by viewModel.firstNameError.collectAsState(null)
    val lastNameError by viewModel.lastNameError.collectAsState(null)
    val emailError by viewModel.emailError.collectAsState(null)
    val passwordError by viewModel.passwordError.collectAsState(null)
    val dateOfBirthError by viewModel.dateOfBirthError.collectAsState(null)

    var registerClicked by remember { mutableStateOf(false) }

    // Launch a coroutine when the register button is clicked
    LaunchedEffect(registerClicked) {
        if (registerClicked) {
            viewModel.registerUser(navController)
            registerClicked = false

        }
    }


    //endregion

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AnimatedBackground()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp)
        ) {
            Header(
                title = "Create Account",
                color = Color.White
            )
            Spacer(
                modifier = Modifier
                    .height(35.dp)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                CustomTextField(
                    label = "First Name",
                    value = firstName,
                    onValueChange = viewModel::onFirstNameChange,
                    leadingIcon = Icons.Default.ContactPage,
                    error = firstNameError ?: "",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.weight(.05f))

                CustomTextField(
                    label = "Last Name",
                    value = lastName,
                    onValueChange = viewModel::onLastNameChange,
                    leadingIcon = Icons.Default.ContactPage,
                    error = lastNameError ?: "",
                    modifier = Modifier.weight(1f)

                )
            }
            CustomTextField(
                label = "Email",
                value = email,
                onValueChange = viewModel::onEmailChanged,
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Default.Email,
                error = emailError ?: ""
            )
            PasswordTextField(
                label = "Password",
                value = password,
                onValueChange = viewModel::onPasswordChanged,
                leadingIcon = Icons.Default.Lock,
                error = passwordError ?: ""
            )

            DateOfBirthPicker(
                dateOfBirth = dateOfBirth,
                onDateChange = viewModel::onDateOfBirthChanged,
                dateOfBirthError = dateOfBirthError
            )
            GenderSelection(selectedGender = gender, onGenderSelected = viewModel::onGenderChanged)

            AgreeCheckbox(
                isChecked = agreeToTerms,
                onCheckedChange = viewModel::onAgreeToTermsChanged
            )

            registrationResult?.let {
                Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
            }

            ButtonLoginRegister(
                onClick = { registerClicked = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                    .height(42.dp),
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
                style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Normal),
                onClick = { navController.navigate(Screen.LoginScreen) })
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreenPage() {
//    RegisterScreenPage2()
}
