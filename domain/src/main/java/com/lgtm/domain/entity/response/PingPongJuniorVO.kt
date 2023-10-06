package com.lgtm.domain.entity.response

data class PingPongJuniorVO(
    val missionName: String,
    val techTagList: List<TechTagVO>,
    val processStatus: String,
    val accountInfo: AccountInfoVO,
    val missionHistory: List<MissionHistoryVO>,
    val reviewId: Int?, // 후기 작성이 완료되었을 경우에만 값이 존재함
    val pullRequestUrl: String?, // PR 제출 이후에만 값이 존재함
    val buttonTitle: String
)