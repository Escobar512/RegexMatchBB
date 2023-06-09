package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class ObjectTechnologies : Serializable {
    @SerializedName("technologyId")
    var technologyId: String = ""

    @SerializedName("dObjectId")
    var dObjectId: String = ""

    @SerializedName("isLooking")
    var isLooking: Boolean = false
}