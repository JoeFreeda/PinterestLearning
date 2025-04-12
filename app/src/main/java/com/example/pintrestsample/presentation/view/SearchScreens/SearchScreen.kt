package com.example.pintrestsample.presentation.view.SearchScreens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pintrestsample.data.model.CollectionPhotos
import com.example.pintrestsample.presentation.viewmodel.SearchViewModel
import com.example.pintrestsample.data.services.ApiResponse

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getCollectionList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        var text by remember { mutableStateOf("") }
        val collectionItems = viewModel.connectionList.collectAsState()
        val creatorsItem = viewModel.creatorList.collectAsState()
        val popularItems = viewModel.popularList.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), singleLine = true,
                value = text,
                onValueChange = { if (it.length < 20) text = it },
                placeholder = {
                    Text(
                        "Search",
                        style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal)
                    )
                },

                shape = RoundedCornerShape(14.dp),
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),


                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(18.dp),
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
                            modifier = Modifier.size(18.dp),
                            tint = Color.Gray
                        )
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Gray
                        )
                    }
                }
            )

        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Trending collections",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left
            )
        )

        Spacer(modifier = Modifier.height(12.dp))
        CreateCollectionList(collectionItems, context)
        Spacer(modifier = Modifier.height(12.dp))
        CreatorItemList(creatorsItem.value, context)
        Spacer(modifier = Modifier.height(12.dp))
        PopularItemList(popularItems.value, context)
    }
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
fun CreateCollectionList(
    collectionItems: State<ApiResponse<List<CollectionPhotos>>>,
    context: Context
) {

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
                items((collectionItems.value as ApiResponse.Success<List<CollectionPhotos>>).data) { imgItem ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = imgItem.cover_photo?.urls?.thumb, // Replace with your image URL
                            contentDescription = "Sample Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Transparent, CircleShape)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        imgItem.title?.let {
                            val truncatedText = if (it.length > 13) it.take(10) + "..." else it
                            Text(
                                text = truncatedText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                            )
                        }
                    }
                }
            }
        }
    }
}