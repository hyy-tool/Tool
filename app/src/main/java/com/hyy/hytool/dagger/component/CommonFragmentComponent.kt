package com.hyy.hytool.dagger.component

import com.hyy.hytool.base.BaseFragment
import com.hyy.hytool.dagger.noudle.CommonFragmentModule
import dagger.Component

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.dagger.component
 */
@Component(modules = [CommonFragmentModule::class])
interface CommonFragmentComponent {
    fun inject(baseFragment: BaseFragment?)
}