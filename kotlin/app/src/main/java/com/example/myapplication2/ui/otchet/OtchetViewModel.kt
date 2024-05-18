package com.example.myapplication2.ui.otchet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtchetViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is otchet Fragment"
    }
    val text: LiveData<String> = _text
}