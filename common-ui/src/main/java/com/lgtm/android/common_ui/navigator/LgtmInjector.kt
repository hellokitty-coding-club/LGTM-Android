package com.lgtm.android.common_ui.navigator

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

sealed interface LgtmInjector {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface LgtmNavigatorInjector {
        fun lgtmNavigator(): LgtmNavigator
    }
}