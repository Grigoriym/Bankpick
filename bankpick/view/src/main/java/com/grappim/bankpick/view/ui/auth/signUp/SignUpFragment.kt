package com.grappim.bankpick.view.ui.auth.signUp

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.bankpick.view.R
import com.grappim.bankpick.view.databinding.FragmentSignUpBinding
import com.grappim.bankpick.view.utils.InsetterSetter
import com.grappim.bankpick.view.utils.InsetterSetterDelegate
import com.grappim.bankpick.view.utils.delegate.viewBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up),
    InsetterSetter by InsetterSetterDelegate() {

    private val viewBinding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInsetterForSystemBars(viewBinding.root)
        setInsetterForNavigationBarsAndIme(viewBinding.scrollView, true)

        with(viewBinding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSignUp.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSignIn.setOnClickListener {
                findNavController().popBackStack()
            }
            etPassword.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}