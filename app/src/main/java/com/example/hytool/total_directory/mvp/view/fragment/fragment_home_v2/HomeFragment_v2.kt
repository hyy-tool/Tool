package com.example.toolgather.mvp.view.fragment.fragment_home_v2

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.hytool.R
import com.example.hytool.databinding.HomeFragmentV1Binding
import com.example.hytool.total_directory.base.BaseFragment
import com.example.hytool.databinding.HomeFragmentV2Binding
import com.example.hytool.total_directory.mvp.module.AreaSelectBean
import com.example.hyytool.utils.HyAdapterTool
import com.example.toolgather.Adapter.adapter_hemo_v2.ListAdapter
import com.example.toolgather.mvp.module.EventBusBean
import com.stefan.hyyTool.HyTool
import com.stefan.hyyTool.Utils.HyDeviceTool
import com.stefan.hyyTool.Utils.HySpTool
import com.stefan.hyyTool.Utils.HyToast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * @author Administrator : HYY
 * TIME    2020/6/9
 * 备注 : 动画
 */
class HomeFragment_v2 : BaseFragment(), View.OnClickListener {
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
        HyDeviceTool
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