package com.example.whatsapp.presentation.splashscreen.userregistrationscreen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.presentation.splashscreen.navigation.Routes
import com.example.whatsapp.presentation.splashscreen.viewmodel.AuthState
import com.example.whatsapp.presentation.splashscreen.viewmodel.PhoneAuthViewmodel

@Composable
fun UserRegistrationScreen(navController: NavHostController,phoneAuthViewmodel: PhoneAuthViewmodel= hiltViewModel()) {

    val authstate by phoneAuthViewmodel._authstate.collectAsState()
    val context= LocalContext.current
    val activity=LocalContext.current as Activity

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedCountry by remember {
        mutableStateOf("India")
    }
    var countrycode by remember {
        mutableStateOf("+91")
    }
    var phonenumber by remember {
        mutableStateOf("")
    }
    var otp by remember {
        mutableStateOf("")
    }
    var verificationId by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Enter your phone number",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.light_green),

            )

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text("WhatsApp will need to verify your phone number", fontSize = 15.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text("What's", fontSize = 15.sp, color = colorResource(R.color.dark_green))
        }
        Text("my number?", fontSize = 15.sp, color = colorResource(R.color.dark_green))

        Spacer(modifier = Modifier.height(30.dp))

        TextButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.width(230.dp)) {
                Text(
                    selectedCountry,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.align(
                        Alignment.CenterEnd
                    ),
                    tint = colorResource(R.color.light_green)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 66.dp),
            thickness = 2.dp,
            color = colorResource(R.color.light_green)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("India", "USA", "Japan", "Canada").forEach { country ->
                DropdownMenuItem(text = { Text(text = country) }, onClick = {
                    selectedCountry = country
                    expanded = false
                })

            }
        }

        when(authstate){
            is AuthState.Ideal, is AuthState.Loading, is AuthState.CodeSent ->{
                if (authstate is AuthState.CodeSent){
                    verificationId= (authstate as AuthState.CodeSent).verificationId
                }
                if (verificationId==null){
                    Spacer(modifier = Modifier.height(16.dp))
                    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(20.dp)){


                           TextField(
                               value = countrycode,
                               onValueChange = { countrycode = it },
                               modifier = Modifier.width(70.dp),
                               singleLine = true,
                               colors = TextFieldDefaults.colors(
                                   unfocusedContainerColor = Color.Transparent,
                                   focusedContainerColor = Color.Transparent,
                                   unfocusedIndicatorColor = Color.Transparent,
                                   focusedIndicatorColor = colorResource( R.color.light_green)
                               )

                           )



                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value = phonenumber,
                            onValueChange = { phonenumber = it },
                            placeholder = { Text("Phone Number") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = colorResource( R.color.light_green)
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp ))

                    Button(onClick = {
                        if (phonenumber.isNotEmpty()){
                            val fullPhoneNumber="$countrycode$phonenumber"
                            phoneAuthViewmodel.sendVerificationCode(fullPhoneNumber,activity)
                        }
                        else{
                            Toast.makeText(context,"Please enter valid phone number", Toast.LENGTH_LONG).show()
                        }
                    },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))
                    ) {
                        Text("Send OTP")
                    }

                    if (authstate is AuthState.Loading){

                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }
                }

                else{
                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        "Enter OTP",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.dark_green)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = otp,
                        onValueChange = { otp = it },
                        placeholder = { Text("OTP") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = colorResource( R.color.light_green)
                        )
                    )
                    Spacer(modifier = Modifier.height(32.dp ))

                    Button(onClick = {
                        if (otp.isNotEmpty() && verificationId !=null){
                            phoneAuthViewmodel.verifyCode(otp,context)

                        } else {
                            Toast.makeText(context, "Please enter a valid OTP", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))
                    ) {
                        Text("Verify OTP")
                    }

                    if (authstate is AuthState.Loading){
                        Spacer(modifier = Modifier.height(6.dp))
                        CircularProgressIndicator()
                    }
                }
            }

             is AuthState.Success -> {
                 Log.d("PhoneAuth","LoginSuccessful")
                 phoneAuthViewmodel.resetAuthState()
                 navController.navigate(Routes.UserProfileScreen){
                     popUpTo<Routes.RegistrationScreen>(){
                         inclusive=true
                     }
                 }
             }
            is AuthState.Error -> {
                Toast.makeText(context,(authstate as AuthState.Error).message, Toast.LENGTH_LONG).show()
            }
        }

    }
}