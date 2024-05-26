package com.kz.dagdy.ui.activities.authorized

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kz.dagdy.R
import com.kz.dagdy.databinding.ActivityAuthorizedBinding
import com.kz.dagdy.ui_common.base.BaseActivity

class AuthorizedActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthorizedBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorized)
        binding.lifecycleOwner = this

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
                R.id.baptauFragment
                -> {
                    binding.viewBottomNavigation.visibility = View.VISIBLE
                }

                else -> {
                    binding.viewBottomNavigation.visibility = View.GONE
                }
            }
        }
        binding.viewBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_qarjy -> {
                    navController.navigate(R.id.navigation_qarjy)
                    true
                }

                R.id.navigation_adet -> {
                    navController.navigate(R.id.navigation_adet)
                    true
                }

                R.id.navigation_jazba -> {
                    navController.navigate(R.id.navigation_jazba)
                    true
                }

                R.id.navigation_baptau -> {
                    navController.navigate(R.id.navigation_baptau)
                    true
                }

                else -> false
            }

        }
    }

    companion object {
        fun getIntent(context: Context?) = Intent(context, AuthorizedActivity::class.java)
    }

}