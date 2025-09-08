package com.example.loginotp2.auth.domain.repository

import com.example.loginotp2.Logger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetPasswordRepository(
    private val supabaseClient: SupabaseClient
) {
    suspend fun updatePassword(newPassword: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                supabaseClient.auth.updateUser {
                    password = newPassword
                }
                return@withContext Result.success(Unit)
            } catch (e: Exception) {
                Logger.e("SetPasswordRepository", "Error updating password: ${e.message}")
                return@withContext Result.failure(e)
            }
        }
    }
}