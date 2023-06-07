package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class LookingProfile {
    @SerializedName("profileId")
    var profileId: String = ""

    @SerializedName("userId")
    var userId: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("age")
    var age: Int = 0

    @SerializedName("degree")
    var degree: String = ""

    @SerializedName("school")
    var school: String = ""

    @SerializedName("position")
    var position: String = ""
}