package com.lgtm.android.main.setting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentMyPageBinding


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun initializeViewModel() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProfileOnClickListener()
        setMyMissionOnClickListener()
        setNotificationSettingClickListener()
        setNoticeOnClickListener()
        setTermsAndPoliciesOnClickListener()
        serviceGuidelines()
        setPrivacyPolicy()
        setLogoutOnClickListener()
        setCsCenterOnClickListener()
        setVersionInfoOnClickListener()
    }

    private fun setProfileOnClickListener() {
        binding.clMyProfile.setOnClickListener {
            // todo move to new intent
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
        binding.btnNotice.setOnClickListener {
            val url = "https://team-hkcc.notion.site/4823db3b781e40fdadd0cf61093c5158?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setTermsAndPoliciesOnClickListener() {
        binding.btnTermsAndPolicies.setOnClickListener {
            val url = "https://team-hkcc.notion.site/efa704ed9464409dae3a9dbe4c4e3777?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun serviceGuidelines() {
        binding.btnServiceGuidelines.setOnClickListener {
            val url = "https://team-hkcc.notion.site/c4f56b4e6e1b46e89c494e5b3919ed8c?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setPrivacyPolicy() {
        binding.btnPrivacyPolicy.setOnClickListener {
            val url = "https://team-hkcc.notion.site/31a6b7a98d1f4d148bb05cc826d0c9aa?pvs=4"
            openUrlInBrowser(url)
        }
    }

    private fun setLogoutOnClickListener() {
        binding.btnLogout.setOnClickListener {
            // dialog 작업
        }
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
}