package com.example.toolgather.Base;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.toolgather.Dagger.component.DaggerCommonActivityComponent;
import com.example.toolgather.Dagger.moudle.CommonActivityModule;
import com.example.toolgather.R;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.stefan.hyyTool.language.HyLanguageService;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    public Bundle savedInstanceState1;
    private BasePopupView mPopupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState1 = savedInstanceState;
        DaggerCommonActivityComponent.builder().commonActivityModule(new CommonActivityModule(this)).build().inject(this);
        setContentView(getLayoutId());
        initViews();
        initDatas();
        //初始化沉浸式
        initImmersionBar();
    }

    public void loading(boolean isShow) {
        if (isShow) {
            mPopupView = new XPopup.Builder(this).asLoading("正在加载中").show();
        } else {
            mPopupView.dismiss();
            mPopupView.delayDismiss(300);//延时消失，有时候消失过快体验可能不好，可以延时一下
        }
    }

    /**
     * findViewById操作
     *
     * @param id 查找ID
     * @param <T> 任意类型
     * @return 返回查找的ID
     */
    public <T extends View> T $(int id) {
        return findViewById(id);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //注销注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        //系统语言等设置发生改变时会调用此方法，需要要重置app语言
        Context context = HyLanguageService.INSTANCE.changeContextLocale(base);
        super.attachBaseContext(context);

    }
    /**
     * 自定义获取数据
     */
    public void initDatas() {

    }

    /**
     * 获取布局信息
     *
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 初始化基本控件 1
     */
    protected abstract void initViews();

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }

}
