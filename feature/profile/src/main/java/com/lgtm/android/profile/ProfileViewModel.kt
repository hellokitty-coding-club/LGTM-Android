package com.lgtm.android.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.profileViewType.ProfileGlance
import com.lgtm.domain.profile.profileViewType.ProfileImage
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val authRepository: AuthRepository,
) : BaseViewModel() {

    private val _profileInfo = MutableLiveData<List<Profile>>()
    val profileInfo: LiveData<List<Profile>> = _profileInfo

    private val _isMyProfile = MutableLiveData<Boolean>()

    private var userId: Int? = null

    fun isMyProfile(): Boolean {
        return _isMyProfile.value ?: false
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getFirstMissionIdx(): Int {
        return profileUseCase.getFirstMissionIdx()
    }

    fun fetchProfileInfo() {
        viewModelScope.launch(lgtmErrorHandler) {
            profileUseCase.fetchProfileInfo(userId)
                .onSuccess {
                    _profileInfo.postValue(it)
                    _isMyProfile.postValue((it[0] as ProfileImage).isMyProfile)
                    Log.d(TAG, "fetchProfileInfo: $it")
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "fetchProfileInfo: $it")
                }
        }
    }

    fun getGithubProfileUrl(): String {
        val gitId = (profileInfo.value?.get(1) as ProfileGlance).githubId
        return "https://github.com/$gitId"
    }

    fun getReportMessage() =
        "안녕하세요 고객님, LGTM 서비스 이용에 불편을 드려 죄송합니다. 보내주신 내용은 빠른 시일 내로 운영팀에서 처리하겠습니다.\n" +
                "처리 결과는 발신된 이메일 주소로 전달될 예정입니다. 감사합니다.\n\n" +
                "[신고 유형을 선택해주세요]\n" +
                "1. 성적, 폭력적 또는 혐오스러운 내용\n" +
                "2. 욕설 혹은 유해한 내용\n" +
                "3. 기타 부적절한 내용\n" +
                "4. 기타 서비스 이용 불편사항\n" +
                "응답 : \n" +
                "\n\n\n" +
                "[신고 내용]\n" +
                "불편을 겪으신 내용을 적어주세요. 필요시 사진을 첨부해주셔도 좋습니다.\n" +
                "응답 : \n" +
                "\n\n\n" +
                "-------------------------------------------------\n" +
                "아래 내용은 운영진에게 전달되는 정보니 수정하지 말아주세요:)\n\n" +
                "[신고 정보]\n" +
                "1. 신고자 ID : ${authRepository.getMemberId()} \n" +
                "2. 피신고자 닉네임 : ${profileUseCase.getNickname()}\n" +
                "3. 피신고자 Gitub : ${profileUseCase.getGithub()}\n" +
                "4. 피신고자 한 줄 소개 : ${profileUseCase.getIntroduction()}\n"
}

