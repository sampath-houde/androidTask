package com.sampath.androidTask.di

import com.sampath.androidTask.data.remote.api.DogApiService
import com.sampath.androidTask.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    @Provides
    fun providesRetrofit(
        baseUrl: String
    )  : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .also { client ->
                        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }.build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    fun providesDogApiService(retrofit: Retrofit): DogApiService = retrofit.create(DogApiService::class.java)

}