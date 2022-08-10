package com.hackbyte.coinview.features.authentication.di

import com.hackbyte.coinview.features.authentication.LoginViewModel
import com.hackbyte.coinview.features.authentication.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val authenticationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
}