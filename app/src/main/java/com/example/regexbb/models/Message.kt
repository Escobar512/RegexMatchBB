package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class Message {
    @SerializedName("sender")
    var sender: String = ""

    @SerializedName("receiver")
    var receiver: String = ""

    @SerializedName("message")
    var message: String = ""
}