package com.hyy.hytool.mvp.view.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hyy.htool.HyTool
import com.hyy.htool.utils.HyAdapterTool
import com.hyy.htool.utils.HySpTool
import com.hyy.htool.utils.HyToast
import com.hyy.hytool.R
import com.hyy.hytool.adapter.ListAdapter
import com.hyy.hytool.base.BaseFragment
import com.hyy.hytool.databinding.HomeFragmentV2Binding
import com.hyy.hytool.mvp.modile.AreaSelectBean
import com.hyy.hytool.mvp.modile.EventBusBean
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view.fragment
 */
class HomeFragment_v2 :BaseFragment(), View.OnClickListener {

    var student1: EventBusBean? = null
    private var register = true
    private var isRefresh = true
    private var mAdapter: ListAdapter? = null
    lateinit var binding: HomeFragmentV2Binding


    override fun getLayoutId(): View {
        binding= HomeFragmentV2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
//        HyDeviceTool
    }

    override fun success(vararg o: Any?) {
        ShowLoading(false)
        when (o[0] as Int) {
            1 -> {
                val appfollowlist: AreaSelectBean = o[1] as AreaSelectBean
                var data = appfollowlist.data

                HyAdapterTool.showList(isRefresh, data, binding.srLiebao, mAdapter)
            }
            else -> {

            }
        }
    }

    override fun error(vararg o: Any?) {
        HyToast.error(o.toString())
        ShowLoading(false)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun receiveStudentEventBus(student: EventBusBean?) {
        student1 = student
    }

    override fun loadData() {
        super.loadData()
        if (student1 != null) {
            HyToast.success(student1?.name.toString())
        }
        ShowLoading(true)
        binding.titleBase.ivBack.visibility = View.GONE
        binding.ivIcon.setOnClickListener(this@HomeFragment_v2)
        if (register) { //判断evrntbus是否注册
            register = false
            EventBus.getDefault().register(this) //注册EventBus 哪里使用，哪里注册
        }

        val image = HySpTool.getContent(HyTool.getContext(), "头像")
        //解决图片拉伸显示
        Glide.with(HyTool.getContext())
            .load(image)
            .skipMemoryCache(true)//跳过内存缓存
            .dontAnimate()
            .into(binding.ivIcon)

        mHome_v2Present?.follow_messages(0)
        mAdapter = ListAdapter(R.layout.item_home_v2)
        mAdapter!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        binding.rvLiebao.adapter = mAdapter
        binding.rvLiebao.layoutManager = LinearLayoutManager(context)
    }




    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_icon -> {

            }
            else -> {

            }
        }
    }
}