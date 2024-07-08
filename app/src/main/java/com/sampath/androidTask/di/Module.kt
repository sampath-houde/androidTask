package com.sampath.androidTask.di

import android.content.Context
import com.bumptech.glide.Glide
import com.sampath.androidTask.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesContext(@ApplicationContext context: Context): Context = context


    @Provides
    fun providesGlide(applicationContext: Context) = Glide.with(applicationContext)


    @Provides
    fun providesNetworkUtils(context: Context) = NetworkUtils(context)

}