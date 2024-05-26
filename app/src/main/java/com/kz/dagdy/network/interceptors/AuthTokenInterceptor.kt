package com.kz.dagdy.network.interceptors

import com.kz.dagdy.data.consts.Header
import com.kz.dagdy.network.utils.HeaderUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthTokenInterceptor(
    private val headerUtils: HeaderUtils
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // Customize the request
        val request = original.newBuilder()
            .header(Header.ACCEPT, headerUtils.getAccept())
            .header(Header.DEVICE_ID, headerUtils.getDeviceId())
            .header(Header.APP_TOKEN, headerUtils.getAppToken())
            .header(Header.APP_VERSION, headerUtils.getAppVersion())
            .header(Header.LANG, headerUtils.getLang())
            .header(Header.DEVICE_BRAND, headerUtils.getDeviceBrand())
            .header(Header.DEVICE_MODEL, headerUtils.getDeviceModel())
            .header(Header.DEVICE_OS, headerUtils.getDeviceOs())
            .header(Header.DEVICE_OS_MAIN, headerUtils.getDeviceOsMain())
            .header(Header.DEVICE_ADD_INFO, headerUtils.getDeviceAddInfo())
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }

}