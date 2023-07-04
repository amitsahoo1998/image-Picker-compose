package com.example.fevphoto.domain

import com.example.fevphoto.data.local.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PhotoRepository {

    fun getAllPhoto(): Flow<List<ImageEntity>>

    suspend fun insertPhotos(photos: List<ImageEntity>)
}