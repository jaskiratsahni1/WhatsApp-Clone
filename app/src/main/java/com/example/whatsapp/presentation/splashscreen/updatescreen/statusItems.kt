package com.example.whatsapp.presentation.splashscreen.updatescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R
import java.nio.file.WatchEvent

@Composable
fun MyStatus(modifier: Modifier = Modifier) {


    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp, top = 20.dp), verticalAlignment = Alignment.CenterVertically){
        Box {
            Image(
                painter = painterResource(R.drawable.akshay_kumar),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.BottomEnd)
                    .padding(2.dp)
                    .background(
                        color = colorResource(R.color.light_green),
                        shape = RoundedCornerShape(16.dp)
                    )
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text("My Status", fontWeight = FontWeight.Bold, fontSize=16.sp)

            Text("Tap to add status update ", fontSize=14.sp, color = Color.Gray)
        }
    }
}

data class StatusData(val img: Int, val name: String, val time: String)
@Composable
fun StatusItem(statusData: StatusData){

    Row (modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 18.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(statusData.img),
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(statusData.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Text(statusData.time, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
