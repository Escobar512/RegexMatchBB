package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class OfferingProfile {
    @SerializedName("profileId")
    var profileId: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("userId")
    var userId: String = ""

    @SerializedName("description")
    var description: String = ""
}