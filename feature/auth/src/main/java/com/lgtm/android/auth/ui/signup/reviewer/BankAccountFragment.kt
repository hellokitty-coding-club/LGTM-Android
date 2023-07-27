package com.lgtm.android.auth.ui.signup.reviewer

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentBankAccountBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.R.layout
import com.lgtm.android.common_ui.adapter.BankSpinnerAdapter
import com.lgtm.android.common_ui.base.BaseFragment

class BankAccountFragment :
    BaseFragment<FragmentBankAccountBinding>(R.layout.fragment_bank_account) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupBankSpinner()
        onBankSelectedListener()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupBankSpinner() {
        val bankAdapter =
            BankSpinnerAdapter(requireContext(), layout.item_bank_spinner, signUpViewModel.bankList)
        binding.spBank.adapter = bankAdapter
    }

    private fun onBankSelectedListener() {
        binding.spBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                signUpViewModel.setSelectedBank(signUpViewModel.bankList[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG, "onNothingSelected: ")
//                signUpViewModel.setSelectedBank(signUpViewModel.bankList[0])
            }
        }
    }


}