package com.hyy.hytool.mvp.view.activity.activity_home_v1

import android.view.View
import com.hyy.htool.utils.HyDataTool
import com.hyy.htool.utils.HyJudgeTool
import com.hyy.htool.utils.HyToast
import com.hyy.htool.utils.SkipActivity
import com.hyy.hytool.R
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivityRegularJudgmentBinding
import java.util.*

/**
 * @Author : HYY
 * 描述:      正则判断
 * 时间:      2022/06/2022/6/1
 * 软件包名:   com.hyy.hytool.mvp.view.avtivity.actvity_home_v1
 */
class RegularJudgmentActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityRegularJudgmentBinding

    override fun getLayoutId(): View {
        binding = ActivityRegularJudgmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun initData() {
        binding.tvRegularTest.setOnClickListener(this)
        binding.titleBase.ivBack.setOnClickListener(this)
        binding.tvRegularFormattingPhone.setOnClickListener(this)
        binding.titleBase.tvTitle.text = "正则判断"

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_regular_formatting_phone -> {
                binding.tvRegularFormattingPhone.text =
                    HyDataTool.hideMobilePhone4(binding.etRegularPhone.text.toString())
            }
            R.id.iv_back -> {
                SkipActivity.finishActivity(this)
            }
            R.id.tv_regular_test -> {
                if (Objects.equals(binding.etRegularPhone.text.toString(), "")) {
                    HyToast.showToast("请输入电话号码")
                } else {
                    var checkPhone = HyJudgeTool.checkPhone(binding.etRegularPhone.text.toString())
                    if (checkPhone) {
                        HyToast.showToast("正确手机号码")
                    } else {
                        HyToast.showToast("手机号输入错误")
                    }
                }
            }
        }
    }
}