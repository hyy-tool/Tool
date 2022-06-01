package com.hyy.hytool.dagger.component

import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.dagger.noudle.CommonActivityModule
import dagger.Component

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.dagger.component
 */
@Component(modules = [CommonActivityModule::class])
interface CommonActivityComponent {
    fun inject(mLoginActivity: BaseActivity?)
}