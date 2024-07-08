package com.sampath.androidTask.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sampath.androidTask.api.DogApiService
import com.sampath.androidTask.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {
    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    fun providesContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesDogRepo(api: DogApiService) {

    }
    fun providesGsonConvertor() = GsonConverterFactory.create()

    @Provides
    fun providesDogApi(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        gsonConverterFactory: GsonConverterFactory,
        api: DogApiService
        ) : DogApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .also { client ->
                        client.addInterceptor(httpLoggingInterceptor)
                    }.build()
            )
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(api::class.java)
    }


}