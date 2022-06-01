package com.example.toolgather.Dagger.component;



import com.example.toolgather.Base.BaseActivity;
import com.example.toolgather.Dagger.moudle.CommonActivityModule;

import dagger.Component;

@Component( modules = CommonActivityModule.class)
public interface CommonActivityComponent {
    void inject(BaseActivity mLoginActivity);
}
