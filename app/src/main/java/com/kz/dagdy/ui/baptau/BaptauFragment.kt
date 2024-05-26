package com.kz.dagdy.ui.baptau

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentBaptauBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class BaptauFragment : BaseFragment() {

    private lateinit var binding: FragmentBaptauBinding
    private lateinit var viewModel: BaptauViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_baptau, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(BaptauViewModel::class.java)
        binding.viewModel = viewModel

    }

}