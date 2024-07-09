package com.sampath.androidTask.di

import android.content.Context
import com.bumptech.glide.Glide
import com.sampath.androidTask.data.repo.DogBreedImageRepo
import com.sampath.androidTask.data.repo.DogBreedRepo
import com.sampath.androidTask.domain.repository.DogBreedImageRepoDomain
import com.sampath.androidTask.domain.repository.DogBreedRepoDomain
import dagger.Binds
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

}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun bindDogBreedRepository(repo: DogBreedRepo): DogBreedRepoDomain

    @Binds
    abstract fun bindCharacterDetailsRepository(repo: DogBreedImageRepo): DogBreedImageRepoDomain
}