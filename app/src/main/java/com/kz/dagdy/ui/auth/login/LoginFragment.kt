package com.kz.dagdy.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kz.dagdy.R
import com.kz.dagdy.data.models.network.Status
import com.kz.dagdy.databinding.FragmentLoginBinding
import com.kz.dagdy.ui.activities.authorized.AuthorizedActivity
import com.kz.dagdy.ui.activities.unauthorized.UnauthorizedActivity
import com.kz.dagdy.ui_common.base.BaseFragment
import com.kz.dagdy.utils.navigation.getSlideLeftAnimBuilder

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

        binding.tvBtnToRegistraion.setOnClickListener {
            viewModel.onRegisterBtnClick()
        }

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.apply {
            sendUserDataResource.observe(
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
                                viewModel.onSuccess()
                            }

                            Status.ERROR -> {
                                dismissProgressDialog()
                                handleExceptionDialog(it.exception)
                            }
                        }
                    }
                }
            )

            openRegister.observe(
                viewLifecycleOwner,
                Observer {
                    it.getContentIfNotHandled()?.let {
                        findNavController().navigate(
                            R.id.navigation_registration,
                            Bundle.EMPTY,
                            getSlideLeftAnimBuilder().build()
                        )
                    }
                }
            )

            viewModel.openAuthorizedActivity.observe(
                viewLifecycleOwner,
                Observer {
                    it.getContentIfNotHandled()?.let {
                        val intent = AuthorizedActivity.getIntent(context)
                        startActivity(intent)
                        activity?.finish()
                    }
                }
            )
        }
    }
}