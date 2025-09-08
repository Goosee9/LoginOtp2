package com.example.loginotp2.auth.presentation.login

import android.app.Activity

sealed class LoginFormEvent {
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    object Submit : LoginFormEvent()
}