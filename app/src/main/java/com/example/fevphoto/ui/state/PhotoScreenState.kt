package com.example.fevphoto.ui.state

import com.example.fevphoto.data.local.ImageEntity

data class PhotoScreenState(
    val photos : List<ImageEntity> = emptyList()
): State
