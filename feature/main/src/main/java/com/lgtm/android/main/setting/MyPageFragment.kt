package com.lgtm.android.main.setting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import com.lgtm.android.common_ui.R.string
import com.lgtm.android.common_ui.R.string.latest_version
import com.lgtm.android.common_ui.R.string.update
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.ui.LgtmConfirmationDialog
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.main.BuildConfig
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentMyPageBinding
import com.lgtm.domain.constants.Role
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val myPageViewModel by viewModels<MyPageViewModel>()
    override fun initializeViewModel() {
        viewModel = myPageViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMyMissionOnClickListener()
        setNotificationSettingClickListener()
        setNoticeOnClickListener()
        setTermsAndPoliciesOnClickListener()
        serviceGuidelines()
        setPrivacyPolicy()
        setLogoutOnClickListener()
        setCsCenterOnClickListener()
        setVersionInfoOnClickListener()
        fetchProfileData()
        setOnProfileClickListener()
        setVersionInfo()
    }

    override fun onResume() {
        super.onResume()
        myPageViewModel.getProfileInfo()
    }

    private fun fetchProfileData() {
        myPageViewModel.profileInfo.observe(viewLifecycleOwner) {
            binding.layoutMyProfile.data = it
        }
    }

    private fun setOnProfileClickListener() {
        binding.clProfileGlance.setOnThrottleClickListener {
            navigateToProfile()
        }
    }


    private fun setMyMissionOnClickListener() {
        binding.btnMyMission.setOnThrottleClickListener {
            // MVP 구현사항 아님
            // 추후 업데이트 시 구현
        }
    }

    private fun setNotificationSettingClickListener() {
        binding.btnNotificationSetting.setOnThrottleClickListener {
            val intent = Intent().apply {
                action = "android.settings.APP_NOTIFICATION_SETTINGS"
                putExtra("android.provider.extra.APP_PACKAGE", requireContext().packageName)
                putExtra("app_uid", requireContext().applicationInfo.uid)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }

    private fun setNoticeOnClickListener() {
        // 공지사항
        binding.btnNotice.setOnThrottleClickListener {
            val url =
                "https://team-hkcc.notion.site/4823db3b781e40fdadd0cf61093c5158?v=3cee323345414e318192b89bfe7dd2d0&pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setTermsAndPoliciesOnClickListener() {
        // 약관 및 정책
        binding.btnTermsAndPolicies.setOnThrottleClickListener {
            val url = "https://team-hkcc.notion.site/c4f56b4e6e1b46e89c494e5b3919ed8c?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun serviceGuidelines() {
        // 서비스 이용 방법
        val role = myPageViewModel.getUserRole()
        binding.btnServiceGuidelines.setOnThrottleClickListener {
            val url = when (role) {
                Role.REVIEWER -> "https://www.notion.so/team-hkcc/c1933575315e4b50aedbdd6b39069d3e?pvs=4"
                Role.REVIEWEE -> "https://www.notion.so/team-hkcc/5abf8b03763a4f0d90cb2d463f6d46b4?pvs=4"
            }
            openUrlInBrowser(url)
        }
    }

    private fun setPrivacyPolicy() {
        // 개인정보 처리방침
        binding.btnPrivacyPolicy.setOnThrottleClickListener {
            val url = "https://team-hkcc.notion.site/31a6b7a98d1f4d148bb05cc826d0c9aa?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setLogoutOnClickListener() {
        binding.btnLogout.setOnThrottleClickListener { showLgtmDialog() }
    }

    private fun logout() {
        myPageViewModel.clearUserData()
        moveToSignInActivity()
    }

    private fun showLgtmDialog() {
        val dialog = LgtmConfirmationDialog(
            title = this.getString(string.want_to_logout),
            doAfterConfirm = ::logout,
            confirmBtnBackground = LgtmConfirmationDialog.ConfirmButtonBackground.GRAY
        )
        dialog.show(requireActivity().supportFragmentManager, this.javaClass.name)
    }


    private fun setCsCenterOnClickListener() {
        binding.btnCustomerCenter.setOnThrottleClickListener {
            onPressCsCenter()
        }
    }

    private fun setVersionInfoOnClickListener() {
        binding.btnVersionInfo.setOnThrottleClickListener {
            val playStoreUrl = "https://play.google.com/store/apps/details?id=com.lgtm.android"
            openUrlInBrowser(playStoreUrl)
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun onPressCsCenter() {
        // check whether sending email valid
        val intent = Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:hkcc.swm.official@gmail.com")
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null)
            startActivity(intent)
        else copyEmailToClipboard()

    }

    private fun copyEmailToClipboard() {
        val clipboard: ClipboardManager =
            requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("lgtm_label", "hkcc.swm.official@gmail.com")
        clipboard.setPrimaryClip(clip)
        Toast.makeText(requireContext(), "메일 주소가 복사되었습니다", Toast.LENGTH_SHORT).show()
    }


    private fun navigateToProfile() {
        lgtmNavigator.navigateToProfile(requireContext())
    }

    private fun setVersionInfo() {
        val currentVersion = getString(string.current_version) + " " + BuildConfig.versionName
        binding.tvCurrentVersion.text = currentVersion
        setVersionStatus()
    }

    private fun setVersionStatus() {
        val appUpdateManager = AppUpdateManagerFactory.create(requireContext())
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                binding.tvVersionInfo.text = getString(update)
            } else {
                binding.tvVersionInfo.text = getString(latest_version)
            }
        }.addOnFailureListener { _ ->
            binding.tvVersionInfo.text = getString(string.unchecked)
        }
    }
}