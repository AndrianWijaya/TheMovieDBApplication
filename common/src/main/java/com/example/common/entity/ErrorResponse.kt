package com.example.common.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("body")
    val body: String,
    @SerializedName("code")
    val code: Int
)