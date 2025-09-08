package com.example.loginotp2.auth.domain.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClientProvider {

    private const val SUPABASE_URL = "https://paabrwsucxluiqzlpnlt.supabase.co"
    private const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBhYWJyd3N1Y3hsdWlxemxwbmx0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTcxMjYzMjEsImV4cCI6MjA3MjcwMjMyMX0.jr3PwxK0X7c29PWgO4pNLJA3pH_F9vUDxk4vPBYgPy4"

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_ANON_KEY
        ) {
            install(Auth) {
                alwaysAutoRefresh = true
                autoLoadFromStorage = true
            }
            install(Postgrest)
        }
    }
}
