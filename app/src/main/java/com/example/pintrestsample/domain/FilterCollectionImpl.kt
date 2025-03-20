package com.example.pintrestsample.domain

import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.services.ApiResponse

class FilterCollectionImpl(private val getCollectionList: GetCollectionList) :
    FilterCollections {
    override fun filterSearchInputForCollections(): ApiResponse<List<CollectionPhotos.PreviewPhoto?>>{
        var result :ApiResponse<List<CollectionPhotos.PreviewPhoto?>> = ApiResponse.Failed("")
         getCollectionList.getList { res->
            result = res
        }
        return result
    }
}