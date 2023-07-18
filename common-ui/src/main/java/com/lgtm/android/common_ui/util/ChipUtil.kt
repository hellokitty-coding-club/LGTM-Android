package com.lgtm.android.common_ui.util

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.get
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.TechTag

class ChipUtil(
    private val view: ChipGroup
) {
    private val states = arrayOf(
        intArrayOf(android.R.attr.state_selected),
        intArrayOf(-android.R.attr.state_selected)
    )
    private val backgroundColors =
        intArrayOf(getColor(view.context, R.color.midnight), getColor(view.context, R.color.white))
    private val textColors =
        intArrayOf(getColor(view.context, R.color.green), getColor(view.context, R.color.black))
    private val strokeColors =
        intArrayOf(getColor(view.context, R.color.green), getColor(view.context, R.color.white))
    private val backgroundStateList = ColorStateList(states, backgroundColors)
    private val textStateList = ColorStateList(states, textColors)
    private val strokeStateList = ColorStateList(states, strokeColors)

    init {
        for (i in 0 until TechTag.values().size) {
            view.addView(
                Chip(view.context).apply {
                    this.text = TechTag.values()[i].tag
                    this.chipStartPadding = 35F
                    this.chipStrokeColor = strokeStateList
                    this.chipStrokeWidth = 10F
                    this.chipEndPadding = 35F
                    this.chipBackgroundColor = backgroundStateList
                    this.setChipIconResource(TechTag.values()[i].imageIcon)
                    this.setTextAppearance(R.style.EngNumBody2)
                    this.setTextColor(textStateList) // (순서 중요) textAppearance 후에 배치
                    this.setOnClickListener {
                        it.isSelected = !it.isSelected
                    }
                }
            )
        }
    }

    fun getSelectedTagList(): List<String> {
        val selectedTagList = mutableListOf<String>()
        for (i in 0 until TechTag.values().size) {
            this.view[i].isSelected = false
            if (this.view[i].isSelected) {
                selectedTagList.add(view[i].tag.toString())
            }
        }
        return selectedTagList
    }
}