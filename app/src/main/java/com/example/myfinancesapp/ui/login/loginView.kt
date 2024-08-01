package com.example.myfinancesapp.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfinancesapp.R
import com.example.myfinancesapp.ui.theme.MyFinancesAPPTheme


@Composable
fun LoginView(loginViewModel: LoginVM) {
    val email by loginViewModel.email.observeAsState(initial = "")
    val password by loginViewModel.password.observeAsState(initial = "")
    val isPasswordVisible by loginViewModel.isPasswordVisible.observeAsState(initial = false)
    val isLoginEnabled by loginViewModel.isLoginEnabled.observeAsState(initial = false)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            EmailTextField(
                text = email
            ) { loginViewModel.onLoginChanged(email = it, password = password) }
            PasswordTextField(
                text = password,
                textVisible = isPasswordVisible,
                { loginViewModel.onLoginChanged(email = email, password = it) },
                { loginViewModel.onPasswordVisibilityChanged(enabled = it) })
            LoginButton(
                loginEnabled = isLoginEnabled,
                onLoginButtonClicked = { loginViewModel.onLoginButtonClicked() })
            RegisterButton(onRegisterButtonClicked = {})


        }
        ForgotPasswordButton(modifier = Modifier.align(Alignment.BottomCenter), onForgotPasswordButtonClicked = {})
    }
}

@Composable
fun EmailTextField(
    text: String,
    onTextChange: (String) -> Unit
) {
    LoginTextField(
        text = text,
        label = "Correo electrónico",
        onTextChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun PasswordTextField(
    text: String,
    textVisible: Boolean,
    onTextChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit
) {
    LoginTextField(
        text = text,
        label = "Contraseña",
        onTextChange = onTextChange,
        onPasswordVisibilityChange = onPasswordVisibilityChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textVisible = textVisible,
        isPassword = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        )

}

@Composable
fun LoginTextField(
    text: String,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
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

@Composable
fun PasswordVisibleIcon(textVisible: Boolean, onPasswordVisibilityChange: (Boolean) -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.visibility),
        contentDescription = "password visible icon",
        modifier = Modifier.clickable { onPasswordVisibilityChange(!textVisible) })
}

@Composable
fun PasswordInvisibleIcon(textVisible: Boolean, onPasswordVisibilityChange: (Boolean) -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.visibility_off),
        contentDescription = "password visible icon",
        modifier = Modifier.clickable { onPasswordVisibilityChange(!textVisible) })
}

@Composable
fun LoginButton(loginEnabled: Boolean, onLoginButtonClicked: () -> Unit) {
    Button(
        onClick = { onLoginButtonClicked() }, enabled = loginEnabled, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Iniciar Sesión")

    }
}

@Composable
fun RegisterButton(onRegisterButtonClicked: () -> Unit) {
    OutlinedButton(onClick = {}, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Text(text = "Registrarse")
    }

}

@Composable
fun ForgotPasswordButton(modifier:Modifier, onForgotPasswordButtonClicked: () -> Unit) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "¿Has olvidado tu contraseña?", modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.secondary)
        Text(text = "Haz clic aquí", modifier = Modifier
            .padding(8.dp)
            .clickable { onForgotPasswordButtonClicked() },
            color = MaterialTheme.colorScheme.primary)
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyFinancesAPPTheme {
        LoginView(LoginVM())
    }
}