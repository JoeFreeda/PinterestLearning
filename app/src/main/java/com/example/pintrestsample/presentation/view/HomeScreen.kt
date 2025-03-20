package com.example.pintrestsample.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pintrestsample.model.Photos
import com.example.pintrestsample.presentation.viewmodel.HomeViewModel
import com.example.pintrestsample.services.ApiResponse
import kotlin.random.Random

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    viewModel.getImgList()
    val picsListResponse by remember { viewModel.picsBitmap }.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getImgList()
    }

    when (picsListResponse) {
        is ApiResponse.Error -> {
            Toast.makeText(
                context,
                (picsListResponse as ApiResponse.Error<List<Photos>>).errorMessage,
                Toast.LENGTH_LONG
            ).show()
        }

        is ApiResponse.Failed -> {
            Toast.makeText(
                context,
                (picsListResponse as ApiResponse.Failed<List<Photos>>).failedException,
                Toast.LENGTH_LONG
            ).show()
        }

        is ApiResponse.Success -> {
            val photoHeights = remember {
                mutableStateMapOf<String, Dp>().apply {
                    (picsListResponse as ApiResponse.Success<List<Photos>>).data.forEach { photo ->
                        put(
                            photo.id,
                            Random.nextInt(100, 250).dp
                        )
                    }
                }
            }
            Column {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        (picsListResponse as ApiResponse.Success<List<Photos>>).data,
                        key = { it.id }) { pics ->
                        AsyncImage(
                            model = pics.urls?.regular,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(
                                    photoHeights.getOrDefault(pics.id, 150.dp)
                                )
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
        }
    }
}


