package com.example.toolgather.mvp.present;


import com.example.toolgather.Base.BaseView;
import com.example.toolgather.mvp.module.AreaSelectBean;
import com.example.toolgather.net.RetrofitAdminHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator : HYY
 * TIME    2020/6/8
 * 备注 :
 */
public class Home_v2Present {
    BaseView mBaseView;

    public Home_v2Present(BaseView baseView) {
        mBaseView = baseView;
    }

    public void follow_messages(int pid) {
        RetrofitAdminHelper.getInstance().follow_messages(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AreaSelectBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mBaseView.error(1, e);
                    }

                    @Override
                    public void onNext(AreaSelectBean diamondlogbean) {
                        mBaseView.success(1, diamondlogbean);
                    }
                });
    }
}
