package com.vickycodes.login.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vickycodes.login.R
import com.vickycodes.login.ui.theme.ComposeCleanArchitectureTheme


@Composable
fun LoginScreen(
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
        Spacer(modifier = Modifier.size(8.dp))
        TextField(
            value = password,
            onValueChange = passwordValChange,
            label = { Text(stringResource(id = R.string.password_label)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = loginButtonClick) {
            Text(text = stringResource(id = R.string.login_label))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ComposeCleanArchitectureTheme {

        LoginScreen("", {
            it
        }, "", {

        }, {

        })
    }
}
