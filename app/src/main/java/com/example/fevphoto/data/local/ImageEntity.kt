package com.example.fevphoto.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val imageId : Int = 0,
    val imageUri : String
)
