package com.lgtm.android.common_ui.constant

import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.TechTagVO


enum class TechTag(val techTagVO: TechTagVO, val imageIcon: Int) {
    JAVA(TechTagVO.JAVA, R.drawable.ic_tech_java),
    PYTHON(TechTagVO.PYTHON, R.drawable.ic_tech_python),
    JAVASCRIPT(TechTagVO.JAVASCRIPT, R.drawable.ic_tech_javascript),
    TENSORFLOW(TechTagVO.TENSORFLOW, R.drawable.ic_tech_tensorflow),
    SPRING(TechTagVO.SPRING, R.drawable.ic_tech_spring),
    REACT(TechTagVO.REACT, R.drawable.ic_tech_react),
    ANDROID(TechTagVO.ANDROID, R.drawable.ic_tech_android),
    NODEJS(TechTagVO.NODEJS, R.drawable.ic_tech_nodejs),
    DJANGO(TechTagVO.DJANGO, R.drawable.ic_tech_django),
    SWIFT(TechTagVO.SWIFT, R.drawable.ic_tech_swift),
    HTML(TechTagVO.HTML, R.drawable.ic_tech_html),
    KOTLIN(TechTagVO.KOTLIN, R.drawable.ic_tech_kotlin),
    IOS(TechTagVO.IOS, R.drawable.ic_tech_ios),
    GO(TechTagVO.GO, R.drawable.ic_tech_go);
}
