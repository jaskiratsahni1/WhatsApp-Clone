package com.example.whatsapp.presentation.splashscreen.updatescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.presentation.splashscreen.bottomnavigation.BottomNavigation
import com.example.whatsapp.presentation.splashscreen.navigation.Routes


@Composable
fun UpdateScreen(navHostController: NavHostController) {

    val scrollState= rememberScrollState()

    val sampledata= listOf(
        StatusData(img = R.drawable.boy, name = "Suraj", time = "10 min ago"),
        StatusData(img = R.drawable.ajay_devgn, name = "Ajay", time = "5 min ago"),
        StatusData(img = R.drawable.boy3, name = "Jaskirat", time = "1 hour ago"),

    )

    val sampleChannel=listOf(
        Channels(image = R.drawable.neat_roots, text = "Neat Roots", subtext = "Latest updates in tech"),
        Channels(image = R.drawable.neat_roots, text = "Neat Roots", subtext = "Latest updates in tech"),

        )

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = colorResource(R.color.light_green),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White
            ) {

                Icon(painter = painterResource(R.drawable.camera), contentDescription = null, modifier = Modifier.size(30.dp))
        }
        }, bottomBar = {
            BottomNavigation(navHostController, selectedItem = 0, onClick = {index->
                when(index){
                    0-> {navHostController.navigate(Routes.HomeScreen)}
                    1-> {navHostController.navigate(Routes.UpdateScreen)}
                    2-> {navHostController.navigate(Routes.CommunityScreen)}
                    3-> {navHostController.navigate(Routes.CallScreen)}


                }

            })
        }, topBar = {
            TopBar()
        }
    ){
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .verticalScroll(scrollState)) {

            Text(
                "Status",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
            )

            MyStatus()

            sampledata.forEach {
                StatusItem(statusData = it)
            }


            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(top = 15.dp),
                color = Color.Gray
            )

            Text(
                "Channels",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
            )

            Column (modifier = Modifier.padding(horizontal = 12.dp)){
                Text("stay updated on topics that matter to you. Find channels to follow below")

                Spacer(modifier = Modifier.height(32.dp))

                Text("Find channels to follow")

            }

            Spacer(modifier = Modifier.height(16.dp))

            sampleChannel.forEach {
                ChannelItemDesign(channels = it)
            }
        }
    }

}