package com.lgtm.android.main

import android.os.Bundle
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.main.adapter.MainViewPagerAdapter
import com.lgtm.android.main.chat.ChatFragment
import com.lgtm.android.main.databinding.ActivityMainBinding
import com.lgtm.android.main.home.HomeFragment
import com.lgtm.android.main.setting.SettingFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        syncBottomNavWithVp()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragmentList =
            listOf(HomeFragment(), ChatFragment(), SettingFragment())
    }

    private fun syncBottomNavWithVp() {
        binding.vpMain.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> binding.vpMain.setCurrentItem(0, false)
                R.id.menu_chat -> binding.vpMain.setCurrentItem(1, false)
                R.id.menu_setting -> binding.vpMain.setCurrentItem(2, false)
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun initializeViewModel() {
        viewModel = null
    }
}