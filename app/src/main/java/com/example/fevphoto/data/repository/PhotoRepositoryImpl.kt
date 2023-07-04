package com.example.fevphoto.data.repository

import com.example.fevphoto.data.local.AddImageDao
import com.example.fevphoto.data.local.ImageEntity
import com.example.fevphoto.domain.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val dao: AddImageDao
) : PhotoRepository {
    override fun getAllPhoto(): Flow<List<ImageEntity>> = dao.fetchImages()

    override suspend fun insertPhotos(photos: List<ImageEntity>) = dao.addImage(photos)
}