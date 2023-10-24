package com.lgtm.android.common_ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.DialogLgtmConfirmationBinding

class LgtmConfirmationDialog(
    private val title: String,
    private val description: String? = null,
    private val doAfterConfirm: () -> Unit,
    private val confirmBtnBackground: ConfirmButtonBackground
) : BottomSheetDialogFragment() {
    private var _binding: DialogLgtmConfirmationBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_lgtm_confirmation, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickCancelListener()
        onClickConfirmListener()
        setText()
        setDescriptionVisibility()
        setConfirmButtonBackground()
    }

    private fun setConfirmButtonBackground() {
        binding.btnConfirm.setBackgroundResource(confirmBtnBackground.background)
    }

    private fun setText() {
        binding.title = title
        description.let { binding.description = it }
    }

    private fun setDescriptionVisibility() {
        if (description.isNullOrEmpty()) {
            binding.tvDescription.visibility = View.GONE
        }
    }

    private fun onClickCancelListener() {
        binding.btnCancel.setOnClickListener { dismiss() }
    }

    private fun onClickConfirmListener() {
        binding.btnConfirm.setOnClickListener {
            dismiss()
            doAfterConfirm()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    enum class ConfirmButtonBackground(@DrawableRes val background: Int) {
        GREEN(R.drawable.rectangle_green_radius_10),
        GRAY(R.drawable.rectangle_gray_1_stroke_gray_3_radius_10)
    }
}
