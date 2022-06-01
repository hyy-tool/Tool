package com.hyy.hytool.dagger.noudle

import com.hyy.hytool.base.BaseView
import com.hyy.hytool.mvp.present.Home_v2Present
import dagger.Module
import dagger.Provides

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.dagger.noudle
 */
@Module
class CommonFragmentModule(var mBaseView: BaseView) {

    @Provides
    fun home_v2present(): Home_v2Present? {
        return Home_v2Present(mBaseView)
    }
}