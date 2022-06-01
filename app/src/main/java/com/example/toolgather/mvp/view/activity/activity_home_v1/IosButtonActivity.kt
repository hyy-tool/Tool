package com.example.toolgather.mvp.view.activity.activity_home_v1

import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.R
import com.stefan.hyyTool.Utils.HyToast
import com.suke.widget.SwitchButton
import kotlinx.android.synthetic.main.iten_base.*


/**
 * @author Administrator : HYY
 * 日期 :  2020/6/14
 * 备注 : 仿IOS Btton
 */
class IosButtonActivity : BaseActivity() {
    private var mSwitch_button: SwitchButton? = null

    override fun getLayoutId(): Int {
        return R.layout.activyt_iosbutton
    }

    override fun initViews() {
        mSwitch_button?.isChecked = true
        mSwitch_button?.isChecked
        mSwitch_button?.toggle() //切换状态
        mSwitch_button?.toggle(true) //没有动画
        mSwitch_button?.setShadowEffect(false) //禁用阴影效果
        mSwitch_button?.isEnabled = true //禁用按钮
        mSwitch_button?.setEnableEffect(true) //禁用切换动画
        mSwitch_button?.setOnCheckedChangeListener(SwitchButton.OnCheckedChangeListener { view, isChecked ->
            HyToast.error(isChecked.toString())
        })
        iv_back.setOnClickListener { finish() }
    }

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
}