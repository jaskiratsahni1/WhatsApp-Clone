package com.example.whatsapp.presentation.splashscreen.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.whatsapp.presentation.splashscreen.viewmodel.PhoneAuthViewmodel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.example.whatsapp.R
import com.example.whatsapp.presentation.splashscreen.navigation.Routes

@Composable
fun UserProfileScreen(phoneAuthViewmodel: PhoneAuthViewmodel= hiltViewModel(), navHostController: NavHostController) {

    var name by remember {
        mutableStateOf("")
    }

    var status by remember {
        mutableStateOf("")
    }
    var image by remember {
        mutableStateOf<Uri?>(null)
    }
    var bitmapImage by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val firebaseAuth= Firebase.auth
    val phoneNumber=firebaseAuth.currentUser?.phoneNumber?:""
    val userId=firebaseAuth.currentUser?.uid?:""
    val context= LocalContext.current

    val imagePickerLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            uri: Uri?  ->

            image=uri
            uri?.let {

                bitmapImage=if (Build.VERSION.SDK_INT<28){
                    @Suppress("DEPRECATION")
                    android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                }
                else{
                    val source= ImageDecoder.createSource(context.contentResolver,it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    )

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(2.dp, color = Color.Gray, shape = CircleShape)
                .clickable { imagePickerLauncher.launch("image/*") }
        )
        {
            if (bitmapImage != null) {
                Image(
                    bitmap = bitmapImage!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else if (image!=null){
                Image(
                    painter = rememberImagePainter(image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            else {
                Image(
                    painter = painterResource(R.drawable.woman),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "$phoneNumber")

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource( R.color.light_green)
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource( R.color.light_green)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            phoneAuthViewmodel.saveUserProfile(userId,name,status,bitmapImage)
            navHostController.navigate(Routes.HomeScreen)
        }, colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
            Text("Save")
        }
    }
}