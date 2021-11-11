package com.vickycodes.login.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vickycodes.login.R
import com.vickycodes.login.ui.theme.ComposeCleanArchitectureTheme


@Composable
fun Login(
    username: String,
    usernameValChange: (String) -> Unit,
    password: String,
    passwordValChange: (String) -> Unit,
    loginButtonClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        TextField(value = username,
            onValueChange = usernameValChange,
            label = { Text(stringResource(id = R.string.user_name)) })
        TextField(value = password,
            onValueChange = passwordValChange,
            label = { Text(stringResource(id = R.string.password_label)) })
        Button(onClick = loginButtonClick) {
            Text(text = stringResource(id = R.string.login_label))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCleanArchitectureTheme {

        Login("",{
            it
        }, "", {

        }, {

        })
    }
}
