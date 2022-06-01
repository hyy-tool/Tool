package com.example.toolgather.Base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.toolgather.Dagger.component.DaggerCommonFragmentComponent;
import com.example.toolgather.Dagger.moudle.CommonFragmentModule;
import com.example.toolgather.mvp.present.Home_v2Present;
import com.example.toolgather.R;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by GSS on 2017/10/24.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private View mRootView;
    private boolean isDataInitiated;


    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;
    private BasePopupView mPopupView;
    @Inject
    public Home_v2Present mHome_v2Present;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        DaggerCommonFragmentComponent.builder().commonFragmentModule(new CommonFragmentModule(this)).build().inject(this);
        initViews();
        initListener();
        initDatas();
        isViewCreated = true;
        lazyLoad();


    }

    public void ShowLoading(boolean isShow) {
        if (isShow) {
            mPopupView = new XPopup
                    .Builder(getActivity())
                    .dismissOnBackPressed(false)
                    .dismissOnTouchOutside(false)
                    .asLoading("正在加载中")
                    .show();

        } else {
            mPopupView.dismiss();
            mPopupView.delayDismiss(300);//延时消失，有时候消失过快体验可能不好，可以延时一下
        }
    }


    public void initListener() {

    }

    public void initDatas() {

    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

        }
    }

    /**
     * fragment是否可见
     */
    protected void loadData() {

    }

    /**
     * 初始化布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化布局控件
     */
    public abstract void initViews();


    /**
     * 主要在initviews方法中使用
     * findViewById操作
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T $(int id) {
        return mRootView.findViewById(id);
    }


    //TODO 查看这个fragment的可见状态
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ImmersionBar.with(this)
                .navigationBarColor(R.color.colorPrimary)
                .init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //移除粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //注销注册
        EventBus.getDefault().unregister(this);
    }
}
