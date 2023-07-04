package com.example.fevphoto.di

import com.example.fevphoto.data.local.AddImageDao
import com.example.fevphoto.data.repository.PhotoRepositoryImpl
import com.example.fevphoto.domain.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePhotoRepository(photoDao: AddImageDao) : PhotoRepository {
        return PhotoRepositoryImpl(photoDao)
    }
}