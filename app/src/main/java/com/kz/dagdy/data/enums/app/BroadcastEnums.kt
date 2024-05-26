package com.kz.dagdy.data.enums.app

import com.kz.dagdy.BuildConfig

enum class BroadcastEnums(val id: String) {

    FOREGROUND_PUSH("${BuildConfig.APPLICATION_ID}.foreground_push")

}