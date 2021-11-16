package com.vickycodes.login.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performTextInput
import com.vickycodes.login.R
import com.vickycodes.login.ui.theme.ComposeCleanArchitectureTheme
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest  {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen() {
        var userName by mutableStateOf("")
        var password by mutableStateOf("")
        var usernameString = ""
        var passwordString = ""
        composeTestRule.setContent {
            ComposeCleanArchitectureTheme {
                usernameString = stringResource(id = R.string.user_name)
                passwordString = stringResource(id = R.string.password_label)
                LoginScreen(
                    username = userName,
                    usernameValChange = { userName = it },
                    password = password,
                    passwordValChange = { password = it },
                    loginButtonClick = {

                    }
                )
            }
        }


        composeTestRule
            .onNode(hasText(usernameString))
            .performTextInput("Vignesh")

        composeTestRule
            .onNode(hasText(passwordString))
            .performTextInput("Test@123")

        composeTestRule.onNode(hasText(usernameString)).assert(hasText("Vignesh"))

        composeTestRule.onNode(hasText(passwordString)).assert(hasText("Test@123"))

    }
}