package com.kz.dagdy.ui.jazba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentJazbaBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class JazbaFragment : BaseFragment() {

    private lateinit var binding: FragmentJazbaBinding
    private lateinit var viewModel: JazbaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jazba, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(JazbaViewModel::class.java)
        binding.viewModel = viewModel

    }

}