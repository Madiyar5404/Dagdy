package com.kz.dagdy.ui.qosu.jazba_qosu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kz.dagdy.R
import com.kz.dagdy.databinding.FragmentJazbaQosuBinding
import com.kz.dagdy.ui_common.base.BaseFragment

class JazbaQosuFragment : BaseFragment() {

    private lateinit var binding: FragmentJazbaQosuBinding
    private lateinit var viewModel: JazbaQosuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jazba_qosu, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(JazbaQosuViewModel::class.java)
        binding.viewModel = viewModel

    }

    companion object {
        fun newInstance(): JazbaQosuFragment {
            return JazbaQosuFragment()
        }
    }
}