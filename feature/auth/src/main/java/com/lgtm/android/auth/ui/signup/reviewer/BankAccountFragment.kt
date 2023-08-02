package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentBankAccountBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.R.layout
import com.lgtm.android.common_ui.adapter.BankSpinnerAdapter
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.constant.Bank

class BankAccountFragment :
    BaseFragment<FragmentBankAccountBinding>(R.layout.fragment_bank_account) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        onAccountNumberChanged()
        setupBankSpinner()
        onBankSelectedListener()
        setupCompleteButtonListener()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun onAccountNumberChanged() {
        binding.etBankAccount.addTextChangedListener {
            signUpViewModel.setAccountNumber(it.toString())
            signUpViewModel.setIsAccountInfoValid()
        }
    }

    private fun setupBankSpinner() {
        val bankAdapter =
            BankSpinnerAdapter(requireContext(), layout.item_bank_spinner, signUpViewModel.bankList)
        binding.spBank.adapter = bankAdapter
    }

    private fun onBankSelectedListener() {
        binding.spBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (position == 0) return // Pos 0 Indicate Hint
                signUpViewModel.setSelectedBank(signUpViewModel.bankList[position] as Bank)
                signUpViewModel.setIsAccountInfoValid()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupCompleteButtonListener() {
        binding.btnComplete.setOnClickListener {
            signUpViewModel.signUpSenior()
            navigateToHomeActivity()
        }
    }

    private fun navigateToHomeActivity() {
        // todo : navigate to home activity
    }

}