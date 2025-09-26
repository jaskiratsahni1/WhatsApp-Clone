package com.example.whatsapp.presentation.splashscreen.callscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Favourites(favouriteItem: FavouriteItem) {

    Column (modifier = Modifier.padding(top = 20.dp, start = 10.dp), horizontalAlignment = Alignment.CenterHorizontally){

        Image(
            painter = painterResource(favouriteItem.img),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape), contentScale = ContentScale.Crop)

        Spacer(modifier = Modifier.height(4.dp))

        Text(favouriteItem.name, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)

    }
}
