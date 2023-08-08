package com.lgtm.android.navigator

import android.content.Context
import android.content.Intent
import com.lgtm.android.auth.ui.SignInActivity
import com.lgtm.android.common_ui.navigator.FakeSignInNavigator
import javax.inject.Inject

class SignInNavigator @Inject constructor(
    private val context: Context
) : FakeSignInNavigator {
    override fun navigateToSignIn() {
        val intent = Intent(context, SignInActivity::class.java)
        context.startActivity(intent)
    }
}
