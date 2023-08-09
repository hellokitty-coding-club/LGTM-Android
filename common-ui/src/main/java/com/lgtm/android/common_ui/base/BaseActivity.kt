package com.lgtm.android.common_ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lgtm.android.common_ui.navigator.FakeSignInNavigator
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T
    lateinit var viewModel: BaseViewModel

    @Inject
    lateinit var signInNavigator: FakeSignInNavigator
    abstract fun initializeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    fun moveToSignInActivity() {
        viewModel.moveToSignIn.observe(this) {
            signInNavigator.navigateToSignIn()
            finishAffinity()
        }
    }

    fun makeToast() {
        viewModel.unknownError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}



