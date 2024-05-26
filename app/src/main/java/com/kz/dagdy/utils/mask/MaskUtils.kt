package com.kz.dagdy.utils.mask

import android.widget.EditText
import com.kz.dagdy.data.enums.app.MaskTypeEnums
import com.kz.dagdy.utils.Logger
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString

object MaskUtils {

    fun initPhoneMask(et: EditText) {
        initMask(
            MaskTypeEnums.PHONE.id,
            et
        )
    }

    private fun initMask(maskFormat: String, et: EditText) {
        val listener = getMaskedTextChangedListener(maskFormat, et)
        et.addTextChangedListener(listener)
//        et.onFocusChangeListener = listener
        et.hint = listener.placeholder()
    }

    private fun getMaskedTextChangedListener(
        maskFormat: String,
        editText: EditText
    ): MaskedTextChangedListener {
//        return CustomMaskedTextChangedListener(
        return MaskedTextChangedListener(
            maskFormat,
            true,
            editText,
            null,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    Logger.e(javaClass, extractedValue)
                    Logger.e(javaClass, maskFilled.toString())
                }
            }
        )
    }

    fun getResult(maskFormat: String, input: String): Mask.Result {
        val mask = Mask(maskFormat)
        return mask.apply(
            CaretString(
                input,
                input.length
            ),
            true // you may consider disabling autocompletion for your case
        )
    }

}