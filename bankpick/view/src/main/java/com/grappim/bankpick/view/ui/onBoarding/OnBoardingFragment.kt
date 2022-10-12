package com.grappim.bankpick.view.ui.onBoarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.grappim.bankpick.common.ui.root.RootActivityViewModel
import com.grappim.bankpick.view.R
import com.grappim.bankpick.view.databinding.FragmentOnBoardingBinding
import com.grappim.bankpick.view.utils.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val viewBinding by viewBinding(FragmentOnBoardingBinding::bind)
    private val rootViewModel by activityViewModels<RootActivityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (rootViewModel.showOnBoarding.not()) {
            findNavController().navigate(R.id.authGraph)
        } else {
            val onBoardingAdapter = OnBoardingAdapter(viewBinding.viewPager)

            with(viewBinding) {
                viewPager.adapter = onBoardingAdapter
                btnNext.setOnClickListener {
                    if (viewPager.currentItem == onBoardingAdapter.itemCount - 1) {
                        rootViewModel.setShowOnBoarding(false)
                        findNavController().navigate(R.id.fromOnBoardingToSignIn)
                    } else {
                        viewPager.currentItem = viewPager.currentItem + 1
                    }
                }
            }
        }
    }
}