package com.example.pintrestsample.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pintrestsample.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen() {
    val viewModel : SearchViewModel= hiltViewModel()
    viewModel.getCollectionList()
    Column {
        var text by remember { mutableStateOf("") }
        val collectionItems = viewModel.connectionList.collectAsState()
        Box {
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Search") },
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }

        LazyRow {
            items(collectionItems.value) { imgItem->
                AsyncImage(
                    model = imgItem?.urls?.regular, // Replace with your image URL
                    contentDescription = "Sample Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}