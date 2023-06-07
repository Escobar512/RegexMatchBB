package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class OfferSwipe {
    @SerializedName("lookerId")
    var lookerId: String = ""

    @SerializedName("offerId")
    var offerId: String = ""

    @SerializedName("swiped")
    var swiped: Boolean = false
}