package com.hyy.hytool.util

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.hyy.hytool.R
import com.lxj.xpopup.core.CenterPopupView


/**
 * @author Administrator : HYY
 * 日期 :  2020/7/6
 * 备注 :
 */
class XPopupUtils(context: Context, var tiele: String, var content: String, var type: String) :
    CenterPopupView(context), View.OnClickListener {
    var etName: EditText? = null
    var etNian: EditText? = null
    var etYue: EditText? = null
    var etZc: EditText? = null

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
        val tv_finish = findViewById<TextView>(R.id.tv_finish)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tv_context = findViewById<TextView>(R.id.tv_context)
        val tv_yes = findViewById<TextView>(R.id.tv_yes)
        tvTitle.text = tiele

        if (type == "1") {
            tv_context.text = content
        } else {
            etName = findViewById<EditText>(R.id.et_name)
            etNian = findViewById<EditText>(R.id.et_nian)
            etYue = findViewById<EditText>(R.id.et_yue)
            etZc = findViewById<EditText>(R.id.et_zc)
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
                    onmySelectListener_v1!!.onSelect_v1(
                        v,
                        etName?.text.toString(),
                        etNian?.text.toString(),
                        etYue?.text.toString(),
                        etZc?.text.toString()
                    )
                }

            }
        }
    }

}