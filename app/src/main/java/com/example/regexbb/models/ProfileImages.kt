package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class ProfileImages {
    @SerializedName("profileId")
    var profileId: String = ""

    @SerializedName("imageUrl")
    var imageUrl: String = ""

    @SerializedName("isLooker")
    var isLooker: Boolean = false
}