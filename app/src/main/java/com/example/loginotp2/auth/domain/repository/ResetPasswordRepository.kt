package com.example.loginotp2.auth.domain.repository

import com.example.loginotp2.Logger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResetPasswordRepository(
    private val supabaseClient: SupabaseClient
) {

    suspend fun isEmailInProfilesTable(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val result  = supabaseClient
                    .from("profiles")
                    .select(columns = Columns.list("email")){
                        filter {
                            eq("provider", "email")
                            eq("email", email)
                        }
                    }
                    .decodeList<Map<String, String>>()
                result.isNotEmpty()
            } catch (e: Exception) {
                Logger.e("SignUpRepository", "Error checking email in profiles: ${e.message}")
                false
            }
        }
    }

    suspend fun sendOTP(email: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                supabaseClient.auth.resetPasswordForEmail(email = email)
                return@withContext Result.success(Unit)

            } catch (e: Exception) {
                Logger.e("ResetPasswordRepository", "Error sending OTP: ${e.message}")
                return@withContext Result.failure(e)
            }
        }
    }
}