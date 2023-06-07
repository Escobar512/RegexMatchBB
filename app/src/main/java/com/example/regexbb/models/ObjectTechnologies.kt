package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class ObjectTechnologies {
    @SerializedName("technologyId")
    var technologyId: String = ""

    @SerializedName("dObjectId")
    var dObjectId: String = ""

    @SerializedName("isLooking")
    var isLooking: Boolean = false
}