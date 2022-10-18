package com.hyy.htool.utils

import android.view.View

/**
 * @Author :  HYY
 * 时间: 2019/5/7
 * 描述:
 */
object XClickUtil {
    /**
     * 最近一次点击的时间
     */
    private var mLastClickTime: Long = 0

    /**
     * 最近一次点击的控件ID
     */
    private var mLastClickViewId = 0

    /**
     * 是否是快速点击
     *
     * @param v  点击的控件
     * @param intervalMillis  时间间期（毫秒）
     * @return  true:是，false:不是
     */
    @JvmStatic
    fun isFastDoubleClick(v: View, intervalMillis: Long): Boolean {
        val viewId = v.id
        val time = System.currentTimeMillis()
        val timeInterval = Math.abs(time - mLastClickTime)
        return if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
            true
        } else {
            mLastClickTime = time
            mLastClickViewId = viewId
            false
        }
    }
}