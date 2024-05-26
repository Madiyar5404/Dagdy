package com.kz.dagdy.network.exceptions

import com.kz.dagdy.data.models.network.error.ApiError

class ApiException(val apiError: ApiError) : Exception()