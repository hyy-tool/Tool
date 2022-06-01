package com.example.toolgather.Utils

import android.content.Context
import android.view.View
import com.example.toolgather.R
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.android.synthetic.main.pickerview_custom_options.view.*
import kotlinx.android.synthetic.main.pickerview_edittext.view.*
import kotlinx.android.synthetic.main.pickerview_edittext.view.tv_finish
import kotlinx.android.synthetic.main.pickerview_edittext.view.tv_title
import kotlinx.android.synthetic.main.pickerview_edittext.view.tv_yes

/**
 * @author Administrator : HYY
 * 日期 :  2020/7/6
 * 备注 :
 */
class XPopupUtils(context: Context, var tiele: String, var content: String, var type: String) : CenterPopupView(context), View.OnClickListener {


    private var onmySelectListener: OnSelectListener? = null
    private var onmySelectListener_v1: OnSelectListener_v1? = null

    override fun getImplLayoutId(): Int {
        if (type == "1") {
            return R.layout.pickerview_custom_options
        } else {
            return R.layout.pickerview_edittext
        }
    }

    override fun onCreate() {
        super.onCreate()
        tv_title.text = tiele
        if (type == "1") {
            tv_context.text = content
        }


        tv_yes.setOnClickListener(this@XPopupUtils)
        tv_finish.setOnClickListener(this@XPopupUtils)
    }


    interface OnSelectListener {
        fun onSelect(v: View?)
    }

    interface OnSelectListener_v1 {
        fun onSelect_v1(v: View?, name: String, nian: String, yue: String, money: String)
    }

    fun setOnSelectListener(onSelectListener: OnSelectListener?) {
        onmySelectListener = onSelectListener
    }

    fun setOnSelectListener_v1(onSelectListener: OnSelectListener_v1?) {
        onmySelectListener_v1 = onSelectListener
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_finish -> {
                dismiss()// 关闭弹窗
            }
            R.id.tv_yes -> {
                if (type == "1") {
                    onmySelectListener!!.onSelect(v)
                } else {
                    onmySelectListener_v1!!.onSelect_v1(v, et_name.text.toString(), et_nian.text.toString(), et_yue.text.toString(), et_zc.text.toString())
                }

            }
        }
    }

}