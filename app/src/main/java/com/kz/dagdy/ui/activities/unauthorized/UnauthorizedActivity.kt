package com.kz.dagdy.ui.activities.unauthorized

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kz.dagdy.R
import com.kz.dagdy.databinding.ActivityUnauthorizedBinding
import com.kz.dagdy.ui_common.base.BaseActivity

class UnauthorizedActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context?) = Intent(context, UnauthorizedActivity::class.java)
    }

    private lateinit var binding: ActivityUnauthorizedBinding
    private lateinit var viewModel: UnauthorizedViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_unauthorized)
        binding.lifecycleOwner = this

        initAndObserveViewModel()
        binding.viewModel = viewModel
        initView()
    }

    private fun initAndObserveViewModel() {
        viewModel = getViewModel(UnauthorizedViewModel::class.java)
    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)
        initOnDestinationChangedListener()
    }

    private fun initOnDestinationChangedListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                else -> {
                    //do nothing
                }
            }
        }
    }
}