package com.example.regexbb.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class Chat {
    @SerializedName("lookerId")
    var lookerId: String = ""

    @SerializedName("offererId")
    var offererId: String = ""

    @SerializedName("lastUpdate")
    var lastUpdate: Date? = null
}