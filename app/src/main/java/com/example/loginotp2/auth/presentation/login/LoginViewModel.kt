package com.example.loginotp2.auth.presentation.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginotp2.auth.domain.repository.LoginRepository
import com.example.loginotp2.auth.domain.repository.ResetPasswordRepository
import com.example.loginotp2.auth.domain.use_case.ValidateEmail
import com.example.loginotp2.auth.domain.use_case.ValidatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Suppress("StaticFieldLeak")
class LoginViewModel(
    private val context: Context,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val loginRepository: LoginRepository,
    private val resetPasswordRepository: ResetPasswordRepository
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        state = state.copy(isLoading = true)

        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(emailResult, passwordResult).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                isLoading = false
            )
            return
        }

        viewModelScope.launch {
            val loginResult = loginRepository.loginUser(state.email, state.password)
            loginResult.fold(
                onSuccess = {
                    state = state.copy(isLoading = false)
                    validationEventChannel.send(ValidationEvent.Success(state.email))
                },
                onFailure = { error ->
                    val errorMessage = when {
                        error.message?.contains("Invalid login credentials") == true ->
                            "Invalid email or password"
                        error.message?.contains("Email not confirmed") == true ->
                            "Please confirm your email first"
                        else -> error.message ?: "Login failed"
                    }

                    state = state.copy(
                        isLoading = false,
                        passwordError = errorMessage,
                        emailError = null
                    )
                }
            )
        }
    }
    sealed class ValidationEvent {
        data class Success(val email: String) : ValidationEvent()
        data class EmailNotVerified(val email: String) : ValidationEvent()
        data class Failure(val error: String) : ValidationEvent()
    }
}


