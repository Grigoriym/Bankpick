package com.grappim.bankpick.view.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.grappim.bankpick.view.databinding.ActivityBankPickViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankPickViewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityBankPickViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBankPickViewBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(viewBinding.root)
    }
}