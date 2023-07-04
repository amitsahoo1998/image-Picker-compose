package com.example.fevphoto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.fevphoto.data.local.ImageEntity
import com.example.fevphoto.ui.theme.FevPhotoTheme
import com.example.fevphoto.ui.viewmodel.PhotoViewModel
import com.example.fevphoto.utils.collectState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel : PhotoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        setContent {
            FevPhotoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.collectState()
                    MainContent(
                        photos = state.photos,
                        addBtnClick = { imagePicker.launch("image/*") })
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            it?.let {
                if (it.isNotEmpty()) run {
                    viewModel.addPhoto(it)
                }
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(photos: List<ImageEntity>, addBtnClick: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {},
        floatingActionButton = {
            FloatingActionButton(
                onClick = addBtnClick,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Add",
                    tint = Color.White
                )
            }
        }
    ) { paddingValue ->
        Column(modifier = Modifier.padding(paddingValue)) {
            LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.padding(5.dp)) {
                items(count = photos.size) {
                    AsyncImage(model = photos[it].imageUri, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FevPhotoTheme {
        //MainContent("Android")
    }
}

