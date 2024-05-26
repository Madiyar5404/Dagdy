package com.kz.dagdy.data.preferences

interface Preferences {

    /**
     *
     */
    fun setUniqueID(uniqueID: String?)

    fun getUniqueID(): String?

    fun setLanguage(language: String)

    fun getLanguage(): String?

    /**
     *
     */
    fun setAppToken(appToken: String)

    fun getAppToken(): String?

    fun removeAppToken()

    fun getSaveUserEmail(): String?

    fun setSaveUserEmail(email: String)

    fun getUserEmail(): String?

    fun setUserEmail(email: String)

    fun tempRemoveLanguage()

}