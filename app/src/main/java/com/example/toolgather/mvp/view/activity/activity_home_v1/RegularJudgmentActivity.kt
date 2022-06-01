package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.view.View
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.R
import com.stefan.hyyTool.Utils.HyDataTool
import com.stefan.hyyTool.Utils.HyJudgeTool
import com.stefan.hyyTool.Utils.HyToast
import com.stefan.hyyTool.Utils.SkipActivity
import kotlinx.android.synthetic.main.activity_regular_judgment.*
import kotlinx.android.synthetic.main.iten_base.*
import java.util.*

/**
 * @Author : HYY
 * 描述:      正则判断
 * 时间:      2022/05/2022/5/12
 * 软件包名:   com.example.toolgather.Mvp.view.activity.activity_home_v1
 */
class RegularJudgmentActivity : BaseActivity(), View.OnClickListener {
    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_regular_judgment
    }

    override fun initViews() {
        tv_regular_test.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        tv_regular_formatting_phone.setOnClickListener(this)
        tv_title.text = "正则判断"

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_regular_formatting_phone -> {
                tv_formatting_phone.text = HyDataTool.hideMobilePhone4(et_regular_phone.text.toString())
            }
            R.id.iv_back -> {
                SkipActivity.finishActivity(this)
            }
            R.id.tv_regular_test -> {
                if(Objects.equals(et_regular_phone.text.toString(), "")) {
                    HyToast.showToast("请输入电话号码")
                } else {
                    var checkPhone = HyJudgeTool.checkPhone(et_regular_phone.text.toString())
                    if(checkPhone) {
                        HyToast.showToast("正确手机号码")
                    } else {
                        HyToast.showToast("手机号输入错误")
                    }
                }
            }
        }
    }
}