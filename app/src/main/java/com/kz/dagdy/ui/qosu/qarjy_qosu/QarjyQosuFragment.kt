package com.kz.dagdy.ui.qosu.qarjy_qosu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentQarjyQosuBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class QarjyQosuFragment : BaseFragment() {

    private lateinit var binding: FragmentQarjyQosuBinding
    private lateinit var viewModel: QarjyQosuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qarjy_qosu, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(QarjyQosuViewModel::class.java)
        binding.viewModel = viewModel

    }

    companion object {
        fun newInstance(): QarjyQosuFragment {
            return QarjyQosuFragment()
        }
    }
}