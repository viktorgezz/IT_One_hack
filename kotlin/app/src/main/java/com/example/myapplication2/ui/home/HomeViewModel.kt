package com.example.myapplication2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.data.NetworkService
import retrofit2.Response
import com.example.myapplication2.data.Expence

import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.Income
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    init {

        getAmountFromApi()

    }

    private fun getAmountFromApi() {
        viewModelScope.launch {
            try {
                val response = NetworkService.api.getAmount()
                if (response.isSuccessful && response.body() != null) {
                    _amount.value = "${response.body()} руб"
                } else {
                    Log.e("HomeViewModel", "Ошибка получения данных: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Исключение при получении данных", e)
            }
        }
    }


    fun addExpense(accountId: Int, title: String, amount: Float, categoryTitle: String) {
        viewModelScope.launch {
            try {
                val expense = Expence(accountId, title, amount, categoryTitle)
                val response = NetworkService.api.addExpence(expense)
                if (response.isSuccessful) {
                    // Обработка успешного добавления траты
                } else {
                    Log.e("HomeViewModel", "Ошибка отправки данных: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Исключение при отправке данных", e)
            }
        }
    }

    fun addIncome(accountId: Int, title: String, profit: Float) {
        viewModelScope.launch {
            try {
                val income = Income(accountId, profit, title)
                val response = NetworkService.api.addIncome(income)
                if (response.isSuccessful) {
                }
                else {
                    Log.e("HomeViewModel", "Ошибка отправки данных: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Исключение при отправке данных", e)
            }
            }
        }




    fun refreshAmount() {
        viewModelScope.launch {
            getAmountFromApi()
        }
    }

}