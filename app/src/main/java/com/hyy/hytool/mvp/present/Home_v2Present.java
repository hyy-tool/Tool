package com.hyy.hytool.mvp.present;

import com.hyy.hytool.base.BaseView;
import com.hyy.hytool.mvp.modile.AreaSelectBean;
import com.hyy.hytool.net.RetrofitAdminHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.present
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
