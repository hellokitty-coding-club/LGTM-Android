package com.lgtm.android.mission_detail

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_detail.databinding.ActivityMissionDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates


@AndroidEntryPoint
class MissionDetailActivity :
    BaseActivity<ActivityMissionDetailBinding>(R.layout.activity_mission_detail) {
    private val missionDetailViewModel by viewModels<MissionDetailViewModel>()
    private var missionId by Delegates.notNull<Int>()
    override fun initializeViewModel() {
        viewModel = missionDetailViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        getMissionDetail()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        getMissionDetail()
    }

    private fun getExtraData() {
        missionId = intent.getIntExtra(MISSION_ID, defaultValue)
    }

    private fun getMissionDetail() {
        missionDetailViewModel.getMissionDetail(missionId)
    }

    companion object {
        const val MISSION_ID = "mission_id"
        const val defaultValue = -1
    }
}