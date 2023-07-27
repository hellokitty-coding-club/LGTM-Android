package com.lgtm.android.common_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.Bank
import com.lgtm.android.common_ui.databinding.ItemBankSpinnerBinding

class BankSpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val values: List<Bank>
) : ArrayAdapter<Bank>(context, resId, values) {

    override fun getCount() = values.size

    override fun getItem(position: Int) = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val binding: ItemBankSpinnerBinding = if (convertView == null) {
            DataBindingUtil.inflate(inflater, R.layout.item_bank_spinner, parent, false)
        } else {
            DataBindingUtil.getBinding(convertView) ?: throw IllegalStateException()
        }
        binding.bank = getItem(position)
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            ItemBankSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = values[position]
        binding.bank = model
        return binding.root
    }

}