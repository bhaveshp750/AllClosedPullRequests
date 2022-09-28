package com.bhaveshp750.allclosedpullrequests.modal

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val name: String,
    val avatar_url:String?
)
