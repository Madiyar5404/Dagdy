package com.kz.dagdy.network.utils

import org.json.JSONObject

object JsonUtils {

    fun isElementOk(json: JSONObject, fieldName: String?): Boolean {
        return json.has(fieldName) && !json.isNull(fieldName)
    }
}