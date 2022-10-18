package com.hyy.htool.utils

import android.app.Activity
import android.widget.ImageView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.ImageViewerPopupView
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener
import com.lxj.xpopup.util.SmartGlideImageLoader

/**
 * @Author : HYY
 * 描述:      预览图片
 * 时间:      2022/06/2022/6/7
 * 软件包名:   com.hyy.htool.utils
 */
object HyPreviewImage {
    /**
     * 单张图片预览
     */
    @JvmStatic
    fun ImageUtilsASingle(activity: Activity, imageView: ImageView, url: String) {
        XPopup.Builder(activity).asImageViewer(imageView, url, SmartGlideImageLoader()).show()
    }

    /**
     * 多张图片预览
     */
    @JvmStatic
    fun ImageMore(activity: Activity, imageView: ImageView,list: List<String>) {
        XPopup.Builder(activity).asImageViewer(imageView, 0, list, object : OnSrcViewUpdateListener {
            override fun onSrcViewUpdate(popupView: ImageViewerPopupView, position: Int) {
                popupView.updateSrcView(imageView)
            }
        }, SmartGlideImageLoader()).show()
    }

}