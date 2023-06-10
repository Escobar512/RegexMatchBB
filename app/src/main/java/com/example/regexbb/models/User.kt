package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("userId")
    val userId: String = ""
    @SerializedName("_id")
    val _id: String = ""

    @SerializedName("userName")
    var userName: String = ""

    @SerializedName("password")
    var password: String = ""


    @SerializedName("email")
    var email: String = ""

    @SerializedName("isLooking")
    var isLooking: Boolean = false

}