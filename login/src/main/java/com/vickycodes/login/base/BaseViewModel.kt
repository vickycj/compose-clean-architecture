package com.vickycodes.login.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected val loaderVal = mutableStateOf(false)
    val loader : State<Boolean> = loaderVal

    protected fun effect(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) { block() }    // 4
    }

}