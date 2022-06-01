package com.example.toolgather.Dagger.component;



import com.example.toolgather.Base.BaseFragment;
import com.example.toolgather.Dagger.moudle.CommonFragmentModule;

import dagger.Component;

@Component( modules = CommonFragmentModule.class)
public interface CommonFragmentComponent {
    void inject(BaseFragment baseFragment);
}
