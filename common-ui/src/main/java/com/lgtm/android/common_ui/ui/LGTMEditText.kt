package com.lgtm.android.common_ui.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.databinding.LayoutLgtmEditTextBinding
import com.lgtm.android.common_ui.model.EditTextData
import com.lgtm.android.common_ui.model.InfoType

@SuppressLint("ViewConstructor")
class LGTMEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var editTextData: LiveData<EditTextData>

    private val binding: LayoutLgtmEditTextBinding by lazy {
        LayoutLgtmEditTextBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        initializeView()
        setListeners()
        observeEditText()
    }

    fun setEditTextData(editTextData: EditTextData) {
        this.editTextData = MutableLiveData(editTextData)
        binding.editTextData = editTextData
    }

    private fun initializeView() {
        addView(binding.root)
        showClearButton()
    }

    private fun setListeners() {
        binding.editText.addTextChangedListener { showClearButton() }
    }

    private fun showClearButton() {
        binding.ivClear.visibility =
            if (binding.editText.text.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun observeEditText() {
        if (::editTextData.isInitialized) return
        binding.editText.addTextChangedListener {
            val infoStatus = editTextData.value?.infoStatus?.value
            updateCurrentLength(it?.length ?: 0)
            updateInfoVisibility(infoStatus)
            updateInfoIcon(infoStatus)
            updateInfoMessage(infoStatus)
            updateInfoTextColor(infoStatus)
        }
    }

    private fun updateCurrentLength(length: Int) {
        binding.tvCurrentLength.text = "$length"
    }

    private fun updateInfoVisibility(infoStatus: InfoType?) {
        binding.clInfo.visibility = if (infoStatus?.isVisible == true) View.VISIBLE else View.GONE
    }

    private fun updateInfoIcon(infoStatus: InfoType?) {
        infoStatus?.icon?.let { icon -> binding.icInfo.setImageResource(icon) }
    }

    private fun updateInfoMessage(infoStatus: InfoType?) {
        infoStatus?.message?.let { text -> binding.tvInfo.text = text }
    }

    private fun updateInfoTextColor(infoStatus: InfoType?) {
        infoStatus?.color?.let { color ->
            binding.tvInfo.setTextColor(ContextCompat.getColor(context, color))
        }
    }
}