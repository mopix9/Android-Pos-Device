package com.fanap.sina.di

import android.content.Context
import android.content.SharedPreferences
import com.fanap.sina.main.fragments.LoadingFragment
import com.fanap.sina.main.fragments.SetupFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoadingFragment(): LoadingFragment = LoadingFragment()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences = app.getSharedPreferences("DATA", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext app: Context) = app

    @Singleton
    @Provides
    fun provideSetupFragment(): SetupFragment = SetupFragment()

}