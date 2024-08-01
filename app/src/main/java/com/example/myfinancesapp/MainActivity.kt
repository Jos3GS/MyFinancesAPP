package com.example.myfinancesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfinancesapp.ui.login.LoginView
import com.example.myfinancesapp.ui.login.LoginVM
import com.example.myfinancesapp.ui.theme.MyFinancesAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinancesAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    LoginView(LoginVM())
                }
            }
        }
    }
}

