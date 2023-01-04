package ru.myrkwill.androidapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.myrkwill.androidapp.adapter.WeatherModel

class MainViewModel: ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}