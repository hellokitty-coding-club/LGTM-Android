package com.lgtm.android.common_ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.lgtm.android.common_ui.navigator.FakeLgtmNavigator
import com.lgtm.android.common_ui.navigator.LgtmInjector
import dagger.hilt.android.EntryPointAccessors

abstract class BaseComposeActivity: ComponentActivity() {
    var viewModel: BaseViewModel? = null

    val lgtmNavigator: FakeLgtmNavigator by lazy {
        EntryPointAccessors.fromActivity(
            this, LgtmInjector.LgtmNavigatorInjector::class.java
        ).lgtmNavigator()
    }

    abstract fun initializeViewModel()

    @Composable
    abstract fun content()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
        setContent {
            content()
        }
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