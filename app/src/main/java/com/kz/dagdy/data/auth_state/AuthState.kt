package com.kz.dagdy.data.auth_state

import com.kz.dagdy.data.preferences.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthState
@Inject constructor(
    private val preferences: Preferences
) {

    val isAuthorized: Boolean
        get() = preferences.getAppToken() != null

}