package com.hyy.hytool.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.hyy.hytool.R
import com.hyy.hytool.dagger.component.DaggerCommonActivityComponent
import com.hyy.hytool.dagger.noudle.CommonActivityModule

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.base
 */
abstract class BaseActivity : AppCompatActivity(),BaseView {
    var savedInstanceState1: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //利用反射设置数据 唯一就是不能和dagger2 一起使用
        // lateinit var binding: VB
//       使用 BaseActivity<VB: ViewBinding>

//        val type = javaClass.genericSuperclass
//        if (type is ParameterizedType) {
//            val clazz = type.actualTypeArguments[0] as Class<ViewBinding>
//            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
//            var binding = method.invoke(null, layoutInflater) as ViewBinding
//            setContentView(binding.root)
//        }

        setContentView(getLayoutId())
        savedInstanceState1 = savedInstanceState

        DaggerCommonActivityComponent.builder().commonActivityModule(CommonActivityModule(this)).build().inject(this)

        initData()
        initImmersionBar()
    }
    /**
     * 获取布局信息
     *
     * @return
     */
    abstract fun getLayoutId(): View
    /**
     * 初始化基本控件 1
     */
    protected abstract fun initData()

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected open fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init()
    }
}