package com.kz.dagdy.ui.auth.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentResetPasswordBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class ResetPasswordFragment : BaseFragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(ResetPasswordViewModel::class.java)
        binding.viewModel = viewModel

    }

}