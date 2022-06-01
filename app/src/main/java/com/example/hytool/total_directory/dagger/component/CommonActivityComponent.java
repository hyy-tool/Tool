package com.example.hytool.total_directory.dagger.component;


import com.example.hytool.total_directory.base.BaseActivity;
import com.example.hytool.total_directory.dagger.moudle.CommonActivityModule;

import dagger.Component;

@Component( modules = CommonActivityModule.class)
public interface CommonActivityComponent {
    void inject(BaseActivity mLoginActivity);
}
