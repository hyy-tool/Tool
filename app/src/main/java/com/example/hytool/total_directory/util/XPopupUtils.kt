package com.example.toolgather.Utils

import android.content.Context
import android.view.View
import com.example.hytool.R
import com.example.hytool.databinding.PickerviewCustomOptionsBinding
import com.example.hytool.databinding.PickerviewEdittextBinding
import com.lxj.xpopup.core.CenterPopupView


/**
 * @author Administrator : HYY
 * 日期 :  2020/7/6
 * 备注 :
 */
class XPopupUtils(context: Context, var tiele: String, var content: String, var type: String) : CenterPopupView(context), View.OnClickListener {


    private var onmySelectListener: OnSelectListener? = null
    private var onmySelectListener_v1: OnSelectListener_v1? = null
    val root = View.inflate(context, R.layout.pickerview_edittext, this)
    val root1 = View.inflate(context, R.layout.pickerview_edittext, this)
    var binding = PickerviewEdittextBinding.bind(root)
    var binding1 = PickerviewCustomOptionsBinding.bind(root1)

    override fun getImplLayoutId(): Int {
        if (type == "1") {
            return R.layout.pickerview_custom_options
        } else {
            return R.layout.pickerview_edittext
        }
    }

    override fun onCreate() {
        super.onCreate()
        binding.tvTitle.text = tiele
        if (type == "1") {
            binding1.tvContext.text = content
        }


        binding.tvYes.setOnClickListener(this@XPopupUtils)
        binding.tvFinish.setOnClickListener(this@XPopupUtils)
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
                        binding.etName.text.toString(),
                        binding.etNian.text.toString(),
                        binding.etYue.text.toString(),
                        binding.etZc.text.toString()
                    )
                }

            }
        }
    }

}