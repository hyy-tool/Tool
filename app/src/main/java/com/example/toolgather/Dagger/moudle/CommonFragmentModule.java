package com.example.toolgather.Dagger.moudle;


import com.example.toolgather.Base.BaseView;
import com.example.toolgather.mvp.present.Home_v2Present;

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
