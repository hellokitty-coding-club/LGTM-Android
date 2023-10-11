package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.UNKNOWN
import com.lgtm.domain.entity.response.PingPongSeniorVO

data class PingPongSeniorDTO(
    val buttonTitle: String?,
    val feedbackId: Int?,
    val githubId: String?,
    val memberId: Int?,
    @SerializedName("missionHistory")
    val missionProcessInfo: MissionProcessInfoDTO?,
    val nickname: String?,
    val status: String?,
    @SerializedName("realName")
    val depositorName: String?,
){
    fun toVO() : PingPongSeniorVO {
        return PingPongSeniorVO(
            buttonTitle = buttonTitle ?: UNKNOWN,
            feedbackId = feedbackId,
            githubId = githubId ?: UNKNOWN,
            memberId = memberId ?: throw NullPointerException("memberId is null"),
            missionProcessInfo = missionProcessInfo?.toVO() ?: throw NullPointerException("missionHistory is null"),
            nickname = nickname ?: UNKNOWN,
            processState = ProcessState.getProcessState(status),
            depositorName = depositorName
        )
    }
}