package com.example.myfinancesapp.ui.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfinancesapp.ui.login.PasswordInvisibleIcon
import com.example.myfinancesapp.ui.login.PasswordVisibleIcon

@Composable
fun ForgotPasswordView(forgotPasswordViewModel: ForgotPasswordVM, navigationController: NavHostController) {
    val email by forgotPasswordViewModel.email.observeAsState(initial = "")
    val isForgotPasswordEnabled by forgotPasswordViewModel.isForgotPasswordEnabled.observeAsState(
        initial = false
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ) {
        EmailTextField(text = email) { forgotPasswordViewModel.onForgotPasswordChanged(it) }
        ForgotPasswordButton(
            forgotPasswordEnabled = isForgotPasswordEnabled,
            onForgotPasswordButtonClicked = { forgotPasswordViewModel.onForgotPasswordButtonClicked() })
        LoginButton(navigationController = navigationController)
    }
}

@Composable
private fun EmailTextField(text: String, onTextChange: (String) -> Unit) {
    ForgotPasswordTextField(
        text = text,
        label = "Correo electrónico",
        onTextChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
private fun ForgotPasswordButton(
    forgotPasswordEnabled: Boolean,
    onForgotPasswordButtonClicked: () -> Unit
) {
    Button(
        onClick = { onForgotPasswordButtonClicked() }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), enabled = forgotPasswordEnabled
    ) {
        Text(text = "Enviar Correo de recuperación")
    }
}

@Composable
private fun LoginButton(navigationController: NavHostController) {
    OutlinedButton(
        onClick = { navigationController.popBackStack() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Volver")
    }
}

@Composable
private fun ForgotPasswordTextField(
    text: String,
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    textVisible: Boolean = false,
    isPassword: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {},
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        singleLine = true,
        maxLines = 1,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        visualTransformation = if (!textVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            if (isPassword) {
                if (!textVisible) {
                    PasswordVisibleIcon(textVisible = false, onPasswordVisibilityChange)
                } else {
                    PasswordInvisibleIcon(textVisible = true, onPasswordVisibilityChange)
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}
