package com.example.pintrestsample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pintrestsample.domain.FilterCollections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val filterCollections: FilterCollections) : ViewModel() {

    fun getCollectionList(){
        filterCollections.filterSearchInputForCollections()
    }

}