package com.kz.dagdy.data.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesImpl(
    private val context: Context
) : Preferences {

    companion object {
        private const val UNIQUE_ID = "unique_id"
        private const val LANGUAGE = "language"
        private const val APP_TOKEN = "app_token"
        private const val SAVE_USER_EMAIL = "email"
        private const val USER_EMAIL = "email"
    }

    private var shPr: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        shPr = context.getSharedPreferences("PreferencesImpl", Context.MODE_PRIVATE)
        editor = shPr.edit()
    }

    /**
     * DEVICE UNIQUE ID
     */
    override fun setUniqueID(uniqueID: String?) {
        editor.putString(UNIQUE_ID, uniqueID)
        editor.commit()
    }

    override fun getUniqueID(): String? = shPr.getString(UNIQUE_ID, null)

    /**
     * LANGUAGE
     */
    override fun setLanguage(language: String) {
        editor.putString(LANGUAGE, language)
        editor.commit()
    }

    override fun getLanguage(): String? {
        return shPr.getString(LANGUAGE, null)
    }

    /**
     *
     */
    override fun setAppToken(appToken: String) {
        editor.putString(APP_TOKEN, appToken)
        editor.commit()
    }

    override fun getAppToken(): String? {
        return shPr.getString(APP_TOKEN, null)
    }

    override fun removeAppToken() {
        editor
            .remove(APP_TOKEN)
            .commit()
    }


    override fun getSaveUserEmail(): String? {
        return shPr.getString(SAVE_USER_EMAIL, null)
    }

    override fun setSaveUserEmail(email: String) {
        editor.putString(SAVE_USER_EMAIL, email)
        editor.commit()
    }

    override fun getUserEmail(): String? {
        return shPr.getString(USER_EMAIL, null)
    }

    override fun setUserEmail(email: String) {
        editor.putString(USER_EMAIL, email)
        editor.commit()
    }

    override fun tempRemoveLanguage() {
        editor
            .remove(LANGUAGE)
            .commit()
    }
}