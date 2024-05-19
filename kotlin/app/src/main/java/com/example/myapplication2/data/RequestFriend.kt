package com.example.myapplication2.data

import android.accounts.Account
import com.google.gson.annotations.SerializedName

data class RequestFriend(
    @SerializedName("accountId") val accountId : Int,
    @SerializedName("login") val login: String
)
