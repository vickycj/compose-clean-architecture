package com.vickycodes.login.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vickycodes.login.R
import com.vickycodes.login.base.BaseActivity
import com.vickycodes.login.screens.Loader
import com.vickycodes.login.screens.LoginScreen

import com.vickycodes.login.ui.theme.ComposeCleanArchitectureTheme
import com.vickycodes.login.viewmodels.LoginViewModel

class LoginActivity : BaseActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val isLoading by loginViewModel.loader
                    val loginResult by loginViewModel.loginResult
                    when {
                        loginResult -> loginSuccessScreen()
                        else -> launchLoginScreen(
                            loginViewModel = loginViewModel,
                            isLoading = isLoading
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun launchLoginScreen(loginViewModel: LoginViewModel, isLoading: Boolean) {
    Box(modifier = Modifier.fillMaxSize()) {
        LoginScreen(
            username = loginViewModel.userName,
            usernameValChange = {
                loginViewModel.userName = it
            },
            password = loginViewModel.password,
            passwordValChange = {
                loginViewModel.password = it
            },
            loginButtonClick = {
                loginViewModel.loginClicked()
            })

        Loader(isLoading = isLoading)
    }
}

@Composable
fun loginSuccessScreen() {
    Text(text = stringResource(R.string.login_success_label))
}

