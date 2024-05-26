package com.kz.dagdy.utils.locale

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.kz.dagdy.data.enums.api.LanguageEnums
import com.kz.dagdy.data.preferences.PreferencesImpl
import java.util.*

//http://developine.com/android-app-language-change-localization-programatically-kotlin-example/
object LocaleUtils {

    fun setLocale(context: Context?): Context? {
        return if (context != null) {
            val preferences = PreferencesImpl(context)

            if (preferences.getLanguage() != null) {
                updateResources(context, preferences.getLanguage()!!)
            } else {
                context
            }

        } else {
            context
        }
    }

    fun setNewLocale(context: Context, language: String) {
        updateResources(context, language)
    }

    private fun updateResources(context: Context, language: String): Context {

        val locale = Locale(getSystemLanguage(language))
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)

        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)
        } else {
            configuration.locale = locale
            configuration.setLayoutDirection(locale)
            resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        }

        return context.createConfigurationContext(configuration)
    }

    private fun getSystemLanguage(language: String): String {
        return when (language) {
            LanguageEnums.KZ.apiId -> {
                LanguageEnums.KZ.systemId
            }
            LanguageEnums.RU.apiId -> {
                LanguageEnums.RU.systemId
            }
            LanguageEnums.EN.apiId -> {
                LanguageEnums.EN.systemId
            }
            else -> {
                LanguageEnums.DEFAULT.systemId
            }
        }
    }

}