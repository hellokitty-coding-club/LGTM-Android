package com.lgtm.android.create_mission

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.R.*
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.create_mission.databinding.FragmentCreateMissionStep5Binding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class CreateMissionStep5Fragment :
    BaseFragment<FragmentCreateMissionStep5Binding>(R.layout.fragment_create_mission_step5) {
    private val createMissionViewModel by activityViewModels<CreateMissionViewModel>()

    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupNextButtonClickListener()
        onEditTextClicked()
    }

    private fun onEditTextClicked() {
        binding.etRegistrationDueDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    @SuppressLint("SetTextI18n")
    val onDateClicked = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val formattedMonth = String.format("%02d", month + 1)
        val formattedDay = String.format("%02d", day)
        binding.etRegistrationDueDate.setText("$year.$formattedMonth.$formattedDay")
        //viewmodel
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance() // To Get Today's date
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), style.ThemeOverlay_App_DatePicker, onDateClicked,
            currentYear, currentMonth, currentDay
        )
        datePickerDialog.show()
    }


    private fun setupViewModel() {
        binding.viewModel = createMissionViewModel
    }


    private fun setupNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            (requireActivity() as? CreateMissionActivity)?.onNextButtonClick(this.javaClass)
        }
    }
}