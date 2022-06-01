package com.example.hytool.total_directory.dagger.component;




import com.example.hytool.total_directory.base.BaseFragment;
import com.example.hytool.total_directory.dagger.moudle.CommonFragmentModule;

import dagger.Component;

@Component( modules = CommonFragmentModule.class)
public interface CommonFragmentComponent {
    void inject(BaseFragment baseFragment);
}
