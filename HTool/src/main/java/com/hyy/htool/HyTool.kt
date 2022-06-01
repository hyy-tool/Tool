package com.hyy.htool

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.widget.TextView
import com.stefan.hyyTool.interfaces.OnSimpleListener

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.htool
 */
object HyTool {
    private var context: Context? = null

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    @JvmStatic
    fun init(context: Context): HyTool {
        HyTool.context = context.applicationContext
        return HyTool
    }

    /**
     * 在某种获取不到 Context 的情况下，即可以使用才方法获取 Context
     *
     *
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    @JvmStatic
    fun getContext(): Context {
        if (context != null) {
            return context as Context
        }
        throw NullPointerException("请先调用init()方法")
    }
    /**
     * 倒计时
     *
     * @param textView 控件
     * @param waitTime 倒计时总时长
     * @param interval 倒计时的间隔时间
     * @param hint     倒计时完毕时显示的文字
     */
    @JvmStatic
    fun countDown(textView: TextView, waitTime: Long, interval: Long, hint: String?) {
        textView.isEnabled = false
        val timer: CountDownTimer = object : CountDownTimer(waitTime, interval) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                textView.text = String.format("剩下 %d S", millisUntilFinished / 1000)
            }

            override fun onFinish() {
                textView.isEnabled = true
                textView.text = hint
            }
        }
        timer.start()
    }


    @JvmStatic
    fun delayToDo(delayTime: Long, onSimpleListener: OnSimpleListener) {
        Handler().postDelayed({ //execute the task
            onSimpleListener.doSomething()
        }, delayTime)
    }
}