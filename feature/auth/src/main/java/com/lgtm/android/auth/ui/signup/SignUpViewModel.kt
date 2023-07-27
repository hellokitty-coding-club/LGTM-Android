package com.lgtm.android.auth.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lgtm.android.common_ui.constant.Bank
import com.lgtm.android.common_ui.constant.BankHint
import com.lgtm.android.common_ui.constant.BankList
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import com.lgtm.domain.constants.EducationStatus
import com.lgtm.domain.constants.EducationStatus.Companion.getEducationStatus
import com.lgtm.domain.constants.Role
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    /** Github Id */
    private val _githubId = MutableLiveData<String>()
    val githubId: LiveData<String> = _githubId

    fun setGithubId(memberData: String) {
        _githubId.value = memberData
    }

    /** 약관 동의 */
    private val _isAgreeWithTerms = MutableLiveData<Boolean>()
    val isAgreeWithTerms: LiveData<Boolean> = _isAgreeWithTerms

    fun setIsAgreeWithTerms(isAgree: Boolean) {
        _isAgreeWithTerms.value = isAgree
    }

    // 이벤트, 광고성 정보 안내
    private val _isAgreeWithEventInfo = MutableLiveData<Boolean>()
    val isAgreeWithEventInfo: LiveData<Boolean> = _isAgreeWithEventInfo

    fun setIsAgreeWithEventInfo(isAgree: Boolean) {
        _isAgreeWithEventInfo.value = isAgree
    }


    /** 닉네임 */
    val nicknameEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 10,
            hint = "닉네임을 입력해주세요."
        )
    )

    val nickname: LiveData<String> = nicknameEditTextData.value?.text
        ?: throw IllegalArgumentException("nickname cannot be null")

    fun fetchNicknameInfoStatus() {
        val regex = Regex("\\s")
        if (regex.containsMatchIn(nickname.value ?: "")) {
            nicknameEditTextData.value?.infoStatus?.value = InfoType.NO_SPACE
        } else {
            nicknameEditTextData.value?.infoStatus?.value = InfoType.NONE
        }
    }

    private val _isNicknameValid = MutableLiveData<Boolean>()
    val isNicknameValid: LiveData<Boolean> = _isNicknameValid

    fun setIsNicknameValid() {
        _isNicknameValid.value =
            (nicknameEditTextData.value?.infoStatus?.value == InfoType.NONE) && (nickname.value?.isNotBlank() == true)
    }

    /** 기술 태그 목록 */
    val techTagList = MutableLiveData<MutableList<String>>(mutableListOf())

    private val _isTechTagValid = MutableLiveData<Boolean>()
    val isTechTagValid: LiveData<Boolean> = _isTechTagValid

    fun setIsTechTagValid() {
        _isTechTagValid.value = (techTagList.value?.size ?: 0) > 0
    }

    /** 한줄 소개 */
    val introEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 40,
            hint = "내용을 입력하세요"
        )
    )

    val introduction: LiveData<String> = introEditTextData.value?.text
        ?: throw IllegalArgumentException("introduction cannot be null")

    private val _isIntroductionValid = MutableLiveData<Boolean>()
    val isIntroductionValid: LiveData<Boolean> = _isIntroductionValid

    fun setIsIntroductionValid() {
        _isIntroductionValid.value =
            (introEditTextData.value?.infoStatus?.value == InfoType.NONE) && (introEditTextData.value?.text?.value?.isNotBlank() == true)
    }

    fun fetchIntroInfoStatus() {
        if (introduction.value?.isBlank() == true && introduction.value?.isNotEmpty() == true)
            introEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else
            introEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    /** Role 선택 */
    private val _selectedRole = MutableLiveData<Role>()
    val chooseRole: LiveData<Role> = _selectedRole

    private val _isRoleValid = MutableLiveData<Boolean>()
    val isRoleValid: LiveData<Boolean> = _isRoleValid

    fun setIsRoleValid() {
        _isRoleValid.value = chooseRole.value != null
    }

    fun onClickReviewee() {
        _selectedRole.value = Role.REVIEWEE
    }

    fun onClickReviewer() {
        _selectedRole.value = Role.REVIEWER
    }

    /** 학력 정보 */
    private val _educationStatus = MutableLiveData<EducationStatus>()
    val educationStatus: LiveData<EducationStatus> = _educationStatus

    fun setEducationStatus(index: Int) {
        _educationStatus.value = getEducationStatus(index)
    }

    private val _isEducationStatusValid = MutableLiveData<Boolean>()
    val isEducationStatusValid: LiveData<Boolean> = _isEducationStatusValid

    fun setIsEducationStatusValid() {
        _isEducationStatusValid.value = educationStatus.value != null
    }

    /** 실명 */
    val realNameEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 20,
            hint = "실명을 입력하세요."
        )
    )

    val realName: LiveData<String> = realNameEditTextData.value?.text
        ?: throw IllegalArgumentException("realName cannot be null")


    fun fetchRealNameInfoStatus() {
        val regex = Regex("[^a-zA-Z가-힣]")
        if (regex.containsMatchIn(realName.value ?: "")) {
            realNameEditTextData.value?.infoStatus?.value = InfoType.VALID_REAL_NAME
        } else {
            realNameEditTextData.value?.infoStatus?.value = InfoType.NONE
        }
    }

    private val _isRealNameValid = MutableLiveData<Boolean>()
    val isRealNameValid: LiveData<Boolean> = _isRealNameValid

    fun setIsRealNameValid() {
        _isRealNameValid.value = realNameEditTextData.value?.infoStatus?.value == InfoType.NONE
                && realName.value?.isNotBlank() == true
    }

    /** 회사명 */
    val companyNameEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 30,
            hint = "회사명을 입력하세요."
        )
    )

    val companyName: LiveData<String> = companyNameEditTextData.value?.text
        ?: throw IllegalArgumentException("companyName cannot be null")


    fun fetchCompanyNameInfoStatus() {
        if (companyName.value?.isBlank() == true && companyName.value?.isNotEmpty() == true)
            companyNameEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else
            companyNameEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    private val _isCompanyNameValid = MutableLiveData<Boolean>()
    val isCompanyNameValid: LiveData<Boolean> = _isCompanyNameValid

    fun setIsCompanyNameValid() {
        _isCompanyNameValid.value =
            companyNameEditTextData.value?.infoStatus?.value == InfoType.NONE
                    && companyName.value?.isNotBlank() == true
    }

    /** 직책 */
    val positionEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 30,
            hint = "직책을 입력하세요."
        )
    )

    val position: LiveData<String> = positionEditTextData.value?.text
        ?: throw IllegalArgumentException("position cannot be null")


    fun fetchPositionInfoStatus() {
        if (position.value?.isBlank() == true && position.value?.isNotEmpty() == true)
            positionEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else
            positionEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    private val _isPositionValid = MutableLiveData<Boolean>()
    val isPositionValid: LiveData<Boolean> = _isPositionValid

    fun setIsPositionValid() {
        _isPositionValid.value =
            positionEditTextData.value?.infoStatus?.value == InfoType.NONE
                    && position.value?.isNotBlank() == true
    }

    /** 경력 기간 */
    val careerPeriodInfoStatus = MutableLiveData(InfoType.NONE)
    fun fetchCareerPeriodInfoStatus() {
        val careerPeriod = careerPeriod.value
        careerPeriodInfoStatus.value = when {
            careerPeriod == null -> InfoType.NONE
            careerPeriod < 12 -> InfoType.OVER_12_MONTHS_EXPERIENCE_REQUIRED
            else -> InfoType.NONE
        }
    }

    private val _careerPeriod = MutableLiveData<Int>()
    private val careerPeriod: LiveData<Int> = _careerPeriod

    fun setCareerPeriod(month: String) {
        _careerPeriod.value = if (month.isEmpty()) null else Integer.parseInt(month)
    }

    private val _isCareerPeriodValid = MutableLiveData<Boolean>()
    val isCareerPeriodValid: LiveData<Boolean> = _isCareerPeriodValid

    fun setIsCareerPeriodValid() {
        val careerPeriod = careerPeriod.value ?: return
        _isCareerPeriodValid.value = careerPeriod >= 12
    }


    /** 계좌 정보 */
    val bankList: List<BankList> = mutableListOf<BankList>().apply {
        this.add(BankHint("은행을 선택하세요."))
        this.addAll(Bank.getBankList())
    }

    private val _selectedBank = MutableLiveData<Bank>()
    val selectedBank: LiveData<Bank> = _selectedBank

    fun setSelectedBank(bank: Bank) {
        _selectedBank.value = bank
    }

    private val _accountNumber = MutableLiveData<String>()
    val accountNumber: LiveData<String> = _accountNumber

    fun setAccountNumber(number: String) {
        _accountNumber.value = number
    }
}
