package com.example.loginotp2.auth.presentation.otpVerification


data class OtpState(
    val code: List<Int?> = (1..6).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean? = null
)