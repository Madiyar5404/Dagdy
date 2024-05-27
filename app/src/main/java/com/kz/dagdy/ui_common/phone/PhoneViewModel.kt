package com.kz.dagdy.ui_common.phone

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.kz.dagdy.R
import com.kz.dagdy.utils.live_data.Event
import com.kz.dagdy.utils.mask.MaskUtils
import javax.inject.Inject

open class PhoneViewModel
@Inject constructor(
    private val app: Application
) : AndroidViewModel(app) {

    val phoneFieldText = MutableLiveData<String?>()
    val phoneFieldError = MutableLiveData<Any?>()
//    val phoneFieldEnabled = MutableLiveData<Boolean>()

    private var phoneFieldTextChangedCallback: PhoneFieldTextChangedCallback? = null

    fun setPhoneFieldTextChangedCallback(phoneFieldTextChangedCallback: PhoneFieldTextChangedCallback) {
        this.phoneFieldTextChangedCallback = phoneFieldTextChangedCallback
    }

    private val _logEvent = MutableLiveData<Event<String>>()
    val logEvent: LiveData<Event<String>>
        get() = _logEvent

    fun onPhoneFieldTextChanged() {
        if (!phoneFieldText.value.isNullOrBlank()) {
            phoneFieldError.postValue(null)

            phoneFieldText.value?.let {
                if (phoneFieldTextChangedCallback != null
                    && fieldsOk()
                ) {
                    phoneFieldTextChangedCallback?.onPhoneFieldTextCompleted(it)
                    _logEvent.postValue(Event(it))
                } else {
                    phoneFieldTextChangedCallback?.onPhoneFieldTextChanged(it)
                }
            }
        }else{
            phoneFieldError.postValue(null) //resetting error
        }
    }

    fun onClearTextListener() {
        phoneFieldText.postValue("")
        phoneFieldError.postValue(null)
    }

    private var domain: String? = null
    private var code: String? = null

    /**
     * Fields
     */
    val domainFieldText = MutableLiveData<String>()
    val codeFieldText = MutableLiveData<Any>()
    val domainFieldVisibility = MutableLiveData<Int>()
    val notChosenErrorFieldVisibility = MutableLiveData<Int>()

    /**
     * Refresh
     */
    private val refreshBtnClick = MutableLiveData<Unit>()

    fun onRefreshBtnClick() {
        refreshBtnClick.postValue(Unit)
    }

    private fun initMask(): String {
        return "([000]) [000] - [00] - [00]"
    }

    private val _initMask = MutableLiveData<Event<String>>()
    val initMask: LiveData<Event<String>>
        get() = _initMask

    private fun setDomainCode(domain: String?, code: String?) {
        this.domain = domain
        this.code = code
    }

    init {
        _initMask.postValue(Event(initMask()))
    }

    /**
     *
     */
    val callback = object : PhoneCallback {
        override fun initFields(domain: String?, code: String?, phone: String?) {
            this@PhoneViewModel.setDomainCode(
                domain = domain,
                code = code
            )
            _initMask.value = Event("([000]) [000]-[00]-[00]")

            phoneFieldText.postValue(phone ?:"")
        }

        override fun getDomain(): String? {
            return domain
        }

        override fun getCode(): String? {
            return code
        }

        override fun getCodeWithoutSign(): String? {
            return code?.replace("\\D+".toRegex(), "")
        }

        override fun setNumber(number: String?) {
            phoneFieldText.postValue(number)
        }

        override fun getPhone(): String? {
            var phone = phoneFieldText.value?.trim()
            _initMask.value?.peekContent()?.let {
                if (it.isNotEmpty()) {
                    val maskResult = MaskUtils.getResult(it, phoneFieldText.value?.trim() ?: "")
                    phone = maskResult.extractedValue
                }
            }

            return phone
        }

        override fun fieldsOk(): Boolean {
            return this@PhoneViewModel.fieldsOk()
        }

        override fun showFieldErrors() {
            this@PhoneViewModel.showFieldErrors()
        }

        override fun showPhoneFieldError(error: String?) {
            phoneFieldError.postValue(error)
        }

        override fun setEnabled(isEnabled: Boolean) {

        }

        override fun clearPhoneField() {
            phoneFieldText.postValue(null)
        }

        //todo доработать
        override fun setPhoneWithCode(phone: String?) {
            val temp = phone?.replace("\\D+".toRegex(), "")

            if (temp != null) {

                var mask = initMask.value?.peekContent()

                if (mask == null) {
                    mask = "([000]) [000]-[000]"
                }

                if (mask != null) {
                    fun countOccurrences(s: String, ch: Char): Int {
                        return s.filter { it == ch }.count()
                    }

                    val count = countOccurrences(mask, '0')
                    val result = temp.length - count

                    phoneFieldText.value = if (result > 0) {
                        temp.substring(result)
                    } else {
                        temp
                    }
                }

            } else {
                phoneFieldText.value = null
            }
        }

    }

    private fun fieldsOk(): Boolean {
        val phoneNotEmpty = !phoneFieldText.value.isNullOrBlank()
        val phoneValid = isPhoneValid()

//        val domainNotNull = domain != null
//        val codeNotNull = code != null

        return (phoneNotEmpty
                && phoneValid
//                && domainNotNull
//                && codeNotNull
                )
    }

    private fun showFieldErrors() {
        val phoneNotEmpty = !phoneFieldText.value.isNullOrBlank()
        val phoneValid = isPhoneValid()

        val domainNotNull = domain != null
        val codeNotNull = code != null

        if (!phoneNotEmpty) {
            phoneFieldError.postValue(R.string.field_error_empty)

        } else if (!phoneValid) {
            phoneFieldError.postValue(R.string.field_error_invalid_phone)

        }

        if (!codeNotNull
            || !domainNotNull
        ) {
            notChosenErrorFieldVisibility.postValue(View.VISIBLE)
        }
    }

    private fun isPhoneValid(): Boolean {
        var phoneValid = true

        _initMask.value?.peekContent()?.let {
            if (it.isNotEmpty()) {
                val maskResult = MaskUtils.getResult(it, phoneFieldText.value ?: "")
                phoneValid = maskResult.complete
            }
        }

        return phoneValid
    }
}