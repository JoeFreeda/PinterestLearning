package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.ApiResponse

class FilterCollectionImpl(private val getCollectionList: GetCollectionList) :
    FilterCollections {
    override fun filterSearchInputForCollections(apiResponse: (ApiResponse<List<CollectionPhotos>>) -> Unit) {
        getCollectionList.getList { res ->
            when (res) {
                is ApiResponse.Error -> {
                    apiResponse(ApiResponse.Error(res.errorMessage))
                }

                is ApiResponse.Failed -> {
                    apiResponse(ApiResponse.Failed(res.failedException))
                }

                is ApiResponse.Success -> {
                    val filteredList: MutableList<CollectionPhotos> = mutableListOf()
                            filteredList.addAll(res.data)
                    apiResponse(ApiResponse.Success(filteredList.toList()))
                }
            }
        }
    }

    override fun getPopularList() {
        TODO("Not yet implemented")
    }
}