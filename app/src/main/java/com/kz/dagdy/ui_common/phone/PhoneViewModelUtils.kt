package com.kz.dagdy.ui_common.phone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.kz.dagdy.utils.mask.MaskUtils
import com.redmadrobot.inputmask.MaskedTextChangedListener

//todo refactor
object PhoneViewModelUtils {

    private var maskedTextChangedListener: MaskedTextChangedListener? = null

    fun setMaskedTextChangedListener(maskedTextChangedListener: MaskedTextChangedListener?) {
        PhoneViewModelUtils.maskedTextChangedListener = maskedTextChangedListener
    }

    fun observePhoneViewModel(
        phoneViewModel: PhoneViewModel,
        viewLifecycleOwner: LifecycleOwner,
        fragment: Fragment,
        fragmentManager: FragmentManager,
        etPhone: TextInputEditText,
    ) {
        phoneViewModel.initMask.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let {
                    maskedTextChangedListener?.let {
                        etPhone.removeTextChangedListener(it)
                    }
//                    maskedTextChangedListener = MaskUtils.initMask(it, etPhone)
                }
            }
        )
    }
}