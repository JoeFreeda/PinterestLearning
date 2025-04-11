package com.example.pintrestsample.presentation.view.SearchScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.pintrestsample.model.CreatorsItem
import com.example.pintrestsample.services.ApiResponse

@Composable
fun CreatorItemList(creatorsItem: ApiResponse<List<CreatorsItem>>, context: Context) {
    Text(
        text = "Ideas for creators",
        style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal)
    )
    Spacer(modifier = Modifier.height(12.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        when (creatorsItem) {
            is ApiResponse.Error -> {
                Toast.makeText(context, creatorsItem.errorMessage, Toast.LENGTH_LONG).show()
            }

            is ApiResponse.Failed -> {
                Toast.makeText(context, creatorsItem.failedException, Toast.LENGTH_LONG).show()
            }

            is ApiResponse.Success -> {
                LazyRow {
                    items(creatorsItem.data) {
                        Box {
                            AsyncImage(
                                model = it.urls?.small,
                                contentDescription = it.slug,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            AsyncImage(
                                model = it.urls?.small,
                                contentDescription = it.slug,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.BottomCenter)
                                    .offset(y = 20.dp) // Move it halfway to the right
                                    .zIndex(1f)
                                    .clip(CircleShape)
                                    .border(1.dp, Color.White, CircleShape)
                            )

                        }

                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }
        }
    }
}