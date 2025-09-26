package com.example.whatsapp.presentation.splashscreen.updatescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource


@Composable
fun ChannelItemDesign(channels: Channels) {
    var isfollowing by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(channels.image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(channels.text, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(channels.subtext, fontSize = 14.sp, color = Color.Gray)

        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { isfollowing = !isfollowing }, colors = ButtonDefaults.buttonColors(
                containerColor = if (isfollowing) {
                    Color.Gray
                } else {
                    colorResource(R.color.light_green)
                }
            ), modifier = Modifier.padding(12.dp).height(36.dp)
        ) {

            Text(if (isfollowing){"Following"}else{"Follow"}, color = if (isfollowing){
                Color.Black}else{Color.White}, fontWeight = FontWeight.Bold)

        }
    }
}
data class Channels(val image: Int,val text: String,val subtext: String)