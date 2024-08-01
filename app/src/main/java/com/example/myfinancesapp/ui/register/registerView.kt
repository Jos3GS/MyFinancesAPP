package com.example.myfinancesapp.ui.register

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfinancesapp.model.Routes
import com.example.myfinancesapp.ui.login.PasswordInvisibleIcon
import com.example.myfinancesapp.ui.login.PasswordVisibleIcon
import com.example.myfinancesapp.ui.theme.MyFinancesAPPTheme

@Composable
fun RegisterView(registerViewModel: RegisterVM, navigationController: NavHostController) {
    val email by registerViewModel.email.observeAsState(initial = "")
    val password by registerViewModel.password.observeAsState(initial = "")
    val repeatPassword by registerViewModel.repeatPassword.observeAsState(initial = "")
    val firstName by registerViewModel.firstname.observeAsState(initial = "")
    val lastName by registerViewModel.lastName.observeAsState(initial = "")
    val isRegisterEnabled by registerViewModel.isRegisterEnabled.observeAsState(initial = false)
    val isPasswordVisible by registerViewModel.isPasswordVisible.observeAsState(initial = true)
    val isRepeatPasswordVisible by registerViewModel.isRepeatPasswordVisible.observeAsState(initial = true)
    val isSamePassword by registerViewModel.isSamePassword.observeAsState(initial = true)

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), verticalArrangement = Arrangement.Center) {
        FirstNameTextField(text = firstName, onTextChange = {
            registerViewModel.onRegisterChanged(
                email = email,
                password = password,
                repeatPassword = repeatPassword,
                firstName = it,
                lastName = lastName
            )
        })
        LastNameTextField(text = lastName, onTextChange = {registerViewModel.onRegisterChanged(
            email = email,
            password = password,
            repeatPassword = repeatPassword,
            firstName = firstName,
            lastName = it
        )})
        EmailTextField(
            text = email,
            onTextChange = {
                registerViewModel.onRegisterChanged(
                    email = it,
                    password = password,
                    repeatPassword = repeatPassword,
                    firstName = firstName,
                    lastName = lastName
                )
            })
        PasswordTextField(
            text = password,
            textVisible = isPasswordVisible,
            onTextChange = {
                registerViewModel.onRegisterChanged(
                    email = email,
                    password = it,
                    repeatPassword = repeatPassword,
                    firstName = firstName,
                    lastName = lastName
                )
            },
            onPasswordVisibilityChange = { registerViewModel.onPasswordVisibilityChanged(enabled = it) }
        )
        RepeatPasswordTextField(
            text = repeatPassword,
            textVisible = isRepeatPasswordVisible,
            isSamePassword = isSamePassword,
            onTextChange = {
                registerViewModel.onRegisterChanged(
                    email = email,
                    password = password,
                    repeatPassword = it,
                    firstName = firstName,
                    lastName = lastName
                )
            },
            onPasswordVisibilityChange = {
                registerViewModel.onRepeatPasswordVisibilityChanged(
                    enabled = it
                )
            }
        )
        RegisterButton(registerEnabled = isRegisterEnabled, onRegisterButtonClicked = { registerViewModel.onRegisterButtonClicked() })
        LoginButton(navigationController)
    }
}

@Composable
fun FirstNameTextField(text: String, onTextChange: (String) -> Unit) {
    RegisterTextField(
        text = text,
        label = "Nombre",
        onTextChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun LastNameTextField(text: String, onTextChange: (String) -> Unit){
    RegisterTextField(
        text = text,
        label = "Apellido",
        onTextChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun EmailTextField(text: String, onTextChange: (String) -> Unit) {
    RegisterTextField(
        text = text,
        label = "Correo electrónico",
        onTextChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordTextField(
    text: String,
    textVisible: Boolean,
    onTextChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit
) {
    RegisterTextField(
        text = text,
        label = "Contraseña",
        isPassword = true,
        textVisible = textVisible,
        onTextChange = onTextChange,
        onPasswordVisibilityChange = onPasswordVisibilityChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun RepeatPasswordTextField(
    text: String,
    textVisible: Boolean,
    isSamePassword: Boolean,
    onTextChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit
) {
    RegisterTextField(
        text = text,
        label = "Repetir contraseña",
        isPassword = true,
        textVisible = textVisible,
        onTextChange = onTextChange,
        isSamePassword = isSamePassword,
        onPasswordVisibilityChange = onPasswordVisibilityChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun RegisterTextField(
    text: String,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth().padding(8.dp),
    textVisible: Boolean = false,
    isPassword: Boolean = false,
    isSamePassword: Boolean = true,
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
        supportingText = {
            if(isPassword) {
                if (!isSamePassword) {
                    Text(text = "Las contraseñas no coinciden")
                }
            }
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
            focusedSupportingTextColor = Color.Red,
            unfocusedSupportingTextColor = Color.Red
        )
    )
}

@Composable
private fun RegisterButton(registerEnabled: Boolean, onRegisterButtonClicked: () -> Unit){
    Button(onClick = { onRegisterButtonClicked() }, enabled = registerEnabled, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = "Registrarse")
    }
}

@Composable
private fun LoginButton(navigationController: NavHostController) {
    OutlinedButton(onClick = { navigationController.popBackStack() }, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = "Ya tengo una cuenta")
    }
}


