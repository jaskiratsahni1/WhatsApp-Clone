package com.example.whatsapp.presentation.splashscreen.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.presentation.splashscreen.bottomnavigation.BottomNavigation
import com.example.whatsapp.presentation.splashscreen.navigation.Routes

@Composable
fun CommunityScreen(navHostController: NavHostController) {

    var issearching by remember {
        mutableStateOf(false)

    }
    var search by remember {
        mutableStateOf("")
    }
    var showmenu by remember {
        mutableStateOf(false)
    }

    val sampleCommunities=listOf(
        Communities(img = R.drawable.img, name = "Tech Enthusiast", count = "256"),
        Communities(img = R.drawable.img, name = "Tech ", count = "21"),
        Communities(img = R.drawable.img, name = "Enthusiast", count = "1000"),

        )
    Scaffold (topBar = {
        Box(modifier = Modifier.fillMaxWidth()){
            Column {
                Row(modifier = Modifier.padding(top = 20.dp)) {

                    if (issearching) {
                        TextField(
                            search,
                            {
                                search = it
                            },
                            placeholder = {
                                Text("Search")
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ), modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                    else{
                        Text(
                            "Communities",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black, modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                        )

                    }

                    Spacer(modifier = Modifier.weight(1f))
                    if (issearching){
                        IconButton(onClick = {issearching=false
                            search=""
                        }) {
                            Icon(painter = painterResource(R.drawable.cross), contentDescription = null, modifier = Modifier.size(15.dp))
                        }
                    }


                    else{

                        IconButton(onClick = {issearching = true}) {
                            Icon(
                                painter = painterResource(R.drawable.search),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = {showmenu=true}) {

                            Icon(
                                painter = painterResource(R.drawable.more),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            DropdownMenu(showmenu,{showmenu=false}) {
                                DropdownMenuItem({Text("Settings")},{showmenu=false})
                            }
                        }
                    }

                }
                HorizontalDivider(thickness = 1.dp)
            }
        }
    }, bottomBar = {   BottomNavigation(navHostController, selectedItem = 0, onClick = {index->
        when(index){
            0-> {navHostController.navigate(Routes.HomeScreen)}
            1-> {navHostController.navigate(Routes.UpdateScreen)}
            2-> {navHostController.navigate(Routes.CommunityScreen)}
            3-> {navHostController.navigate(Routes.CallScreen)}


        }

    })}){

        Column (modifier = Modifier.padding(it)){

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.light_green)), modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text("Start a new community", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text("Your Communities", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))

            LazyColumn {
                items(sampleCommunities){
                    CommunityItemDesign(communities = it)
                }
            }
        }

    }
}