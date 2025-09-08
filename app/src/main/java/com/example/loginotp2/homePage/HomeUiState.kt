package com.example.loginotp2.homePage

data class HomeUiState(
    val userName: String = "Guest",
    val accessToken: String = "No Access Token",
    val refreshToken: String = "No Refresh Token",
    val errorMessage: String? = null,
    val isLoading: Boolean = true
)

