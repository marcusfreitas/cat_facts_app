package com.example.catfactsapp.injection.module

import com.example.catfactsapp.ui.home.injection.HomeModule
import com.example.catfactsapp.ui.home.view.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity
}