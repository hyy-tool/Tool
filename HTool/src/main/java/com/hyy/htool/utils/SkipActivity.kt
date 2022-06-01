package com.hyy.htool.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.hyy.htool.HyConstTool
import com.hyy.htool.R


/**
 * @Author : HYY
 * 描述:      Activity跳转操作类
 * 时间:      2022/05/2022/5/11
 * 软件包名:   com.stefan.hyyTool.Utils
 */
object SkipActivity {
    /**
     * activity跳转
     */
    fun startActivity(clazz: Class<*>?, isFinish: Boolean, activity: Activity) {
        val intent = Intent(activity, clazz)
        activity.startActivity(intent)
        when (HyConstTool.ANIMATION) {
            "ZOOM_OUT" -> {
                activity.overridePendingTransition(R.anim.skip_amplification_one, R.anim.skip_amplification_two)
            }
            "STAGGERED_AND_DOWN" -> {
                activity.overridePendingTransition(R.anim.skip_staggered_and_down_one, R.anim.skip_staggered_and_down_two)
            }
            "RIGHT_GET_IN_AND_OUT" -> {
                activity.overridePendingTransition(R.anim.skip_right_enter_one, R.anim.skip_right_enter_two)
            }
            "SKIP_NARROW" -> {
                activity.overridePendingTransition(R.anim.skip_narrow_one, R.anim.skip_narrow_two)
            }
        }
        if(isFinish) {
            activity.finish()
        }
    }

    /**
     * fragment跳转
     */

    fun startFragmentActivity(clazz: Class<*>?, isFinish: Boolean, activity: FragmentActivity?) {
        val intent = Intent(activity, clazz)
        activity?.startActivity(intent)
        when (HyConstTool.ANIMATION) {
            "ZOOM_OUT" -> {
                activity?.overridePendingTransition(R.anim.skip_amplification_one, R.anim.skip_amplification_two)
            }
            "STAGGERED_AND_DOWN" -> {
                activity?.overridePendingTransition(R.anim.skip_staggered_and_down_one, R.anim.skip_staggered_and_down_two)
            }
            "RIGHT_GET_IN_AND_OUT" -> {
                activity?.overridePendingTransition(R.anim.skip_right_enter_one, R.anim.skip_right_enter_two)
            }
            "SKIP_NARROW" -> {
                activity?.overridePendingTransition(R.anim.skip_narrow_one, R.anim.skip_narrow_two)
            }
        }

        if(isFinish) {
            activity?.finish()
        }
    }

    /**
     * 关闭当前 Activity
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        when (HyConstTool.ANIMATION) {
            "ZOOM_OUT" -> {
                activity.overridePendingTransition(R.anim.skip_amplification_one, R.anim.skip_amplification_two)
            }
            "STAGGERED_AND_DOWN" -> {
                activity.overridePendingTransition(R.anim.skip_staggered_and_down_one, R.anim.skip_staggered_and_down_two)
            }
            "RIGHT_GET_IN_AND_OUT" -> {
                activity.overridePendingTransition(R.anim.skip_right_enter_one, R.anim.skip_right_enter_two)
            }
            "SKIP_NARROW" -> {
                activity.overridePendingTransition(R.anim.skip_narrow_one, R.anim.skip_narrow_two)
            }
        }
    }
}