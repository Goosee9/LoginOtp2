package com.example.loginotp2.auth.presentation.forgetPassword.reset

data class ForgetPasswordFormState(
    val email: String = "",
    val emailError: String? = null,

    val isLoading: Boolean = false
)