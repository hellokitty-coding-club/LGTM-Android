package com.lgtm.android.common_ui.constant

import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.TechTagVO


enum class TechTag(val techTagVO: TechTagVO, val imageIcon: Int) {
    JAVA(TechTagVO.JAVA, R.drawable.ic_tech_java),
    PYTHON(TechTagVO.PYTHON, R.drawable.ic_tech_python),
    JAVASCRIPT(TechTagVO.JAVASCRIPT, R.drawable.ic_tech_javascript),
    ANDROID(TechTagVO.ANDROID, R.drawable.ic_tech_android),
    SWIFT(TechTagVO.SWIFT, R.drawable.ic_tech_swift),
    KOTLIN(TechTagVO.KOTLIN, R.drawable.ic_tech_kotlin),
    IOS(TechTagVO.IOS, R.drawable.ic_tech_ios),
    GO(TechTagVO.GO, R.drawable.ic_tech_go),
    TYPESCRIPT(TechTagVO.TYPESCRIPT, R.drawable.ic_tech_ts),
    C_CPP(TechTagVO.C_CPP, R.drawable.ic_tech_c),
    AI(TechTagVO.AI, R.drawable.ic_tech_ai),
    GIT_GITHUB(TechTagVO.GIT_GITHUB, R.drawable.ic_tech_github),
    FRONTEND(TechTagVO.FRONTEND, R.drawable.ic_tech_frontend),
    BACKEND(TechTagVO.BACKEND, R.drawable.ic_tech_backend);
}
