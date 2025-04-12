package com.example.pintrestsample.presentation.view.SearchScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pintrestsample.data.model.PopularItems
import com.example.pintrestsample.data.services.ApiResponse

@Composable
fun PopularItemList(apiResponse: ApiResponse<List<PopularItems>>, context: Context) {
    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = "Popular",
        style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal)
    )
    Spacer(modifier = Modifier.height(12.dp))

    when (apiResponse) {
        is ApiResponse.Error -> {
            Toast.makeText(context, apiResponse.errorMessage, Toast.LENGTH_LONG).show()
        }

        is ApiResponse.Failed -> {
            Toast.makeText(context, apiResponse.failedException, Toast.LENGTH_LONG).show()
        }

        is ApiResponse.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(apiResponse.data) { PopularItems ->
                    AsyncImage(
                        model = PopularItems.cover_photo?.urls?.thumb,
                        contentDescription = PopularItems.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }

}