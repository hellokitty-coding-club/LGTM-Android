package com.lgtm.android.common_ui.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    fun setEditTextData(editTextData: EditTextData) {
        Log.d(TAG, "address receive: $editTextData")
        this.editTextData = MutableLiveData(editTextData)
        binding.editTextData = editTextData
    }

    private val binding: LayoutLgtmEditTextBinding by lazy {
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        DataBindingUtil.inflate(inflater, R.layout.layout_lgtm_edit_text, this, false)
        LayoutLgtmEditTextBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.layout_lgtm_edit_text, this, false)
        )
    }

    init {
        addView(binding.root)
        showClearButton()
        binding.editText.addTextChangedListener { showClearButton() }
        observeEditText()
//        observeEditTextData()
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
            binding.tvCurrentLength.text = "${it?.length ?: 0}"
            binding.clInfo.visibility =
                if (editTextData.value?.infoStatus?.value?.isVisible == true) View.VISIBLE else View.GONE
            editTextData.value?.infoStatus?.value?.icon?.let { icon ->
                binding.icInfo.setImageResource(
                    icon
                )
            }
            editTextData.value?.infoStatus?.value?.message?.let { text ->
                binding.tvInfo.text = text
            }
            editTextData.value?.infoStatus?.value?.color?.let { color ->
                binding.tvInfo.setTextColor(ContextCompat.getColor(this.context, color))
            }
            // LiveData 변경
            Log.d(TAG, "observeEditText: ${binding.editTextData?.infoStatus?.value}")
        }
    }

}
