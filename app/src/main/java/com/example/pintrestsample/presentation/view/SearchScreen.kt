package com.example.pintrestsample.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pintrestsample.R
import com.example.pintrestsample.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen() {
    val viewModel : SearchViewModel= hiltViewModel()
    Column {
        var text by remember { mutableStateOf("") }
        val collectionItems = remember { mutableListOf<String>() }
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
            items(collectionItems) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your image
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp) // Adjust size as needed
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}