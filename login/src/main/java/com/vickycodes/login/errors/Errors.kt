package com.vickycodes.login.errors

sealed class Errors : LoginErrors, GetUserErrors // extend both hierarchies 👍
object ServerError : Errors()
object Forbidden : Errors()
object Unauthorized : Errors()

sealed interface LoginErrors {
    data class InvalidUsername(val username: String) : LoginErrors
    object InvalidPasswordFormat : LoginErrors
}

sealed interface GetUserErrors {
    data class UserNotFound(val userId: String) : GetUserErrors
    data class InvalidUserId(val userId: String) : GetUserErrors
}
