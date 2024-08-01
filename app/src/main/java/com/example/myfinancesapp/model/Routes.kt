package com.example.myfinancesapp.model

sealed class Routes (val route: String){
    object Login: Routes("LoginView")
    object Register: Routes("RegisterView")

}