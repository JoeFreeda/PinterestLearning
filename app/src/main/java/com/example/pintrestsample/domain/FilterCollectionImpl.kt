package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.ApiResponse

class FilterCollectionImpl(private val getCollectionList: GetCollectionList) :
    FilterCollections {
    override fun filterSearchInputForCollections(apiResponse: (ApiResponse<List<CollectionPhotos.PreviewPhoto>>) -> Unit) {
        getCollectionList.getList { res ->
            when (res) {
                is ApiResponse.Error -> {
                    apiResponse(ApiResponse.Error(res.errorMessage))
                }

                is ApiResponse.Failed -> {
                    apiResponse(ApiResponse.Failed(res.failedException))
                }

                is ApiResponse.Success -> {
                    val filteredList: MutableList<CollectionPhotos.PreviewPhoto?> = mutableListOf()
                    res.data.forEach { collectionPhotos ->
                        collectionPhotos.preview_photos?.let { previewPhotos ->
                            previewPhotos.forEach {photos->
                                photos?.name = collectionPhotos.title
                                filteredList.add(photos)
                            }
                        }
                    }
                    filteredList.filterNotNull().toList().let {
                        apiResponse(ApiResponse.Success(it))
                    }
                }
            }
        }
    }
}