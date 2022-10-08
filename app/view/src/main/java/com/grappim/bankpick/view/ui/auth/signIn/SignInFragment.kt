package com.grappim.bankpick.view.ui.auth.signIn

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.bankpick.view.R
import com.grappim.bankpick.view.databinding.FragmentSignInBinding
import com.grappim.bankpick.view.utils.InsetterSetter
import com.grappim.bankpick.view.utils.InsetterSetterDelegate
import com.grappim.bankpick.view.utils.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in),
    InsetterSetter by InsetterSetterDelegate() {

    private val viewBinding by viewBinding(FragmentSignInBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInsetterForSystemBars(viewBinding.root)
        setInsetterForNavigationBarsAndIme(viewBinding.scrollView, true)

        with(viewBinding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.fromSignInToHomeRoot)
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.fromSignInToSignUp)
            }
            etPassword.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        findNavController().navigate(R.id.fromSignInToHomeRoot)
                        true
                    }
                    else -> false
                }
            }
        }
    }
}