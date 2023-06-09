package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class ProfileImages : Serializable {
    @SerializedName("profileId")
    var profileId: String = ""

    @SerializedName("imageUrl")
    var imageUrl: String = ""

    @SerializedName("isLooker")
    var isLooker: Boolean = false
}