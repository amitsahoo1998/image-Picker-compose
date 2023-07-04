package com.example.fevphoto.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fevphoto.data.local.ImageEntity
import com.example.fevphoto.domain.PhotoRepository
import com.example.fevphoto.ui.state.PhotoScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : BaseViewModel<PhotoScreenState>(initialState = PhotoScreenState()) {

    init {
        getAllPhoto()
    }

    private fun getAllPhoto() {
        photoRepository.getAllPhoto()
            .distinctUntilChanged()
            .onEach {
                setState { state->
                    state.copy(
                        photos = it
                    )
                }
            }
    }

    fun addPhoto(uri : List<Uri>) {
        viewModelScope.launch {
            val imageEntity: ArrayList<ImageEntity> = ArrayList()
            for (i in uri) {
                imageEntity.add(ImageEntity(imageUri = i.toString()))
            }
            photoRepository.insertPhotos(imageEntity.toList())
        }
    }


}