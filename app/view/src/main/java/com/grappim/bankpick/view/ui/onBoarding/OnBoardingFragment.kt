package com.grappim.bankpick.view.ui.onBoarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.bankpick.view.R
import com.grappim.bankpick.view.databinding.FragmentOnBoardingBinding
import com.grappim.bankpick.view.utils.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val viewBinding by viewBinding(FragmentOnBoardingBinding::bind)
    private val onBoardingAdapter by lazy {
        OnBoardingAdapter(viewBinding.viewPager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            viewPager.adapter = onBoardingAdapter
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.fromOnBoardingToSignIn)
            }
        }
    }
}