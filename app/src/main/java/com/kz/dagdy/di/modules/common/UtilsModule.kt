package com.kz.dagdy.di.modules.common

import com.kz.dagdy.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import com.kz.dagdy.network.utils.HeaderUtils
import com.kz.dagdy.network.utils.UniqueID
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideUniqueID(preferences: Preferences) = UniqueID(preferences)

    @Provides
    @Singleton
    fun provideHeaderUtils(preferences: Preferences, uniqueID: UniqueID) =
        HeaderUtils(preferences, uniqueID)

}