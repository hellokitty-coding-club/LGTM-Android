package com.lgtm.android.main

import android.os.Bundle
import com.lgtm.android.common_ui.adapter.ViewPagerAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.main.chat.ChatFragment
import com.lgtm.android.main.databinding.ActivityMainBinding
import com.lgtm.android.main.home.HomeFragment
import com.lgtm.android.main.setting.MyPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        syncBottomNavWithVp()
        setBottomNaviIconTintColor()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = ViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragmentList =
            listOf(HomeFragment(), ChatFragment(), MyPageFragment())
    }

    private fun syncBottomNavWithVp() {
        binding.vpMain.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> binding.vpMain.setCurrentItem(0, false)
                R.id.menu_chat -> binding.vpMain.setCurrentItem(1, false)
                R.id.menu_my_page -> binding.vpMain.setCurrentItem(2, false)
            }
            return@setOnItemSelectedListener true
        }

    }

    private fun setBottomNaviIconTintColor() {
        binding.bottomNav.itemIconTintList = null
    }

    override fun initializeViewModel() {
        viewModel = null
    }
}