package com.lgtm.android.common_ui.util

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.TechTagUI


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
        setChipSpacing()
        TechTagUI.values().forEach { tag ->
            val chip = createChip(tag)
            chipGroup.addView(chip)
        }
    }

    private fun setChipSpacing() {
        chipGroup.apply {
            chipSpacingHorizontal = resources.getDimension(R.dimen.chip_spacing_horizontal).toInt()
            chipSpacingVertical = 0
        }
    }

    private fun createChip(techTagUI: TechTagUI): Chip {
        return Chip(chipGroup.context).apply {
            text = techTagUI.techTag.stack
            chipStartPadding = resources.getDimension(R.dimen.chip_padding_horizontal)
            chipEndPadding = resources.getDimension(R.dimen.chip_padding_horizontal)
            chipMinHeight = resources.getDimension(R.dimen.chip_min_height)
            chipStrokeColor =
                if (theme == TechTagTheme.LIGHT) strokeStateListLight else strokeStateListDark
            chipBackgroundColor = backgroundStateList
            setChipIconResource(techTagUI.defaultIcon)
            setTextAppearance(R.style.Body2)
            setTextColor(textStateList) // (순서 중요) textAppearance 후에 배치
            setOnClickListener { onChipSelected(this, techTagUI) }
            if (selectedTagList.value?.contains(this.text) == true) {
                isSelected = true
            }
        }
    }

    private fun onChipSelected(chip: Chip, techTagUI: TechTagUI) {
        chip.apply {
            isSelected = !isSelected
            if (isSelected) {
                chipStrokeWidth = resources.getDimension(R.dimen.chip_stroke_width_selected)
                this@TechTagChipGroup.selectedTagList.value?.add(this.text.toString())
                techTagUI.selectedIcon?.let { selectedIcon -> this.setChipIconResource(selectedIcon) }
            } else {
                chipStrokeWidth = resources.getDimension(R.dimen.chip_stroke_width_unselected)
                this@TechTagChipGroup.selectedTagList.value?.remove(this.text.toString())
                this.setChipIconResource(techTagUI.defaultIcon)
            }
            this@TechTagChipGroup.selectedTagList.value =
                this@TechTagChipGroup.selectedTagList.value?.toMutableList()
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