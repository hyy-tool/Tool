package com.stefan.hyyTool.Utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.util.*

/**
 * @Author :  HYY
 * 时间: 2022/4/6
 * 描述: 获取权限
 */
object HyPermissionsTool {
    @JvmStatic
    fun with(activity: Activity?): Builder {
        return Builder(activity)
    }

    class Builder(private val mActivity: Activity?) {
        private val permissionList: MutableList<String>

        /**
         * 确定是否已授予您特定的权限
         *
         * @param permission 正在检查的权限的名称。
         *
         */
        fun addPermission(permission: String): Builder {
            if (!permissionList.contains(permission)) {
                permissionList.add(permission)
            }
            return this
        }

        fun initPermission(): List<String> {
            val list: MutableList<String> = ArrayList()
            for (permission in permissionList) {
                if (ActivityCompat.checkSelfPermission(mActivity!!.baseContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    list.add(permission)
                }
            }
            if (list.size > 0) {
                ActivityCompat.requestPermissions(mActivity!!, list.toTypedArray(), 1)
            }
            return list
        }

        init {
            permissionList = ArrayList()
        }
    }
}