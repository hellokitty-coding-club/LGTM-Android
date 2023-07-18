package com.lgtm.android.common_ui.util

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.TechTag

class TechTagChipGroup(private val chipGroup: ChipGroup) {
    private lateinit var selectedTagList: MutableLiveData<MutableList<String>>

    private val states = arrayOf(
        intArrayOf(android.R.attr.state_selected),
        intArrayOf(-android.R.attr.state_selected)
    )
    private val backgroundColors =
        intArrayOf(
            getColor(chipGroup.context, R.color.midnight),
            getColor(chipGroup.context, R.color.white)
        )
    private val textColors =
        intArrayOf(
            getColor(chipGroup.context, R.color.green),
            getColor(chipGroup.context, R.color.black)
        )
    private val strokeColors =
        intArrayOf(
            getColor(chipGroup.context, R.color.green),
            getColor(chipGroup.context, R.color.white)
        )
    private val backgroundStateList = ColorStateList(states, backgroundColors)
    private val textStateList = ColorStateList(states, textColors)
    private val strokeStateList = ColorStateList(states, strokeColors)

    fun setChipGroup(selectedTagList: MutableLiveData<MutableList<String>>) {
        this.selectedTagList = selectedTagList

        for (i in 0 until TechTag.values().size) {
            chipGroup.addView(
                Chip(chipGroup.context).apply {
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
                        if (it.isSelected)
                            this@TechTagChipGroup.selectedTagList.value?.add(this.text.toString())
                        else
                            this@TechTagChipGroup.selectedTagList.value?.remove(this.text.toString())
                        this@TechTagChipGroup.selectedTagList.value =
                            this@TechTagChipGroup.selectedTagList.value?.toMutableList()
                    }
                }
            )
        }
    }
}