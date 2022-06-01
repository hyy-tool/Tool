package com.stefan.hyyTool.Utils

import android.app.Activity
import com.lxj.xpopup.XPopup

/**
 * @Author :  HYY
 * 时间: 2022/4/6
 * 描述: 设置延时效果
 */
object HyLoading {
    /**
     * @activirt 当前activity
     * @show 是否终止
     */
    fun loding(activirt: Activity, show: Boolean) {
        if (show) {
            XPopup.Builder(activirt)
                .asLoading("正在加载中")
                .show()
        } else {
            val popupView = XPopup.Builder(activirt)
                .asLoading("正在加载中")
                .show()
            popupView.delayDismiss(300) //延时消失，有时候消失过快体验可能不好，可以延时一下
        }
    }
}