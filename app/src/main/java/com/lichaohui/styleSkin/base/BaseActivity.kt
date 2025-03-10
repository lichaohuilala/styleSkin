package com.lichaohui.styleSkin.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lichaohui.styleSkin.AppConfig
import com.lichaohui.styleSkin.R

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity(){
    lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppConfig.instance.themeNightFlag == 1) {
            setTheme(R.style.Theme_Night)
        }
        mBinding = DataBindingUtil.setContentView(this, layoutId)

        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)


}