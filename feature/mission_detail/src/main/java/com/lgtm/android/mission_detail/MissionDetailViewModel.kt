package com.lgtm.android.mission_detail

import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.repository.MissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MissionDetailViewModel @Inject constructor(
    private val missionRepository: MissionRepository
) : BaseViewModel()