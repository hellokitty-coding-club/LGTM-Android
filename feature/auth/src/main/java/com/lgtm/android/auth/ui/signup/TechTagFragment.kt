package com.lgtm.android.auth.ui.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTechTagBinding
import com.lgtm.android.common_ui.R.color
import com.lgtm.android.common_ui.R.style
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.constant.TechTag
import com.lgtm.android.common_ui.util.getColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechTagFragment : BaseFragment<FragmentTechTagBinding>(R.layout.fragment_tech_tag) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setChips()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setChips() {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected),
            intArrayOf(-android.R.attr.state_selected)
        )
        val backgroundColors = intArrayOf(getColor(color.midnight), getColor(color.white))
        val textColors = intArrayOf(getColor(color.green), getColor(color.black))
        val strokeColors = intArrayOf(getColor(color.green), getColor(color.white))
        val backgroundStateList = ColorStateList(states, backgroundColors)
        val textStateList = ColorStateList(states, textColors)
        val strokeStateList = ColorStateList(states, strokeColors)

        for (i in 0 until TechTag.values().size) {
            binding.chipTechTag.addView(
                Chip(requireContext()).apply {
                    this.text = TechTag.values()[i].tag
                    this.chipStartPadding = 35F
                    this.chipStrokeColor = strokeStateList
                    this.chipStrokeWidth = 10F
                    this.chipEndPadding = 35F
                    this.chipBackgroundColor = backgroundStateList
                    this.setChipIconResource(TechTag.values()[i].imageIcon)
                    this.setTextAppearance(style.EngNumBody2)
                    this.setTextColor(textStateList) // (순서 중요) textAppearance 후에 배치
                    this.setOnClickListener {
                        it.isSelected = !it.isSelected
                    }
                }
            )
        }
    }
}