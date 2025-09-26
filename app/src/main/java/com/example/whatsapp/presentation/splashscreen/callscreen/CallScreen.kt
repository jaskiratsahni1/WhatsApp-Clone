package com.example.whatsapp.presentation.splashscreen.callscreen

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
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.presentation.splashscreen.bottomnavigation.BottomNavigation
import com.example.whatsapp.presentation.splashscreen.navigation.Routes

@Composable
fun CallScreen(navHostController: NavHostController) {

    var issearching by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var showmenu by remember { mutableStateOf(false) }

    val CallItems = listOf(
        Call(name = "Salman Khan", image = R.drawable.boy, time = "Yesterday, 8:30 PM"),
        Call(name = "Shahrukh Khan", image = R.drawable.boy1, time = "Today, 8 PM"),
        Call(name = "Carry", image = R.drawable.carryminati, time = "Yesterday, 8:30 PM"),
        Call(name = "Disha", image = R.drawable.disha_patani, time = "Yesterday, 8:30 PM"),
        Call(name = "Salman Khan", image = R.drawable.boy, time = "Yesterday, 8:30 PM"),
        Call(name = "Shahrukh Khan", image = R.drawable.boy1, time = "Today, 8 PM"),
        Call(name = "Carry", image = R.drawable.carryminati, time = "Yesterday, 8:30 PM"),
        Call(name = "Disha", image = R.drawable.disha_patani, time = "Yesterday, 8:30 PM")
    )

    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(modifier = Modifier.padding(top = 20.dp)) {

                        if (issearching) {
                            TextField(
                                value = search,
                                onValueChange = { search = it },
                                placeholder = { Text("Search") },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        } else {
                            Text(
                                "Calls",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        if (issearching) {
                            IconButton(
                                onClick = {
                                    issearching = false
                                    search = ""
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.cross),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = { issearching = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Box {
                                IconButton(onClick = { showmenu = true }) {
                                    Icon(
                                        painter = painterResource(R.drawable.more),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                DropdownMenu(
                                    expanded = showmenu,
                                    onDismissRequest = { showmenu = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Settings") },
                                        onClick = { showmenu = false }
                                    )
                                }
                            }
                        }
                    }
                    HorizontalDivider(thickness = 1.dp)
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                navHostController,
                selectedItem = 0,
                onClick = { index ->
                    when (index) {
                        0 -> navHostController.navigate(Routes.HomeScreen)
                        1 -> navHostController.navigate(Routes.UpdateScreen)
                        2 -> navHostController.navigate(Routes.CommunityScreen)
                        3 -> navHostController.navigate(Routes.CallScreen)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = colorResource(R.color.light_green),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.telephone),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp) // ✅ fixed here
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                "Favourites",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp, top = 24.dp)
            )

            FavouriteSection()

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.light_green))
            ) {
                Text(
                    "Start a new call",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Text(
                "Recent Calls",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp, top = 15.dp)
            )

            LazyColumn {
                items(CallItems) {
                    CallItemDesign(it)
                }
            }
        }
    }
}