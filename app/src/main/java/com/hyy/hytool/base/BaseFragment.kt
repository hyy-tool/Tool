package com.hyy.hytool.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar
import com.hyy.hytool.R
import com.hyy.hytool.dagger.component.DaggerCommonFragmentComponent
import com.hyy.hytool.dagger.noudle.CommonFragmentModule
import com.hyy.hytool.mvp.present.Home_v2Present
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.base
 */
abstract class BaseFragment : Fragment(), BaseView {
    private var mRootView: View? = null

    //Fragment的View加载完毕的标记
    private var isViewCreated = false

    //Fragment对用户可见的标记
    private var isUIVisible = false

    @set:Inject//java 混合开发不支持私有注入 必须加上 set:
    var mHome_v2Present: Home_v2Present? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getLayoutId()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerCommonFragmentComponent.builder().commonFragmentModule(CommonFragmentModule(this)).build().inject(this)
        initViews()
        initListener()
        initDatas()
        isViewCreated = true
        lazyLoad()
    }



    fun initListener() {}
    fun initDatas() {}
    private fun lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData()
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isUIVisible = false
        }
    }

    /**
     * fragment是否可见
     */
    protected open fun loadData() {}

    /**
     * 初始化布局
     *
     * @return
     */
    abstract fun getLayoutId(): View

    /**
     * 初始化布局控件
     */
    abstract fun initViews()

    /**
     * 主要在initviews方法中使用
     * findViewById操作
     *
     * @param id
     * @param <T>
     * @return
    </T> */
    fun <T : View?> `$`(id: Int): T {
        return mRootView!!.findViewById(id)
    }

    //TODO 查看这个fragment的可见状态
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
        }
    }

    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init()
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除粘性事件
        EventBus.getDefault().removeAllStickyEvents()
        //注销注册
        EventBus.getDefault().unregister(this)
    }

    /**
     * binding  释放防止内存泄漏
     * 不管onCreateView是否返回一个非空视图，它都会被调用
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }
}