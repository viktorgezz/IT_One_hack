package com.example.myapplication2.data

import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @GET("/expense/get_total_amount/1")
    suspend fun getAmount(): Response<Int>

    @POST("/expense/create")
    suspend fun addExpence(@Body expence: Expence) : Response<ResponseBody>

    @POST("/income/create")
    suspend fun addIncome(@Body income : Income) : Response<ResponseBody>

    @GET("/income/get")
    suspend fun getProfit(): Response<Int>

    @GET("/goal/get_percent_savings_expense/1")
    suspend fun getEconomyPercent(): Response<Float>

    @GET("/goal/get_net_savings/2")
    suspend fun getEconomy(): Response<Float>

    @POST("/goal/create")
    suspend fun createGoal(@Body createGoal : CreateGoal) : Response<ResponseBody>


    @POST("/friend/request_friend")
    suspend fun requestFriend(@Body requestFriend: RequestFriend) : Response<ResponseBody>

    @GET("/friend/get_requeest/1")
    suspend fun getRequestFriend() : Response<List<String>>

}