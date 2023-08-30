package com.example.linkofficial.presentation.screens.register_screen_2.components

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.filament.Box
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateOfBirthPicker(
    dateOfBirth: LocalDate, onDateChange: (LocalDate) -> Unit, dateOfBirthError: String?
) {
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    val dateStr = dateOfBirth.format(formatter)

    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog) {
        val datePickerDialog = DatePickerDialog(
            context, { _, year, month, dayOfMonth ->
                onDateChange(LocalDate.of(year, month + 1, dayOfMonth))
                showDialog = false
            }, dateOfBirth.year, dateOfBirth.monthValue - 1, dateOfBirth.dayOfMonth
        )
        datePickerDialog.show()
        showDialog = false
    }

//    OutlinedTextField(
//        value = dateStr,
//        onValueChange = { },
//        label = {
//            Text(
//                text = "Date of Birth",
//                modifier = Modifier
//                    .background(Color.Transparent))},
//        colors = TextFieldDefaults.colors(
//            unfocusedTextColor = Color.Black,
//            focusedTextColor = Color.Black,
//            disabledTextColor = Color.Black,
//            focusedContainerColor = Color.White,
//            unfocusedContainerColor = Color.White,
//            disabledContainerColor = Color.White,
//            focusedLabelColor = Color.Black,
//            unfocusedLabelColor = Color.Black,
//            disabledLabelColor = Color.Black,
//            unfocusedTrailingIconColor = Color.Black,
//            focusedTrailingIconColor = Color.DarkGray,
//            disabledTrailingIconColor = Color.DarkGray,
//            focusedPlaceholderColor = Color.DarkGray,
//            unfocusedPlaceholderColor = Color.Black,
//            disabledPlaceholderColor = Color.Black,
//
//        ),
//        trailingIcon = {
//            Icon(Icons.Default.CalendarMonth,
//                contentDescription = "Date Picker Icon",
//                modifier = Modifier.clickable { showDialog = true })
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 8.dp, bottom = 8.dp),
//        enabled = false,
//        supportingText = {
//            if (dateOfBirthError != null) {
//                Text(
//                    text = dateOfBirthError,
//                    style = TextStyle(
//                        color = Color.Red,
//                        fontSize = 12.sp
//                    ),
//
//                    )
//            }
//        }
//
//    )
    Column {
        Text(
            text = "Date of Birth",
            color = Color.Black,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray)
                .fillMaxWidth()
        ) {
            TextField(
                value = dateStr,
                onValueChange = { /* handle value change */ },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(Icons.Default.CalendarMonth,
                        contentDescription = "Date Picker Icon",
                        modifier = Modifier.clickable { showDialog = true })

                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                )
            )
        }
    }

}

