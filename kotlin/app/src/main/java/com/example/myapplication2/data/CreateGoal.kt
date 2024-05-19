package com.example.myapplication2.data

import com.google.gson.annotations.SerializedName

data class CreateGoal(
    @SerializedName("accountId") val accountId : Int,
    @SerializedName("monthlyIncome") val monthlyIncome: Float,
    @SerializedName("monthlySavings") val monthlySavings: Float
)
