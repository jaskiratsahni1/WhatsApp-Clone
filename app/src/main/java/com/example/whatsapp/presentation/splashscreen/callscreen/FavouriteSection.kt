package com.example.whatsapp.presentation.splashscreen.callscreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whatsapp.R

@Composable
@Preview(showSystemUi = true)
fun FavouriteSection() {

    val SampleFavourite=listOf(
        FavouriteItem(img = R.drawable.boy, name = "Salman Khan"),
        FavouriteItem(img = R.drawable.boy1, name = "ShahRukh Khan"),
        FavouriteItem(img = R.drawable.boy3, name = "Amitabh Bachan"),
        FavouriteItem(img = R.drawable.carryminati, name = "Carry"),
        FavouriteItem(img = R.drawable.disha_patani, name = "Disha Patani"),
    )
    Row (modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())){

        SampleFavourite.forEach {

            Favourites(it)
        }

    }
}

data class FavouriteItem(val img: Int,val name: String)