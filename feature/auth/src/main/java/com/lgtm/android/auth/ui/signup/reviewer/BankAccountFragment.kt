package com.lgtm.android.auth.ui.signup.reviewer

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentBankAccountBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.R.layout
import com.lgtm.android.common_ui.adapter.BankSpinnerAdapter
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.constant.Bank
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme

class BankAccountFragment :
    BaseFragment<FragmentBankAccountBinding>(R.layout.fragment_bank_account) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    private lateinit var bankAdapter : BankSpinnerAdapter
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        onAccountNumberChanged()
        setupBankSpinner()
        setEnteredData()
        onBankSelectedListener()
        setupCompleteButtonListener()
        onAccountHolderChanged()
        observeSignUpStatus()
        shotBankAccountExposureLogging()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setEnteredData(){
        Log.d(TAG, "setEnteredData: ${signUpViewModel.selectedBankIdx.value}")
        binding.spBank.setSelection(signUpViewModel.selectedBankIdx.value ?: 0)
        binding.etBankAccount.setText(signUpViewModel.accountNumber.value)
        binding.etAccountHolder.setText(signUpViewModel.accountHolder.value)
    }

    private fun onAccountNumberChanged() {
        binding.etBankAccount.addTextChangedListener {
            signUpViewModel.setAccountNumber(it.toString())
            signUpViewModel.setIsAccountInfoValid()
        }
    }

    private fun setupBankSpinner() {
        bankAdapter = BankSpinnerAdapter(requireContext(), layout.item_bank_spinner, signUpViewModel.bankList)
        binding.spBank.adapter = bankAdapter
    }

    private fun onBankSelectedListener() {
        binding.spBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (position == 0) return // Pos 0 Indicate Hint
                signUpViewModel.setSelectedBankIdx(position)
                signUpViewModel.setSelectedBank(signUpViewModel.bankList[position] as Bank)
                signUpViewModel.setIsAccountInfoValid()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {/*no-op*/}
        }
    }

    private fun onAccountHolderChanged() {
        binding.etAccountHolder.addTextChangedListener {
            signUpViewModel.setAccountHolder(it.toString())
            signUpViewModel.setIsAccountInfoValid()
        }
    }

    private fun setupCompleteButtonListener() {
        binding.btnComplete.setOnThrottleClickListener {
            signUpViewModel.signUpSenior()
        }
    }

    private fun observeSignUpStatus() {
        signUpViewModel.signUpState.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Init -> {
                    /* no-op */
                }

                is NetworkState.Success -> {
                    navigateToHomeActivity()
                    requireActivity().finish()
                }

                is NetworkState.Failure -> {
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                    signUpViewModel.clearSignUpState()
                }
            }
        }
    }

    private fun navigateToHomeActivity() {
        lgtmNavigator.navigateToMain(requireContext())
    }

    private fun shotBankAccountExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("bankAccountExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 9, "seniorStep" to 4))
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }

}