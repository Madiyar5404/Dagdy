package com.kz.dagdy.ui_common.phone

interface PhoneCallback {

    fun initFields(domain: String?, code: String?, phone: String?)

    //
    fun getDomain(): String?

    fun getCode(): String?

    fun getPhone(): String?

    fun getCodeWithoutSign(): String?

    fun setNumber(number: String?)
    //
    fun fieldsOk(): Boolean

    fun showFieldErrors()

    //todo for phone, code, domain
    fun showPhoneFieldError(error: String?)

    //
    fun setEnabled(isEnabled: Boolean)

    //
    fun clearPhoneField()

    //
    fun setPhoneWithCode(phone: String?)

}