package com.example.myfinancesapp.ui.forgotPassword

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgotPasswordVM: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _isForgotPasswordEnabled = MutableLiveData<Boolean>()
    val isForgotPasswordEnabled: LiveData<Boolean> = _isForgotPasswordEnabled

    fun onForgotPasswordChanged(email: String) {
        _email.value = email
        _isForgotPasswordEnabled.value = onForgotPasswordEnabled(email)
    }

    private fun onForgotPasswordEnabled(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onForgotPasswordButtonClicked() {

    }
}