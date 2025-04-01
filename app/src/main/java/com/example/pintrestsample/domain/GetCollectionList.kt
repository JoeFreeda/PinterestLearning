package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.ApiResponse

interface GetCollectionList {
    fun getList(apiResponse: (ApiResponse<List<CollectionPhotos>>) -> Unit)
}