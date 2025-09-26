package com.example.whatsapp.presentation.splashscreen.updatescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R

@Composable
@Preview(showSystemUi = true)
fun TopBar(modifier: Modifier = Modifier) {

    var issearching by remember {
        mutableStateOf(false)

    }
    var search by remember {
        mutableStateOf("")
    }
    var showmenu by remember {
        mutableStateOf(false)
    }

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
                        "Updates",
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
                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
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
                            DropdownMenuItem({Text("Status Privacy")},{showmenu=false})

                            DropdownMenuItem({Text("Create Channel")},{showmenu=false})

                            DropdownMenuItem({Text("Settings")},{showmenu=false})
                        }
                    }
                }

                }
            HorizontalDivider(thickness = 1.dp)
            }
        }
    }