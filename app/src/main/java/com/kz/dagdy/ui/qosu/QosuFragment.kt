package com.kz.dagdy.ui.qosu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentQosuBinding
import com.kz.dagdy.ui.qarjy.ViewPagerAdapter
import com.kz.dagdy.ui.qosu.adet_qosu.AdetQosuFragment
import com.kz.dagdy.ui.qosu.jazba_qosu.JazbaQosuFragment
import com.kz.dagdy.ui.qosu.qarjy_qosu.QarjyQosuFragment
import com.kz.dagdy.ui_common.base.BaseFragment

class QosuFragment : BaseFragment() {

    private lateinit var binding: FragmentQosuBinding
    private lateinit var viewModel: QosuViewModel

    private val items = arrayListOf(
        QarjyQosuFragment.newInstance(),
        AdetQosuFragment.newInstance(),
        JazbaQosuFragment.newInstance(),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qosu, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(QosuViewModel::class.java)
        binding.viewModel = viewModel

        var pagerAdapter = ViewPagerAdapter(
            childFragmentManager, items = items, titles = arrayListOf(
                getString(R.string.qarjy), getString(R.string.adet), getString(R.string.jazba)
            )
        )

        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)

        initAndObserveViewModel()
    }

    private fun initAndObserveViewModel() {
        viewModel.apply {
            popBackStack.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    findNavController().popBackStack()
                }
            })
        }
    }
}