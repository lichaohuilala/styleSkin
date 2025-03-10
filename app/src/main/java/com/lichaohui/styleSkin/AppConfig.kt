package com.lichaohui.styleSkin

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import kotlin.properties.Delegates

private const val key_theme_flag = "key_theme_flag"

private const val TAG = "SettingAppConfig"

class AppConfig private constructor() {

    companion object {
        val instance: AppConfig by lazy(LazyThreadSafetyMode.NONE) { AppConfig() }
    }

    /* themeFlag 0 默认主题 1 夜间模式 */
    var themeNightFlag: Int by Delegates.observable(
        SPUtils.getInstance().getInt(key_theme_flag, 0)
    ) { prop, old, new ->
        LogUtils.dTag(TAG, "autoPlay $old: $new")
        SPUtils.getInstance().put(key_theme_flag, new)
    }


}


