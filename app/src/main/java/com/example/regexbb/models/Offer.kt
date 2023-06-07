package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class Offer {
    @SerializedName("offerId")
    var offerId: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("pay")
    var pay: Double = 0.0

    @SerializedName("country")
    var country: String = ""


    @SerializedName("schedule")
    var schedule: String = ""

    @SerializedName("mode")
    var mode: String = ""

    @SerializedName("idOfferor")
    var idOfferor: String = ""
}