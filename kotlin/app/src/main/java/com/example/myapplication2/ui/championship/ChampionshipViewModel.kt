package com.example.myapplication2.ui.championship

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.NetworkService
import kotlinx.coroutines.launch

class ChampionshipViewModel : ViewModel() {
    private val _economyPercent = MutableLiveData<Float>()

    private val _expence = MutableLiveData<Float>()
    private val _profit = MutableLiveData<Float>()



    val economyPercent: LiveData<Float> = _economyPercent
    val expence: LiveData<Float> = _expence
    val profit: LiveData<Float> = _profit

    val economy : Float = _expence.value?.toFloat() - _profit.value?.toFloat()

    init {
        fetchEconomyPercent()
    }

    private fun fetchEconomyPercent() {
        viewModelScope.launch {
            try {
                val response = NetworkService.api.getEconomyPercent()
                if (response.isSuccessful) {
                    _economyPercent.value = response.body()
                } else {
                    Log.e("ChampionshipVM", "Ошибка получения данных: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ChampionshipVM", "Исключение при получении данных", e)
            }
        }
    }

    private fun fetchEconomy(){
        viewModelScope.launch {
        try {
            val responseExpence = NetworkService.api.getAmount()
            val responseProfit = NetworkService.api.getProfit()
            if (responseExpence.isSuccessful) {
                _expence.value = responseExpence.body()?.toFloat()
            }
            if (responseProfit.isSuccessful){
                _profit.value = responseProfit.body()?.toFloat()

            }
            else {
                Log.e("ChampionshipVM", "Ошибка получения данных: ${responseExpence.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("ChampionshipVM", "Исключение при получении данных", e)
        }
    }
    }



}

