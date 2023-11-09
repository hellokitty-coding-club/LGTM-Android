package com.lgtm.android.auth.ui

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivitySystemMaintenanceBinding
import com.lgtm.android.common_ui.base.BaseActivity

class SystemMaintenanceActivity :
    BaseActivity<ActivitySystemMaintenanceBinding>(R.layout.activity_system_maintenance) {
    override fun initializeViewModel() {
        viewModel = null
    }

    override fun onResume() {
        super.onResume()

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            val isSystemMaintenance = remoteConfig.getString("systemUnderMaintenanceDescription")
                .replace("\\n", "\n")
            binding.tvDescription.text = isSystemMaintenance
        }
    }
}