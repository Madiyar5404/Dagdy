package com.kz.dagdy.ui.activities.authorized

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kz.dagdy.R
import com.kz.dagdy.databinding.ActivityAuthorizedBinding
import com.kz.dagdy.ui_common.base.BaseActivity

class AuthorizedActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthorizedBinding
    private lateinit var viewModel: AuthorizedViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorized)
        binding.lifecycleOwner = this

        initAndObserveView()
        initView()
    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.viewBottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.adetFragment,
                R.id.qarjyFragment,
                R.id.jazbaFragment,
                R.id.baptauFragment -> {
                    binding.clBottomNavigation.visibility = View.VISIBLE
                    binding.btnAdd.visibility = View.VISIBLE
                }

                else -> {
                    binding.clBottomNavigation.visibility = View.GONE
                    binding.btnAdd.visibility = View.GONE
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.qarjyFragment -> {
                    navController.navigate(R.id.navigation_qosu)
                }

                R.id.adetFragment -> {
                    navController.navigate(R.id.navigation_qosu)
                }

                R.id.jazbaFragment -> {
                    navController.navigate(R.id.navigation_qosu)
                }

                R.id.baptauFragment -> {}
            }
        }
    }

    private fun initAndObserveView() {
        viewModel = getViewModel(AuthorizedViewModel::class.java)
        binding.viewModel = viewModel
    }

    companion object {
        fun getIntent(context: Context?) = Intent(context, AuthorizedActivity::class.java)
    }

}
