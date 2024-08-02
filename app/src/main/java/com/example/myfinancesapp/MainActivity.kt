package com.example.myfinancesapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfinancesapp.model.Routes
import com.example.myfinancesapp.ui.forgotPassword.ForgotPasswordVM
import com.example.myfinancesapp.ui.forgotPassword.ForgotPasswordView
import com.example.myfinancesapp.ui.home.HomeVM
import com.example.myfinancesapp.ui.home.HomeView
import com.example.myfinancesapp.ui.login.LoginVM
import com.example.myfinancesapp.ui.login.LoginView
import com.example.myfinancesapp.ui.register.RegisterVM
import com.example.myfinancesapp.ui.register.RegisterView
import com.example.myfinancesapp.ui.theme.MyFinancesAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinancesAPPTheme {
                val navigationController = rememberNavController()
                var backPressedTime by remember { mutableLongStateOf(0L) }
                NavHost(navController = navigationController, startDestination = Routes.Login.route){
                    composable(route = Routes.Login.route) {LoginView(loginViewModel = LoginVM(), navigationController = navigationController)}
                    composable(route = Routes.Register.route) {RegisterView(registerViewModel = RegisterVM(), navigationController = navigationController)}
                    composable(route = Routes.ForgotPassword.route) {ForgotPasswordView(forgotPasswordViewModel = ForgotPasswordVM(), navigationController = navigationController)}
                    composable(route = Routes.Home.route) {HomeView(homeViewModel = HomeVM())}
                }

                BackHandler (enabled = true) {
                    if(navigationController.currentBackStackEntry?.destination?.route == Routes.Login.route) {
                        if(backPressedTime + 2000 > System.currentTimeMillis()) {

                            finish()
                        }else{
                            backPressedTime = System.currentTimeMillis()
                            Toast.makeText(this, "Presiona de nuevo para salir", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        navigationController.popBackStack()
                    }
                }
            }
        }
    }
}

