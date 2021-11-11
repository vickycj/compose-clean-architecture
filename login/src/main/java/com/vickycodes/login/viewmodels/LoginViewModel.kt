package com.vickycodes.login.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.vickycodes.login.base.BaseViewModel
import com.vickycodes.login.data.Results
import com.vickycodes.login.modules.NetworkModule

class LoginViewModel : BaseViewModel() {

    private val loginRepository = NetworkModule.loginRepository
    var userName by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginSuccess = mutableStateOf(false)
    val loginResult: State<Boolean> = _loginSuccess

    fun loginClicked() {
        effect {
            loaderVal.value = true
            when (val result = loginRepository.login(userName, password)) {
                is Results.Success -> {
                    _loginSuccess.value = true
                }
                is Results.Error -> {
                    _loginSuccess.value = true
                }
            }
            loaderVal.value = false
        }
    }

}