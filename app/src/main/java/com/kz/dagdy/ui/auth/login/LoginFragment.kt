package com.kz.dagdy.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kz.dagdy.R
import com.kz.dagdy.data.models.network.Status
import com.kz.dagdy.databinding.FragmentLoginBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(LoginViewModel::class.java)
        binding.viewModel = viewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.sendUserDataResource.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let {
                    when (it.status) {
                        Status.LOADING -> {
                            showProgressDialog()
                        }
                        Status.SUCCESS -> {
                            dismissProgressDialog()
                            viewModel.onSendUserResourceSuccess(it.data)
                        }
                        Status.ERROR -> {
                            dismissProgressDialog()
                            handleExceptionDialog(it.exception)
                        }
                    }
                }
            }
        )
    }

}