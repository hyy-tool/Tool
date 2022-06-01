package com.example.toolgather.mvp.view.fragment.fragment_home_v2

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.toolgather.Adapter.adapter_hemo_v2.ListAdapter
import com.example.toolgather.Base.BaseFragment
import com.example.toolgather.mvp.module.AreaSelectBean
import com.example.toolgather.mvp.module.EventBusBean
import com.example.toolgather.R
import com.stefan.hyyTool.HyTool
import com.stefan.hyyTool.Utils.HyAdapterTool
import com.stefan.hyyTool.Utils.HyDeviceTool
import com.stefan.hyyTool.Utils.HySpTool
import com.stefan.hyyTool.Utils.HyToast

import kotlinx.android.synthetic.main.home_fragment_v2.*
import kotlinx.android.synthetic.main.iten_base.*
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

    override fun getLayoutId(): Int {
        return R.layout.home_fragment_v2
    }

    override fun initViews() {
        HyDeviceTool
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
        iv_back.visibility = View.GONE
        iv_icon.setOnClickListener(this@HomeFragment_v2)
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
                .into(iv_icon)

        mHome_v2Present.follow_messages(0)
        mAdapter = ListAdapter(R.layout.item_home_v2)
        mAdapter!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        rv_liebao.adapter = mAdapter
        rv_liebao.layoutManager = LinearLayoutManager(context)
    }

    override fun success(vararg o: Any) {
        ShowLoading(false)
        when (o[0] as Int) {
            1 -> {
                val appfollowlist: AreaSelectBean = o[1] as AreaSelectBean
                var data = appfollowlist.data

                HyAdapterTool.showList(isRefresh, data, sr_liebao, mAdapter)
            }
            else -> {

            }
        }
    }

    override fun error(vararg o: Any) {
        HyToast.error(o.toString())
        ShowLoading(false)
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