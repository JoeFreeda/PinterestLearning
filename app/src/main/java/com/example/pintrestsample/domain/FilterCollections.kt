package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.ApiResponse

interface FilterCollections {
    fun filterSearchInputForCollections(apiResponse: (ApiResponse<List<CollectionPhotos.PreviewPhoto>>)->Unit)
}