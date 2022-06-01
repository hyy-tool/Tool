package com.example.hytool.total_directory.dagger.moudle;



import com.example.hytool.total_directory.base.BaseView;

import dagger.Module;

/**
 * @author Administrator : HYY
 * TIME    2019/11/4
 * 备注 :
 */
@Module
public class CommonActivityModule {
    BaseView mBaseView;

    public CommonActivityModule(BaseView baseView) {
        mBaseView = baseView;
    }


}
