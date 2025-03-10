package com.lichaohui.styleSkin.theme

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

//# 直接写死的颜色 不处理
//?2130903258 ?colorPrimary 这样的 解析主题，找到id，再去找资源名称和类型
//@2131231208 @color/red 直接就是id，根据id找到资源名称和类型
//Log.i(TAG, "${it.getAttributeName(i)} = ${it.getAttributeValue(i)}")
fun Activity.getThemeColor(attr: Int): Int {
    return getThemeColor(
        this, attr
    )
}

fun Activity.getThemeDrawable(attr: Int): Drawable? {
    return getThemeDrawable(
        this, attr
    )
}

fun Fragment.getThemeColor(attr: Int): Int {
    return getThemeColor(
        requireActivity(), attr
    )
}

fun Fragment.getThemeDrawable(attr: Int): Drawable? {
    return getThemeDrawable(
        requireActivity(), attr
    )
}

fun View.getThemeColor(attr: Int): Int {
    return getThemeColor(
        context, attr
    )
}

fun View.getThemeDrawable(attr: Int): Drawable? {
    return getThemeDrawable(
        context, attr
    )
}


/**
 * 获取主题属性的资源id，方案二
 */
fun getThemeColor(context: Context, attr: Int): Int {
    val typedValue = TypedValue()
    val success = context.theme.resolveAttribute(
        attr,
        typedValue,
        true
    )
    return ContextCompat.getColor(context, typedValue.resourceId)
    //TypedValue详解
    //针对#ffffff 这种制定值，data就位这个值，resourceId为0
    //针对@color/black，data为这个值，resourceId为 R.color.black
    //针对@drawable/XXX,data不能直接用，resourceId为 R.drawable.XXX，type为TypedValue.TYPE_STRING,string字段为文件名

//    return if (success) {
//        if (typedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT
//            && typedValue.type <= TypedValue.TYPE_LAST_COLOR_INT
//        ) {
//            typedValue.data
//        } else {
//            defaultColor
//        }
//    } else {
//        defaultColor
//    }
}

private const val TAG = "ThemeUtils"

fun getThemeDrawable(context: Context, attr: Int): Drawable? {
    val typedValue = TypedValue()
    val success = context.theme.resolveAttribute(
        attr,
        typedValue,
        true
    )
//    Log.d(TAG, "getThemeColor3: success ${typedValue.toString()}")
//    Log.d(TAG, "getThemeColor3: type ${typedValue.type}")
//    Log.d(TAG, "getThemeColor3: string ${typedValue.string}")
    return ContextCompat.getDrawable(context, typedValue.resourceId)

//    Log.d(TAG, "getThemeColor3: bg1 ${R.mipmap.bg1}")
//    Log.d(TAG, "getThemeColor3: bg2 ${R.mipmap.bg2}")

}
