package com.khailatao.digitalclock.di

import com.khailatao.digitalclock.presentation.MainViewModel
import com.khailatao.digitalclock.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel() }
    single { HomeViewModel() }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}