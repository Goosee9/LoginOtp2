package com.example.loginotp2.auth.presentation.forgetPassword.reset

sealed class ForgetPasswordFormEvent {
    data class EmailChanged(val email: String) : ForgetPasswordFormEvent()
    object Submit : ForgetPasswordFormEvent()
}