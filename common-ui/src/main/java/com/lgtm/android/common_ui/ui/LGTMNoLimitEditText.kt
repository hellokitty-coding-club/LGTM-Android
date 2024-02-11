package com.lgtm.android.common_ui.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.databinding.LayoutLgtmNoLimitEditTextBinding
import com.lgtm.android.common_ui.model.NoLimitEditTextData

@SuppressLint("ViewConstructor")
class LGTMNoLimitEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var lifecycleOwner: LifecycleOwner? = null

    fun setLifecycleOwner(owner: LifecycleOwner) {
        lifecycleOwner = owner
    }

    private val binding: LayoutLgtmNoLimitEditTextBinding by lazy {
        LayoutLgtmNoLimitEditTextBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        initializeView()
    }

    fun bindEditTextData(editTextData: MutableLiveData<NoLimitEditTextData>) {
        binding.editTextData = editTextData.value
        binding.lifecycleOwner = lifecycleOwner
    }

    fun setMaxLine(lines: Int) {
        binding.editText.maxLines = lines
    }

    private fun initializeView() {
        addView(binding.root)
        showClearButton()
    }


    private fun showClearButton() {
        binding.apply {
            editText.addTextChangedListener {
                ivClear.visibility = if (editText.text.isNotEmpty()) View.VISIBLE else View.GONE
            }
        }
    }
}