package com.example.pintrestsample.domain

import com.example.pintrestsample.model.Photos
import com.example.pintrestsample.services.ApiResponse

interface GetPhotoList {
    suspend fun <T> getAllPhotosInList(apiResponse:(ApiResponse<List<Photos>>)->Unit)
}