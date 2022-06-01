package com.example.hytool.total_directory.dagger.moudle;



import com.example.hytool.total_directory.base.BaseView;
import com.example.hytool.total_directory.mvp.present.Home_v2Present;

import dagger.Module;
import dagger.Provides;

/**
 * @author Administrator : HYY
 * TIME    2019/11/4
 * 备注 :
 */
@Module
public class CommonFragmentModule {
    BaseView mBaseView;

    public CommonFragmentModule(BaseView baseView) {
        mBaseView = baseView;
    }

    @Provides
    public Home_v2Present home_v2present() {
        return new Home_v2Present(mBaseView);
    }

}
