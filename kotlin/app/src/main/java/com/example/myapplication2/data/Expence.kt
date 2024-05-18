package com.example.myapplication2.data

import com.google.gson.annotations.SerializedName

data class Expence (
    @SerializedName("accountId") val accountId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("amount") val amount: Float,
    @SerializedName("categoryTitle") val categoryTitle: String

)