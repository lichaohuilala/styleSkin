package com.lichaohui.styleSkin

import android.os.Bundle
import com.lichaohui.styleSkin.base.BaseActivity
import com.lichaohui.styleSkin.databinding.ActivityMainBinding
import com.lichaohui.styleSkin.theme.ThemeController
import com.lichaohui.styleSkin.theme.getThemeColor
import com.lichaohui.styleSkin.theme.getThemeDrawable

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mThemeController = ThemeController()

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.btSwitchTheme.setOnClickListener {
            mThemeController.switchTheme()
        }

        mBinding.ivImage.setImageDrawable(getThemeDrawable(R.attr.theme_d_icon_delete))

        mBinding.ivColor.setBackgroundColor(getThemeColor(R.attr.theme_b100))

    }

    override fun onResume() {
        super.onResume()
        mThemeController.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        mThemeController.onPause()
    }


}

