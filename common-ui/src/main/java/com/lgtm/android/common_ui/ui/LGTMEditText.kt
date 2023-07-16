package com.lgtm.android.common_ui.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.LayoutLgtmEditTextBinding
import com.lgtm.android.common_ui.model.EditTextData

@SuppressLint("ViewConstructor")
class LGTMEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var editTextData: LiveData<EditTextData>

    fun setEditTextData(editTextData: LiveData<EditTextData>) {
        this.editTextData = editTextData
        binding.editTextData = this.editTextData.value
    }

    private val binding: LayoutLgtmEditTextBinding by lazy {
        LayoutLgtmEditTextBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.layout_lgtm_edit_text, this, false)
        )
    }

    init {
        addView(binding.root)
        binding.editText.addTextChangedListener { showClearButton() }
        observeEditText()
    }

    private fun showClearButton() {
        if (binding.editText.text.isNotEmpty()) {
            binding.ivClear.visibility = VISIBLE
        } else {
            binding.ivClear.visibility = GONE
        }
    }

    private fun observeEditText() {
        binding.editText.addTextChangedListener {
            Log.d(TAG, "observeEditText: $it")
            // LiveData 변경
        }
    }
}

