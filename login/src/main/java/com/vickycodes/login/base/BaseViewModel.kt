package com.vickycodes.login.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _loader = MutableLiveData<Boolean>()
    val loader : LiveData<Boolean> = _loader

}