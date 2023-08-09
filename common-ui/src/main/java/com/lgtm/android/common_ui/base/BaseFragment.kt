package com.lgtm.android.common_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lgtm.android.common_ui.navigator.FakeSignInNavigator
import com.lgtm.android.common_ui.util.KeyboardUtil
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    lateinit var viewModel: BaseViewModel

    @Inject
    lateinit var signInNavigator: FakeSignInNavigator

    abstract fun initializeViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setSoftKeyboard()
        return binding.root
    }

    fun moveToSignInActivity() {
        viewModel.moveToSignIn.observe(this) {
            signInNavigator.navigateToSignIn()
            requireActivity().finishAffinity()
        }
    }

    fun makeToast() {
        viewModel.unknownError.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSoftKeyboard() {
        KeyboardUtil().setUpAsSoftKeyboard(binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
