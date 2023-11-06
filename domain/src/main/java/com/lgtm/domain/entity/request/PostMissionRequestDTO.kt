package com.lgtm.domain.entity.request

data class PostMissionRequestDTO(
    val description: String,
    val maxPeopleNumber: Int,
    val missionRepositoryUrl: String?,
    val notRecommendTo: String?,
    val price: Int,
    val recommendTo: String?,
    val registrationDueDate: String,
    val tagList: List<String>,
    val title: String
)