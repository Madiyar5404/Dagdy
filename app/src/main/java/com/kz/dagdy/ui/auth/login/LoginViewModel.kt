package com.kz.dagdy.ui.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.R
import com.kz.dagdy.ui_common.phone.PhoneCallback
import com.kz.dagdy.ui_common.phone.PhoneFieldTextChangedCallback
import com.kz.dagdy.utils.live_data.Event
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {
    private lateinit var phoneCallback: PhoneCallback

    fun setPhoneCallback(phoneCallback: PhoneCallback) {
        this.phoneCallback = phoneCallback
    }

    private val _hideKeyboard = MutableLiveData<Event<Unit>>()
    val hideKeyboard: LiveData<Event<Unit>>
        get() = _hideKeyboard

    private val _openAuthorizedActivity = MutableLiveData<Event<Unit>>()
    val openAuthorizedActivity: LiveData<Event<Unit>>
        get() = _openAuthorizedActivity
    /**
     * CHECK LOGIN
     */
    val phoneFieldTextChangedCallback = object : PhoneFieldTextChangedCallback {

        override fun onPhoneFieldTextChanged(phone: String) {
            //todo
        }

        override fun onPhoneFieldTextCompleted(phone: String) {
            //do nothing
        }

    }

    val passwordFieldText = MutableLiveData<String>()
    val passwordFieldError = MutableLiveData<Any?>()

    fun onPasswordFieldTextChanged() {
        if (!passwordFieldText.value.isNullOrBlank()) {
            passwordFieldError.postValue(null)
        }
    }


    fun onAuthBtnClick() {

        _hideKeyboard.postValue(Event(Unit))

        if(checkFields()){
            _openAuthorizedActivity.postValue(Event(Unit))
        }
    }

    fun checkFields(): Boolean {
        val phoneFieldsOk = phoneCallback.fieldsOk()
        val pwdFieldsNotEmpty = !passwordFieldText.value.isNullOrBlank()

        if(!phoneFieldsOk){
            phoneCallback.showFieldErrors()
        }

        if(!pwdFieldsNotEmpty){
            passwordFieldError.postValue(app.getString(R.string.field_error_empty))
        }

        return phoneFieldsOk && pwdFieldsNotEmpty
    }
}