package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.entity.response.PingPongJuniorVO

data class PingPongJuniorDTO(
    val accountInfo: AccountInfoDTO?,
    val buttonTitle: String?,
    @SerializedName("missionHistory")
    val missionProcessInfo: MissionProcessInfoDTO?,
    val missionName: String?,
    val processStatus: String?,
    val reviewId: Int?,
    val techTagList: List<TechTagDTO>?,
    val pullRequestUrl: String?
) {
    fun toVO(): PingPongJuniorVO {
        return PingPongJuniorVO(
            accountInfo = accountInfo?.toVO(),
            buttonTitle = buttonTitle ?: throw NullPointerException("buttonTitle is null"),
            missionProcessInfo = missionProcessInfo?.toVO()
                ?: throw NullPointerException("missionHistory is null"),
            missionName = missionName ?: throw NullPointerException("missionName is null"),
            processStatus = ProcessState.getProcessState(processStatus),
            reviewId = reviewId,
            techTagList = techTagList?.map { it.toVO() }
                ?: throw NullPointerException("techTagList is null"),
            pullRequestUrl = pullRequestUrl
        )
    }
}