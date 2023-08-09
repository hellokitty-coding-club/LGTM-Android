package com.lgtm.android.common_ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lgtm.android.common_ui.navigator.FakeLgtmNavigator
import com.lgtm.android.common_ui.navigator.LgtmInjector
import dagger.hilt.android.EntryPointAccessors

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T
    var viewModel: BaseViewModel? = null

    val lgtmNavigator: FakeLgtmNavigator by lazy {
        EntryPointAccessors.fromActivity(
            this, LgtmInjector.LgtmNavigatorInjector::class.java
        ).lgtmNavigator()
    }

    abstract fun initializeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    fun moveToSignInActivity() {
        viewModel?.moveToSignIn?.observe(this) {
            lgtmNavigator.navigateToSignIn(this)
            finishAffinity()
        }
    }

    fun makeToast() {
        viewModel?.unknownError?.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}



