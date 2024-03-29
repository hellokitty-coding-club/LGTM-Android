package com.lgtm.android.main.notification

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.ItemDecorationUtil
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.ActivityNotificationCenterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationCenterActivity :
    BaseActivity<ActivityNotificationCenterBinding>(R.layout.activity_notification_center) {

    private val notificationCenterViewModel by viewModels<NotificationCenterViewModel>()
    private lateinit var notificationAdapter: NotificationAdapter

    override fun initializeViewModel() {
        viewModel = notificationCenterViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getNotificationList()
        initAdapter()
        observeNotificationList()
        onClickBackButton()
    }

    private fun getNotificationList() {
        notificationCenterViewModel.getNotificationList()
    }

    private fun initAdapter() {
        notificationAdapter = NotificationAdapter()
        binding.rvNotification.adapter = notificationAdapter
    }

    private fun observeNotificationList() {
        notificationCenterViewModel.notificationList.observe(this) {
            if (it.isNullOrEmpty()) binding.clEmpty.visibility = View.VISIBLE
            else {
                binding.clEmpty.visibility = View.GONE
                notificationAdapter.submitList(it)
                addItemDecoration()
            }
        }
    }

    private fun addItemDecoration() {
        val decoration = ItemDecorationUtil.DividerItemDecoration(
            padding = com.lgtm.android.common_ui.R.dimen.base_guideline,
            color = com.lgtm.android.common_ui.R.color.gray_3
        )
        binding.rvNotification.addItemDecoration(decoration)
    }

    private fun onClickBackButton() {
        binding.ivBack.setOnThrottleClickListener { finish() }
    }
}