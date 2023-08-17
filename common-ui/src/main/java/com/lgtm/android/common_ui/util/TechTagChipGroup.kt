package com.lgtm.android.common_ui.util

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.TechTag


enum class TechTagTheme {
    LIGHT, DARK
}

class TechTagChipGroup(private val chipGroup: ChipGroup, private val theme: TechTagTheme) {
    private lateinit var selectedTagList: MutableLiveData<MutableList<String>>

    private val states = arrayOf(
        intArrayOf(android.R.attr.state_selected),
        intArrayOf(-android.R.attr.state_selected)
    )

    private val backgroundStateList = getColorStateList(R.color.black, R.color.white)
    private val textStateList = getColorStateList(R.color.green, R.color.black)
    private val strokeStateListLight = getColorStateList(R.color.green, R.color.gray_3)
    private val strokeStateListDark = getColorStateList(R.color.green, R.color.white)

    fun setChipGroup(selectedTagList: MutableLiveData<MutableList<String>>) {
        this.selectedTagList = selectedTagList

        TechTag.values().forEach { tag ->
            val chip = createChip(tag)
            chipGroup.addView(chip)
        }
    }


    private fun createChip(techTag: TechTag): Chip {

        return Chip(chipGroup.context).apply {
            val screenWidth = resources.displayMetrics.widthPixels
            text = techTag.techTagVO.stack
            this.chipStartPadding = screenWidth * 0.035F
            this.chipEndPadding = screenWidth * 0.035F
            this.chipStrokeColor =
                if (theme == TechTagTheme.LIGHT) strokeStateListLight else strokeStateListDark
            this.chipStrokeWidth = screenWidth * 0.005F
            this.chipBackgroundColor = backgroundStateList
            this.setChipIconResource(techTag.defaultIcon)
            this.setTextAppearance(R.style.Body2)
            this.setTextColor(textStateList) // (순서 중요) textAppearance 후에 배치
            this.setOnClickListener {
                it.isSelected = !it.isSelected
                if (it.isSelected) {
                    this@TechTagChipGroup.selectedTagList.value?.add(this.text.toString())
                    techTag.selectedIcon?.let { selectedIcon ->
                        this.setChipIconResource(
                            selectedIcon
                        )
                    }
                } else {
                    this@TechTagChipGroup.selectedTagList.value?.remove(this.text.toString())
                    this.setChipIconResource(techTag.defaultIcon)
                }
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