package com.example

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name:String,
    val age:Int,
    val route:String
)
