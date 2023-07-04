package com.example.fevphoto.di

import android.app.Application
import com.example.fevphoto.data.local.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = PhotoDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideImageDao(database: PhotoDatabase) = database.addImageDao()
}