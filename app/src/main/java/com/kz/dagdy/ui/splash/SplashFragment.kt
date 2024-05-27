package com.kz.dagdy.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentSplashBinding
import com.kz.dagdy.ui.activities.authorized.AuthorizedActivity
import com.kz.dagdy.ui.activities.unauthorized.UnauthorizedActivity
import com.kz.dagdy.ui_common.base.BaseFragment

class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = getViewModel(SplashViewModel::class.java)
        binding.viewModel = viewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.openMain.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let {
                    val intent = UnauthorizedActivity.getIntent(context)
                    startActivity(intent)
                    activity?.finish()
                }
            }
        )
    }
}