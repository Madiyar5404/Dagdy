package com.kz.dagdy.ui.auth.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kz.dagdy.R
import com.kz.dagdy.data.models.network.Status
import com.kz.dagdy.databinding.FragmentRegistrationBinding
import com.kz.dagdy.ui_common.base.BaseFragment
import com.kz.dagdy.ui_common.phone.PhoneViewModel
import com.kz.dagdy.utils.navigation.getSlideLeftAnimBuilder

class RegistrationFragment : BaseFragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var phoneViewModel: PhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(RegistrationViewModel::class.java)
        binding.viewModel = viewModel

        binding.tvBtnToLogin.setOnClickListener {
            viewModel.onLoginBtnClick()
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
                            }

                            Status.ERROR -> {
                                dismissProgressDialog()
                                handleExceptionDialog(it.exception)
                            }
                        }
                    }
                }
            )

            openLogin.observe(
                viewLifecycleOwner,
                Observer {
                    it.getContentIfNotHandled()?.let {
                        findNavController().navigate(
                            R.id.navigation_login,
                            Bundle.EMPTY,
                            getSlideLeftAnimBuilder().build()
                        )
                    }
                }
            )

            popBackStack.observe(
                viewLifecycleOwner,
                Observer {
                    it.getContentIfNotHandled()?.let {
                        findNavController().popBackStack()
                    }
                }
            )

        }
    }

}