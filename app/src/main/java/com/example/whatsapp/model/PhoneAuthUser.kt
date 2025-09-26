package com.example.whatsapp.model

data class PhoneAuthUser(
    val userId: String? ="",
    val phonenumber: String ="",
    val name: String ="",
    val status: String ="",
    val profileimg: String? =null
)
