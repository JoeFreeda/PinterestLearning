package com.example.pintrestsample.presentation.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pintrestsample.model.CollectionPhotos
import com.example.pintrestsample.presentation.viewmodel.SearchViewModel
import com.example.pintrestsample.services.ApiResponse

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()
    viewModel.getCollectionList()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var text by remember { mutableStateOf("") }
        val collectionItems = viewModel.connectionList.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .border(1.dp, Color.Black, RectangleShape)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(), singleLine = true,
                value = text,
                onValueChange = { if (it.length < 20) text = it },
                placeholder = { Text("Search") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray, // Background when focused
                    unfocusedContainerColor = Color.White, // Background when not focused
                    disabledContainerColor = Color.Gray, // Background when disabled
                    errorContainerColor = Color.Red // Background in case of an error
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    Row(
                        Modifier.padding(end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Face",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Gray
                        )
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Gray
                        )
                    }
                }
            )

        }

        Spacer(modifier = Modifier.height(10.dp))


        CreateCollectionList(collectionItems)
    }
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
fun CreateCollectionList(collectionItems: State<ApiResponse<List<CollectionPhotos.PreviewPhoto>>>) {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        when (collectionItems.value) {
            is ApiResponse.Error -> toast(
                context,
                (collectionItems.value as ApiResponse.Error<*>).errorMessage
            )

            is ApiResponse.Failed -> toast(
                context,
                (collectionItems.value as ApiResponse.Failed<*>).failedException
            )

            is ApiResponse.Success -> {
                items((collectionItems.value as ApiResponse.Success<List<CollectionPhotos.PreviewPhoto>>).data) { imgItem ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = imgItem.urls?.regular, // Replace with your image URL
                            contentDescription = "Sample Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Transparent, CircleShape)
                        )
                            imgItem.name?.let {
                                val truncatedText = if (it.length > 10) it.take(7) + "..." else it
                                Text(text = truncatedText, maxLines = 1, overflow = TextOverflow.Ellipsis) }
                    }
                }
            }
        }
    }
}