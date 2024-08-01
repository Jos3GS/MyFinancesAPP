package com.example.myfinancesapp.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterVM : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword : LiveData<String> = _repeatPassword

    private val _firstName = MutableLiveData<String>()
    val firstname : LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName : LiveData<String> = _lastName

    private val _isRegisterEnabled = MutableLiveData<Boolean>()
    val isRegisterEnabled : LiveData<Boolean> = _isRegisterEnabled

    private val _isPasswordVisible = MutableLiveData<Boolean>()
    val isPasswordVisible : LiveData<Boolean> = _isPasswordVisible

    private val _isRepeatPasswordVisible = MutableLiveData<Boolean>()
    val isRepeatPasswordVisible : LiveData<Boolean> = _isRepeatPasswordVisible

    private val _isSamePassword = MutableLiveData<Boolean>()
    val isSamePassword : LiveData<Boolean> = _isSamePassword

    fun onRegisterChanged(email: String, password: String, repeatPassword: String, firstName: String, lastName: String) {
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
        _firstName.value = firstName
        _lastName.value = lastName

        isSamePassword(password, repeatPassword)
        isRegisterEnabled(email, password, repeatPassword, firstName, lastName)

    }

    private fun isRegisterEnabled(email: String, password: String, repeatPassword: String, firstName: String, lastName: String) {
        if(_isSamePassword.value == true){
            _isRegisterEnabled.value = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 7 && firstName.length > 3 && lastName.length > 3
        }else _isRegisterEnabled.value = false
    }

    private fun isSamePassword(password: String, repeatPassword: String) {
        _isSamePassword.value = password == repeatPassword
    }

    fun onPasswordVisibilityChanged(enabled: Boolean) {
        _isPasswordVisible.value = enabled
    }

    fun onRepeatPasswordVisibilityChanged(enabled: Boolean) {
        _isRepeatPasswordVisible.value = enabled
    }

    fun onRegisterButtonClicked() {

    }

}