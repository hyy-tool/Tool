package com.hyy.hytool.mvp.view.activity.activity_home_v1

import android.view.View
import com.hyy.htool.utils.HyToast
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivytSwitchBinding
import com.suke.widget.SwitchButton


/**
 * @author Administrator : HYY
 * 日期 :  2020/6/14
 * 备注 : 仿IOS Btton
 */
class IoswitchActivity : BaseActivity() {
    lateinit var binding: ActivytSwitchBinding

    override fun getLayoutId(): View {
        binding = ActivytSwitchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun initData() {
        binding.switchButton.isChecked = true
        binding.switchButton.isChecked
        binding.switchButton.toggle() //切换状态
        binding.switchButton.toggle(true) //没有动画
        binding.switchButton.setShadowEffect(false) //禁用阴影效果
        binding.switchButton.isEnabled = true //禁用按钮
        binding.switchButton.setEnableEffect(true) //禁用切换动画
        binding.switchButton.setOnCheckedChangeListener(SwitchButton.OnCheckedChangeListener { view, isChecked ->
            HyToast.error(isChecked.toString())
        })
        binding.titleBase.titleIv.setOnClickListener { finish() }
    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }


}