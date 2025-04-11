package com.example.pintrestsample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pintrestsample.data.GetPhotoList
import com.example.pintrestsample.data.model.Photos
import com.example.pintrestsample.data.services.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPhotoList: GetPhotoList) : ViewModel() {

    private var _picsBitmap = MutableStateFlow<ApiResponse<List<Photos>>>(ApiResponse.Failed(""))
    val picsBitmap: StateFlow<ApiResponse<List<Photos>>> = _picsBitmap


    fun getImgList() {
        viewModelScope.launch(Default) {
            getPhotoList.getAllPhotosInList<ApiResponse<List<Photos>>> { result ->
                    _picsBitmap.value = result
            }
        }
    }

}

