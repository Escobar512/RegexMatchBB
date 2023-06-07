package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class LookerPreferences {
    @SerializedName("pay")
    var pay: Double = 0.0

    @SerializedName("country")
    var country: String = ""

    @SerializedName("state")
    var state: String = ""

    @SerializedName("schedule")
    var schedule: String = ""

    @SerializedName("mode")
    var mode: String = ""

    @SerializedName("lookerId")
    var lookerId: String = ""
}