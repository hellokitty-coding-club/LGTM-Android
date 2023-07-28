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

    private val backgroundStateList = getColorStateList(R.color.midnight, R.color.white)
    private val textStateList = getColorStateList(R.color.green, R.color.black)
    private val strokeStateList = getColorStateList(R.color.green, R.color.white)

    fun setChipGroup(selectedTagList: MutableLiveData<MutableList<String>>) {
        this.selectedTagList = selectedTagList

        TechTag.values().forEach { tag ->
            val chip = createChip(tag)
            chipGroup.addView(chip)
        }
    }


    private fun createChip(techTag: TechTag): Chip {
        return Chip(chipGroup.context).apply {
            text = techTag.techTagVO.stack
            this.chipStartPadding = 35F
            this.chipEndPadding = 35F
            this.chipStrokeColor = strokeStateList
            this.chipStrokeWidth = 5F
            this.chipBackgroundColor = backgroundStateList
            this.setChipIconResource(techTag.imageIcon)
            this.setTextAppearance(R.style.Body2)
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
    }

    private fun getColorIntArray(selectedColor: Int, unSelectedColor: Int): IntArray {
        val context = chipGroup.context
        return intArrayOf(getColor(context, selectedColor), getColor(context, unSelectedColor))
    }

    private fun getColorStateList(selectedColor: Int, unSelectedColor: Int): ColorStateList {
        val colorIntArray = getColorIntArray(selectedColor, unSelectedColor)
        return ColorStateList(states, colorIntArray)
    }
}