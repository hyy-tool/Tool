package com.example.hytool.total_directory.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hytool.R
import com.example.hytool.total_directory.dagger.component.DaggerCommonActivityComponent
import com.example.hytool.total_directory.dagger.moudle.CommonActivityModule
import com.gyf.immersionbar.ImmersionBar
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.example.hyytool.language.HyLanguageService
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity(), BaseView {

    var savedInstanceState1: Bundle? = null
    private var mPopupView: BasePopupView? = null


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
        initViews()
        initDatas()
        //初始化沉浸式
        initImmersionBar()
    }

    fun loading(isShow: Boolean) {
        if (isShow) {
            mPopupView = XPopup.Builder(this).asLoading("正在加载中").show()
        } else {
            mPopupView!!.dismiss()
            mPopupView!!.delayDismiss(300) //延时消失，有时候消失过快体验可能不好，可以延时一下
        }
    }


    /**
     * 获取布局信息
     *
     * @return
     */
    abstract fun getLayoutId(): View


    /**
     * findViewById操作
     *
     * @param id 查找ID
     * @param <T> 任意类型
     * @return 返回查找的ID
    </T> */
    fun <T : View?> `$`(id: Int): T {
        return findViewById(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除粘性事件
        EventBus.getDefault().removeAllStickyEvents()
        //注销注册
        EventBus.getDefault().unregister(this)
    }

    override fun attachBaseContext(base: Context) {
        //系统语言等设置发生改变时会调用此方法，需要要重置app语言
        var conlanguage = HyLanguageService.changeContextLocale(base)
        super.attachBaseContext(conlanguage)

    }

    /**
     * 自定义获取数据
     */
    protected abstract fun initDatas()


    /**
     * 初始化基本控件 1
     */
    protected abstract fun initViews()

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected open fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init()
    }
}