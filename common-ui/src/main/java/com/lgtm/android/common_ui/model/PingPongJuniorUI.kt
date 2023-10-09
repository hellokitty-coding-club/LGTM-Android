package com.lgtm.android.common_ui.model

import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.entity.response.AccountInfoVO
import com.lgtm.domain.entity.response.TechTagVO

data class PingPongJuniorUI(
    val missionName: String,
    val techTagList: List<TechTagVO>,
    val processStatus: ProcessState,
    val accountInfo: AccountInfoVO,
    val missionHistory: MissionHistoryUI,
    val reviewId: Int?, // 후기 작성이 완료되었을 경우에만 값이 존재함
    val pullRequestUrl: String?, // PR 제출 이후에만 값이 존재함
    val buttonTitle: String
)