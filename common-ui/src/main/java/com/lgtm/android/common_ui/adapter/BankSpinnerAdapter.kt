package com.lgtm.android.common_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.Bank
import com.lgtm.android.common_ui.constant.BankHint
import com.lgtm.android.common_ui.constant.BankList
import com.lgtm.android.common_ui.databinding.ItemBankEmptySpinnerBinding
import com.lgtm.android.common_ui.databinding.ItemBankHintSpinnerBinding
import com.lgtm.android.common_ui.databinding.ItemBankSpinnerBinding
import com.lgtm.android.common_ui.util.dpToPx

class BankSpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val values: List<BankList>
) : ArrayAdapter<BankList>(context, resId, values) {

    override fun getCount() = values.size

    override fun getItem(position: Int) = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val binding: ViewDataBinding = when (position) {
            0 -> {
                val bankHintBinding = if (convertView == null)
                    DataBindingUtil.inflate<ItemBankHintSpinnerBinding>(
                        inflater, R.layout.item_bank_hint_spinner, parent, false
                    )
                else DataBindingUtil.getBinding(convertView) ?: throw IllegalStateException()

                bankHintBinding.apply {
                    bankHint = getItem(position) as BankHint
                    tvBankName.setTextAppearance(R.style.Body1B)
                    tvBankName.setTextColor(ContextCompat.getColor(context, R.color.gray_3))
                    val scale: Float = context.resources.displayMetrics.density
                    val paddingStart = dpToPx(10, scale)
                    root.setPadding(paddingStart, 0, 0, 0)
                }
            }

            else -> {
                val bankBinding = if (convertView == null)
                    DataBindingUtil.inflate<ItemBankSpinnerBinding>(
                        inflater, R.layout.item_bank_spinner, parent, false
                    )
                else DataBindingUtil.getBinding(convertView) ?: throw IllegalStateException()


                bankBinding.apply {
                    bank = getItem(position) as Bank
                    tvBankName.setTextAppearance(R.style.Body1B)
                    tvBankName.setTextColor(ContextCompat.getColor(context, R.color.black))
                    root.setPadding(0, 0, 0, 0)
                }
            }
        }
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return when (position) {
            0 ->
                ItemBankEmptySpinnerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).root

            else ->
                ItemBankSpinnerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ).apply { bank = values[position] as? Bank }.root

        }
    }
}
