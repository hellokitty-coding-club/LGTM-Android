package com.lgtm.android.common_ui.constant

import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.TechTag


enum class TechTagUI(val techTag: TechTag, val defaultIcon: Int, val selectedIcon: Int? = null) {
    JAVA(TechTag.JAVA, R.drawable.ic_tech_java),
    PYTHON(TechTag.PYTHON, R.drawable.ic_tech_python),
    JAVASCRIPT(TechTag.JAVASCRIPT, R.drawable.ic_tech_javascript),
    ANDROID(TechTag.ANDROID, R.drawable.ic_tech_android),
    SWIFT(TechTag.SWIFT, R.drawable.ic_tech_swift),
    KOTLIN(TechTag.KOTLIN, R.drawable.ic_tech_kotlin),
    IOS(TechTag.IOS, R.drawable.ic_tech_ios),
    GO(TechTag.GO, R.drawable.ic_tech_go),
    TYPESCRIPT(TechTag.TYPESCRIPT, R.drawable.ic_tech_ts),
    C_CPP(TechTag.C_CPP, R.drawable.ic_tech_c),
    AI(TechTag.AI, R.drawable.ic_tech_ai, R.drawable.ic_tech_ai_selected),
    GIT_GITHUB(TechTag.GIT_GITHUB, R.drawable.ic_tech_github),
    FRONTEND(TechTag.FRONTEND, R.drawable.ic_tech_frontend, R.drawable.ic_tech_frontend_selected),
    BACKEND(TechTag.BACKEND, R.drawable.ic_tech_backend, R.drawable.ic_tech_backend_selected);
}
