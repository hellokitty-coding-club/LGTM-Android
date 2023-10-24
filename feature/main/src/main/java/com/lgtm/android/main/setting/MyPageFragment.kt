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
import com.lgtm.android.common_ui.R.string
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.ui.LgtmConfirmationDialog
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentMyPageBinding
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
        binding.clProfileGlance.setOnClickListener {
            navigateToProfile()
        }
    }


    private fun setMyMissionOnClickListener() {
        binding.btnMyMission.setOnClickListener {
            // MVP 구현사항 아님
            // 추후 업데이트 시 구현
        }
    }

    private fun setNotificationSettingClickListener() {
        binding.btnNotificationSetting.setOnClickListener {
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
        binding.btnNotice.setOnClickListener {
            val url =
                "https://team-hkcc.notion.site/4823db3b781e40fdadd0cf61093c5158?v=3cee323345414e318192b89bfe7dd2d0&pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setTermsAndPoliciesOnClickListener() {
        // 약관 및 정책
        binding.btnTermsAndPolicies.setOnClickListener {
            val url = "https://team-hkcc.notion.site/c4f56b4e6e1b46e89c494e5b3919ed8c?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun serviceGuidelines() {
        // 서비스 이용 방법
        binding.btnServiceGuidelines.setOnClickListener {
            val url = "https://team-hkcc.notion.site/efa704ed9464409dae3a9dbe4c4e3777?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setPrivacyPolicy() {
        // 개인정보 처리방침
        binding.btnPrivacyPolicy.setOnClickListener {
            val url = "https://team-hkcc.notion.site/31a6b7a98d1f4d148bb05cc826d0c9aa?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setLogoutOnClickListener() {
        binding.btnLogout.setOnClickListener { showLgtmDialog() }
    }

    private fun logout() {
        myPageViewModel.clearUserData()
        requireContext().cacheDir.deleteRecursively()
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
        binding.btnCustomerCenter.setOnClickListener {
            onPressCsCenter()
        }
    }

    private fun setVersionInfoOnClickListener() {
        binding.btnVersionInfo.setOnClickListener {
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

}