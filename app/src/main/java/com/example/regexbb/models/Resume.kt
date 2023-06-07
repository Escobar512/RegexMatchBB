package com.example.regexbb.models

import com.google.gson.annotations.SerializedName

class Resume {
    @SerializedName("profileId")
    var profileId: String = ""

    @SerializedName("resumeUrl")
    var resumeUrl: String = ""
}