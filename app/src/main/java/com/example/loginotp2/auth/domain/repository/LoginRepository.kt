package com.example.loginotp2.auth.domain.repository

import android.content.Context
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(
    private val context: Context,
    private val supabaseClient: SupabaseClient
) {

    suspend fun loginUser(email: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                supabaseClient.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }

                val session = supabaseClient.auth.currentSessionOrNull()
                val user = supabaseClient.auth.retrieveUserForCurrentSession()

                if (user != null && session != null) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Invalid credentials. Please try again."))
                }
            } catch (e: Exception) {
                if (e.message?.contains("Email not confirmed") == true) {
                    supabaseClient.auth.resendEmail(OtpType.Email.SIGNUP, email)
                    Result.failure(Exception("Email not verified. Verification email sent."))
                } else {
                    Result.failure(e)
                }
            }
        }
    }
    suspend fun logoutUser(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                supabaseClient.auth.signOut()
                Result.success(Unit)

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
