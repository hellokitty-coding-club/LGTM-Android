package com.lgtm.android.common_ui.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.databinding.LayoutLgtmEditTextBinding
import com.lgtm.android.common_ui.model.EditTextData

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
    }

    fun setEditTextData(editTextData: EditTextData) {
        this.editTextData = MutableLiveData(editTextData)
        binding.editTextData = this.editTextData.value
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
}