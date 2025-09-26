package com.example.whatsapp.presentation.splashscreen.viewmodel

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whatsapp.model.PhoneAuthUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import androidx.core.content.edit
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.io.ByteArrayOutputStream
import android.util.Base64

@HiltViewModel
class PhoneAuthViewmodel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase): ViewModel() {

    private val authstate= MutableStateFlow<AuthState>(AuthState.Ideal)

    val _authstate=authstate.asStateFlow()

    private val userRef=database.reference.child("users")

    fun sendVerificationCode(phoneNumber: String,activity: Activity){

        authstate.value= AuthState.Loading

        val option = object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                 super.onCodeSent(id, token)
                Log.d("PhoneAuth","onCodeSent triggered. Verification ID:$id ")
                authstate.value= AuthState.CodeSent(id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                signinWithCredentials(credential, context =activity)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("PhoneAuth","Verification failed:${exception.message}")
                authstate.value= AuthState.Error(exception.message?:"Verification failed")
            }


        }
        val phoneAuthOption =
            PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phoneNumber).setTimeout(
                60L,
                TimeUnit.SECONDS
            ).setActivity(activity).setCallbacks(option).build()

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOption)
    }

    private fun signinWithCredentials(credential: PhoneAuthCredential, context: Context){
        authstate.value= AuthState.Loading
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val user=firebaseAuth.currentUser
                val phoneAuthUser= PhoneAuthUser(
                    userId = user?.uid?:"",
                    phonenumber = user?.uid?:""
                )
                markUserAsSignedIn(context)
                authstate.value= AuthState.Success(phoneAuthUser)
                fetchUserProfile(user?.uid?:"")
            }
            else{
                authstate.value= AuthState.Error(task.exception?.message?:"Sign in failed")
            }
        }
    }
    private fun markUserAsSignedIn(context: Context){
        val sharedPreference=context.getSharedPreferences("app_prefs",Context.MODE_PRIVATE)
        sharedPreference.edit { putBoolean("isSignedIn", true) }

    }
    private fun fetchUserProfile(userId: String){
        val userRef=userRef.child(userId)
        userRef.get().addOnSuccessListener {snapshot ->

            if (snapshot.exists()){
                val userProfile=snapshot.getValue(PhoneAuthUser::class.java)

                if (userProfile !=null){
                    authstate.value= AuthState.Success(userProfile)
                }
            }

        }.addOnFailureListener {
            authstate.value= AuthState.Error("Failed to fetch user profile")
        }
    }
    fun verifyCode(otp: String,context: Context){
        val currentAuthState=authstate.value
        if (currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()){
            Log.e("PhoneAuth","Attempting to verify otp without valid verification ID")
            authstate.value= AuthState.Error("Verification not started or invalid ID")
            return
        }

        val credential= PhoneAuthProvider.getCredential(currentAuthState.verificationId,otp)
        signinWithCredentials(credential, context)
    }

    fun saveUserProfile(userId: String,name: String,status: String,profileImg: Bitmap?){

        val database= FirebaseDatabase.getInstance().reference

        val encodedImg=profileImg?.let { convertBitmapToBase64(it) }
        val userProfile = PhoneAuthUser(
            userId = userId,
            name = name,
            status = status,
            phonenumber = Firebase.auth.currentUser?.phoneNumber ?: ""
        )
        database.child("users").child(userId).setValue(userProfile)
    }

   private fun convertBitmapToBase64( bitmap: Bitmap): String{
         val byteArrayOutputStream= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG ,100,byteArrayOutputStream)
        val byteArray=byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
    fun resetAuthState(){
        authstate.value= AuthState.Ideal
    }

    fun signOut(activity: Activity){
        firebaseAuth.signOut()
        val sharedPreference= activity.getSharedPreferences("app_prefs", Activity.MODE_PRIVATE)
        sharedPreference.edit().putBoolean("isSigned",false).apply()
    }
}

sealed class AuthState{

    object Ideal: AuthState()
    object Loading: AuthState()
    data class CodeSent(val verificationId: String): AuthState()
    data class Success(val user: PhoneAuthUser): AuthState()
    data class Error(val message: String?): AuthState()
}