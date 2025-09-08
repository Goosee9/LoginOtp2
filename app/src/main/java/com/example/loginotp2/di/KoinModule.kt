package com.example.loginotp2.di

import com.example.loginotp2.auth.domain.repository.LoginRepository
import com.example.loginotp2.auth.domain.repository.ResetPasswordRepository
import com.example.loginotp2.auth.domain.repository.SetPasswordRepository
import com.example.loginotp2.auth.domain.repository.SignUpRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.example.loginotp2.auth.domain.supabase.SupabaseClientProvider
import com.example.loginotp2.auth.domain.use_case.ValidateEmail
import com.example.loginotp2.auth.domain.use_case.ValidateName
import com.example.loginotp2.auth.domain.use_case.ValidatePassword
import com.example.loginotp2.auth.domain.use_case.ValidateTerms
import com.example.loginotp2.auth.presentation.forgetPassword.reset.ResetPasswordViewModel
import com.example.loginotp2.auth.presentation.forgetPassword.setNew.SetPasswordViewModel
import com.example.loginotp2.auth.presentation.login.LoginViewModel
import com.example.loginotp2.auth.presentation.main.MainViewModel
import com.example.loginotp2.auth.presentation.otpVerification.OtpViewModel
import com.example.loginotp2.auth.presentation.signUp.SignUpViewModel
import com.example.loginotp2.homePage.HomeViewModel


val appModule = module {

    single { SupabaseClientProvider.client }

    single { SignUpRepository(get()) }

    single { ResetPasswordRepository(get()) }

    single { SetPasswordRepository(get()) }

    single { LoginRepository(androidContext(), get()) }

    factoryOf(::ValidateEmail)
    factoryOf(::ValidatePassword)
    factoryOf(::ValidateTerms)
    factoryOf(::ValidateName)
}

val viewModelModule = module {
    viewModelOf(::SignUpViewModel)
    viewModelOf(::OtpViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::ResetPasswordViewModel)
    viewModelOf(::SetPasswordViewModel)
}