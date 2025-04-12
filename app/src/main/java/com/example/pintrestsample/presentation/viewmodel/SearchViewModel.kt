package com.example.pintrestsample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pintrestsample.data.FilterCollections
import com.example.pintrestsample.data.model.CollectionPhotos
import com.example.pintrestsample.data.model.CreatorsItem
import com.example.pintrestsample.data.model.PopularItems
import com.example.pintrestsample.data.services.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val filterCollections: FilterCollections) :
    ViewModel() {

    private var _collectionList: MutableStateFlow<ApiResponse<List<CollectionPhotos>>> =
        MutableStateFlow(
            ApiResponse.Error("")
        )
    val connectionList: StateFlow<ApiResponse<List<CollectionPhotos>>> =
        _collectionList

    private var _creatorList: MutableStateFlow<ApiResponse<List<CreatorsItem>>> =
        MutableStateFlow(
            ApiResponse.Error("")
        )
    val creatorList: StateFlow<ApiResponse<List<CreatorsItem>>> =
        _creatorList

    private var _popularList: MutableStateFlow<ApiResponse<List<PopularItems>>> =
        MutableStateFlow(
            ApiResponse.Error("")
        )
    val popularList: StateFlow<ApiResponse<List<PopularItems>>> =
        _popularList

    fun getCollectionList() {
        viewModelScope.launch(Dispatchers.IO) {
            filterCollections.filterSearchInputForCollections {
                _collectionList.value = it
            }

            filterCollections.getCreatorsList{
                _creatorList.value = it
            }
            filterCollections.getPopularList {
                _popularList.value = it
            }
        }
    }
}