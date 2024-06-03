package com.kz.dagdy.di.contributes_android_injector.fragment

import com.kz.dagdy.ui.qarjy.dialog.SelectionDateDialog
import com.kz.dagdy.ui.qarjy.dialog.selectionTypePaid.SelectionItemDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun selectionDateFragment(): SelectionDateDialog

    @ContributesAndroidInjector
    internal abstract fun selectionItemFragment(): SelectionItemDialog
}