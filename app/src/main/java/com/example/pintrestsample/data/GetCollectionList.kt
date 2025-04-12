package com.example.pintrestsample.data

import com.example.pintrestsample.data.model.CollectionPhotos
import com.example.pintrestsample.data.model.CreatorsItem
import com.example.pintrestsample.data.model.PopularItems
import com.example.pintrestsample.data.services.ApiResponse

interface GetCollectionList {
    fun getList(apiResponse: (ApiResponse<List<CollectionPhotos>>) -> Unit)
    fun getCreatorsList(apiResponse: (ApiResponse<List<CreatorsItem>>)->Unit)
    fun getPopularList(apiResponse: (ApiResponse<List<PopularItems>>) -> Unit)
}