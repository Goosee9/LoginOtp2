package com.example.loginotp2.auth.domain.use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
