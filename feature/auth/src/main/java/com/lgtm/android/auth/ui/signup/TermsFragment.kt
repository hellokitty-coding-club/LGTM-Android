package com.lgtm.android.auth.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTermsBinding
import com.lgtm.android.common_ui.base.BaseFragment


class TermsFragment : BaseFragment<FragmentTermsBinding>(R.layout.fragment_terms) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}