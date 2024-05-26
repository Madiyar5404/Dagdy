package com.kz.dagdy.di.modules.common

import android.app.Application
import com.kz.dagdy.data.preferences.Preferences
import com.kz.dagdy.data.preferences.PreferencesImpl
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferences(app: Application): Preferences {
        return PreferencesImpl(app)
    }

}