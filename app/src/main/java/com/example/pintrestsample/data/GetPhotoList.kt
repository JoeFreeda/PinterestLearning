package com.example.pintrestsample.data

import com.example.pintrestsample.data.model.Photos
import com.example.pintrestsample.data.services.ApiResponse

interface GetPhotoList {
    suspend fun <T> getAllPhotosInList(apiResponse:(ApiResponse<List<Photos>>)->Unit)
}