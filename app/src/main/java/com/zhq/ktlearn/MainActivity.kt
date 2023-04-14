package com.zhq.ktlearn

import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhq.commonlib.base.BaseActivity
import com.zhq.commonlib.utils.ToastUtils.showToast
import com.zhq.ktlearn.databinding.ActivityMainBinding
import com.zhq.ktlearn.ui.activity.viewmodel.MainViewModel
import com.zhq.ktlearn.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAB_MAIN_0: Int = 0;
    val TAB_MAIN_1: Int = 1;
    val TAB_MAIN_2: Int = 2;
    val TAB_MAIN_3: Int = 3;
    val TAB_MAIN_4: Int = 4;
    private lateinit var currentFragment: Fragment

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        initFragment(0)
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationSelectedListener)
        val badge = bottomNavigationView.getOrCreateBadge(R.id.tab_1)
        badge.backgroundColor = Color.RED
        badge.badgeTextColor = Color.WHITE
        badge.number = 99
    }

    private val bottomNavigationSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener()

        { item ->
            when (item.itemId) {
                R.id.tab_1 -> {
                    switchFragment(0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tab_2 -> {
                    switchFragment(1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tab_3 -> {
                    switchFragment(2)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tab_4 -> {
                    switchFragment(3)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tab_5 -> {
                    switchFragment(4)
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }

        }

    private fun initFragment(index: Int) {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        currentFragment = createFragment(index)
        if (!currentFragment.isAdded) {
            fragmentTransaction.add(R.id.fl_container, currentFragment, createFragmentTag(index))
        }
        fragmentTransaction.commitNowAllowingStateLoss()
    }

    fun switchFragment(index: Int) {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(0, 0)
        val tag = createFragmentTag(index)
        var targetFragment = supportFragmentManager.findFragmentByTag(tag)
        if (targetFragment == null) {
            targetFragment = createFragment(index)
            fragmentTransaction.hide(currentFragment)
                .add(R.id.fl_container, targetFragment, createFragmentTag(index))
                .commitNowAllowingStateLoss()
            currentFragment = targetFragment
        } else {
            if (targetFragment != currentFragment) {
                fragmentTransaction.hide(currentFragment).show(targetFragment)
                    .commitNowAllowingStateLoss()
                currentFragment = targetFragment
            }
        }
    }


    private fun createFragment(index: Int): Fragment {
        val fragment = when (index) {
            TAB_MAIN_0 -> HomeFragment()
            TAB_MAIN_1 -> OfficialAccountsFragment()
            TAB_MAIN_2 -> ArchitectureFragment()
            TAB_MAIN_3 -> NavigationContentFragment()
            TAB_MAIN_4 -> MineFragment()
            else -> HomeFragment()
        }
        return fragment;
    }

    private fun createFragmentTag(index: Int): String {
        val s = when (index) {
            TAB_MAIN_0 -> "main"
            TAB_MAIN_1 -> "official_accounts"
            TAB_MAIN_2 -> "Atchitecture"
            TAB_MAIN_3 -> "NavigationContent"
            TAB_MAIN_4 -> "Mine"
            else -> "main"
        }
        return s
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    private var exitTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            "再按一次退出".showToast()
            exitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }
}