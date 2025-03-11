package com.lichaohui.styleSkin.theme

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.blankj.utilcode.util.UtilsTransActivity
import com.lichaohui.styleSkin.AppConfig
import java.lang.ref.WeakReference
private const val TAG = "ThemeController"

class ThemeController {
    companion object {
        var bit: Bitmap? = null
        var act: WeakReference<UtilsTransActivity?>? = null
        var transCreate: Runnable? = null

        val delegate = object : UtilsTransActivity.TransActivityDelegate() {
            override fun onCreateBefore(activity: UtilsTransActivity, savedInstanceState: Bundle?) {
                super.onCreateBefore(activity, savedInstanceState)
                act = WeakReference(activity)
            }

            override fun onCreated(activity: UtilsTransActivity, savedInstanceState: Bundle?) {
                super.onCreated(activity, savedInstanceState)
//                ImmersionBar.with(activity)
//                    .statusBarAlpha(0.0f)
//                    .statusBarDarkFont(true)
//                    .init()
                val room = activity.window.decorView as FrameLayout
                val image = ImageView(activity).apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    layoutParams =
                        FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                        )
                    setImageBitmap(bit)
                }
                activity.apply {
                    room.apply {
                        addView(image)
                        post {
                            transCreate?.run()
                        }
                    }

                }

            }
        }
    }

    private var activity: AppCompatActivity? = null


    fun onResume(activity: AppCompatActivity) {
        this.activity = activity
        Log.d(TAG, "onResume: ")
        if (bit != null) {
            bit = null
            activity.window.decorView.postDelayed({
                act?.get()?.finish()
                act?.clear()
                act = null
            }, 100)

        }
    }

    fun onPause() {
        Log.d(TAG, "onPause: ")
        activity = null
    }

    fun switchTheme() {
        toggleThemeSp()
        Log.d(
            TAG,
            "switchTheme: activity=$activity    themeFlag=${AppConfig.instance.themeNightFlag}"
        )
        activity?.let {
            val decorview = it.window.decorView
            bit = decorview.drawToBitmap()

            UtilsTransActivity.start(delegate)
            transCreate = kotlinx.coroutines.Runnable {
                Log.d(TAG, "switchTheme: Runnable transCreate ${AppConfig.instance.themeNightFlag}")
                it.apply {
                    recreate()
                }
                transCreate = null
            }
        }

    }
    /*闪烁切换主题*/
    fun switchThemeDef() {
        toggleThemeSp()
        Log.d(
            TAG,
            "switchTheme: activity=$activity    themeFlag=${AppConfig.instance.themeNightFlag}"
        )
        activity?.let {
            it.recreate()
        }
    }
    private fun toggleThemeSp() {
        when (AppConfig.instance.themeNightFlag) {
            1 -> {
                AppConfig.instance.themeNightFlag = 0
            }
            else -> {
                AppConfig.instance.themeNightFlag = 1
            }
        }
    }


}