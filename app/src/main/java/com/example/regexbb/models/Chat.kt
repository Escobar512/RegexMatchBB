package com.example.regexbb.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class Chat {
    @SerializedName("lookerId")
    var lookerId: String = ""

    @SerializedName("offererId")
    var offererId: String = ""

    @SerializedName("offerId")
    var offerId: String = ""


    @SerializedName("chatId")
    var chatId: String = ""


    @SerializedName("lastUpdate")
    var lastUpdate: Date? = null
}