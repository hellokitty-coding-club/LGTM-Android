package com.lgtm.android.main.notification

import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.ActivityNotificationCenterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationCenterActivity :
    BaseActivity<ActivityNotificationCenterBinding>(R.layout.activity_notification_center) {

    private val notificationCenterViewModel by viewModels<NotificationCenterViewModel>()



    override fun initializeViewModel() {
        viewModel = notificationCenterViewModel
    }

}