package com.kz.dagdy.ui.qarjy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentQarjyBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class QarjyFragment : BaseFragment() {

    private lateinit var binding: FragmentQarjyBinding
    private lateinit var viewModel: QarjyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qarjy, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(QarjyViewModel::class.java)
        binding.viewModel = viewModel

    }

}