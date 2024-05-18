package com.example.myapplication2.data

import com.google.gson.annotations.SerializedName

data class Income(
    @SerializedName("accountId") val accountId: Int,
    @SerializedName("profit") val amount: Float,
    @SerializedName("title") val title: String
)