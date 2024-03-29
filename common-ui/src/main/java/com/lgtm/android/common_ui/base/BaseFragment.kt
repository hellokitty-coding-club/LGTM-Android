package com.lgtm.android.common_ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lgtm.android.common_ui.navigator.LgtmInjector
import com.lgtm.android.common_ui.navigator.LgtmNavigator
import com.lgtm.android.common_ui.util.KeyboardUtil
import dagger.hilt.android.EntryPointAccessors

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    lateinit var viewModel: BaseViewModel

    val lgtmNavigator: LgtmNavigator by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(), LgtmInjector.LgtmNavigatorInjector::class.java
        ).lgtmNavigator()
    }

    abstract fun initializeViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setSoftKeyboard()
        initializeViewModel()
        return binding.root
    }

    fun moveToSignInActivityOnUnauthorized() {
        viewModel.moveToSignIn.observe(this) {
            moveToSignInActivity()
        }
    }

    fun moveToSignInActivity(){
        requireActivity().finishAffinity()
        lgtmNavigator.navigateToSignIn(requireContext())
    }

    fun makeToast() {
        viewModel.unknownError.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSoftKeyboard() {
        KeyboardUtil().setUpAsSoftKeyboard(binding.root)
    }

    fun closeKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
