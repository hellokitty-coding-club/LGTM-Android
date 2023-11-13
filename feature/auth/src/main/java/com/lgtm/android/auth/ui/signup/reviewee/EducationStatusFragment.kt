package com.lgtm.android.auth.ui.signup.reviewee

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentEducationStatusBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.R.color
import com.lgtm.android.common_ui.R.dimen
import com.lgtm.android.common_ui.R.drawable
import com.lgtm.android.common_ui.R.style
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.constants.EducationStatus
import com.lgtm.domain.logging.SwmCommonLoggingScheme


class EducationStatusFragment :
    BaseFragment<FragmentEducationStatusBinding>(R.layout.fragment_education_status) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRadioButtons()
        setupViewModel()
        fetchEducationStatus()
        setupNextButtonListener()
        shotEducationStatusExposureLogging()
    }

    private fun setupRadioButtons() {
        val radioGroup = binding.rgEduStatus

        EducationStatus.values().forEach { educationStatus ->
            val radioButton = RadioButton(requireContext()).apply {
                id = educationStatus.ordinal
                text = educationStatus.status
                setTextAppearance(style.Body1B)
                textSize =
                    resources.getDimension(dimen.body1_b_text_size) / resources.displayMetrics.density
                setTextColor(
                    resources.getColorStateList(
                        color.selector_if_checked_green_black,
                        null
                    )
                )
                setBackgroundResource(drawable.selector_rect_gray_8_white)
                buttonDrawable = null
                gravity = Gravity.CENTER
                includeFontPadding = false
                setPadding(
                    0, resources.getDimensionPixelSize(dimen.radio_button_padding_vertical),
                    0, resources.getDimensionPixelSize(dimen.radio_button_padding_vertical)
                )
                val layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT
                )
                if (educationStatus.ordinal > 0)
                    layoutParams.topMargin =
                        resources.getDimensionPixelSize(dimen.radio_button_margin_top)

                this.layoutParams = layoutParams
                this.setOnThrottleClickListener { signUpViewModel.setEducationStatus(educationStatus.ordinal) }
            }
            radioGroup.addView(radioButton)
        }
    }

    private fun fetchEducationStatus() {
        signUpViewModel.educationStatus.observe(viewLifecycleOwner) {
            signUpViewModel.setIsEducationStatusValid()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            navigateToRealNameActivity()
        }
    }

    private fun navigateToRealNameActivity() {
        findNavController().navigate(R.id.action_educationStatusFragment_to_realNameFragment)
    }

    private fun shotEducationStatusExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("educationStatusExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 5, "juniorStep" to 1))
            .build()
        viewModel.shotSwmLogging(scheme)
    }
}