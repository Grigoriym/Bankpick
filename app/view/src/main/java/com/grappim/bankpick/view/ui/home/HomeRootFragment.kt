package com.grappim.bankpick.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.grappim.bankpick.view.R
import com.grappim.bankpick.view.databinding.FragmentHomeRootBinding
import com.grappim.bankpick.view.utils.InsetterSetter
import com.grappim.bankpick.view.utils.InsetterSetterDelegate
import com.grappim.bankpick.view.utils.delegate.viewBinding

class HomeRootFragment : Fragment(R.layout.fragment_home_root),
    InsetterSetter by InsetterSetterDelegate() {

    private val viewBinding by viewBinding(FragmentHomeRootBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInsetterForSystemBars(viewBinding.root)
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.homeFragmentsContainer) as NavHostFragment
        val navController = navHostFragment.navController
        viewBinding.bottomNavigation.setupWithNavController(navController)
    }
}