package com.lgtm.android.auth.ui.signup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTechTagBinding
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.TechTagChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechTagFragment : BaseFragment<FragmentTechTagBinding>(R.layout.fragment_tech_tag) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setChips()
        observeTechTagList()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setChips() {
        signUpViewModel.techTagList.let {
            TechTagChipGroup(binding.chipTechTag).setChipGroup(it)
        }
    }

    private fun observeTechTagList() {
        signUpViewModel.techTagList.observe(viewLifecycleOwner) {
            Log.d(TAG, "observeTechTagList: $it")
        }
    }
}