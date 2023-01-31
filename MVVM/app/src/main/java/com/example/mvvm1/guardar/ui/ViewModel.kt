package com.example.mvvm1.guardar.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class  ViewModel:ViewModel() {

    private val _nombre_tropa = MutableLiveData<String>()
    val nombre_tropa : LiveData<String> = _nombre_tropa

    private val _espacio_tropa = MutableLiveData<String>()
    val espacio_tropa : LiveData<String> = _espacio_tropa

    private val _nivel_tropa = MutableLiveData<String>()
    val nivel_tropa : LiveData<String> = _nivel_tropa

    private val _danio_tropa = MutableLiveData<String>()
    val danio_tropa : LiveData<String> = _danio_tropa

    private val _vida_tropa = MutableLiveData<String>()
    val vida_tropa : LiveData<String> = _vida_tropa

    private val _isButtonEnable =MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    fun onCompletedFields(nombre_tropa:String, espacio_tropa:String, nivel_tropa:String, danio_tropa:String, vida_tropa:String) {
        _nombre_tropa.value = nombre_tropa
        _espacio_tropa.value = espacio_tropa
        _nivel_tropa.value = nivel_tropa
        _danio_tropa.value = danio_tropa
        _vida_tropa.value = vida_tropa

        _isButtonEnable.value = enableButton(nombre_tropa, espacio_tropa, nivel_tropa, danio_tropa, vida_tropa)
    }

    fun enableButton(nombre_tropa:String, espacio_tropa:String, nivel_tropa:String, danio_tropa:String, vida_tropa:String) =
        //Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        nombre_tropa.length >0 && espacio_tropa.length >0 && nivel_tropa.length>0 && danio_tropa.length>0 && vida_tropa.length>0
}