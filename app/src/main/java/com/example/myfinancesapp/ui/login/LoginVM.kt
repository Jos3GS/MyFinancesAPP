package com.example.myfinancesapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginVM: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isPasswordVisible = MutableLiveData<Boolean>()
    val isPasswordVisible: LiveData<Boolean> = _isPasswordVisible

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        onLoginEnabled(email, password)

    }

    private fun onLoginEnabled(email: String, password: String) {
        _isLoginEnabled.value = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 7
    }

    fun onPasswordVisibilityChanged(enabled: Boolean) {
        _isPasswordVisible.value = enabled
    }

    fun onLoginButtonClicked(){
        //TODO: login
    }

}