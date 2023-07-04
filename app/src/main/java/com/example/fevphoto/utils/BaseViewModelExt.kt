package com.example.fevphoto.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fevphoto.ui.state.State
import com.example.fevphoto.ui.viewmodel.BaseViewModel

@Composable
fun <S : State, VM : BaseViewModel<S>> VM.collectState() = state.collectAsStateWithLifecycle()