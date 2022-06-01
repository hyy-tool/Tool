package com.example.toolgather.mvp.view.fragment.fragment_home_v1

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.toolgather.Adapter.adapter_hemo_v1.HomeFragmentAdapter
import com.example.toolgather.Base.BaseFragment
import com.example.toolgather.mvp.module.EventBusBean
import com.example.toolgather.mvp.view.activity.activity_home_v1.*
import com.example.toolgather.R
import com.gyf.immersionbar.ImmersionBar
import com.hjq.permissions.XXPermissions
import com.stefan.hyyTool.Utils.HyAdapterTool
import com.stefan.hyyTool.Utils.HyToast
import com.stefan.hyyTool.Utils.SkipActivity
import kotlinx.android.synthetic.main.activity_answer.iv_back
import kotlinx.android.synthetic.main.activity_answer.title_iv
import kotlinx.android.synthetic.main.activity_im.*
import kotlinx.android.synthetic.main.home_fragment_v1.*
import org.greenrobot.eventbus.EventBus


/**
 * @author Administrator : HYY
 * TIME    2020/6/9
 * 备注 : 首页
 */
class HomeFragment_v1 : BaseFragment() {
    private var name = ArrayList<String?>()
    private val isRefresh = true
    private var mAdapter: HomeFragmentAdapter? = null


    override fun getLayoutId(): Int {
        return R.layout.home_fragment_v1
    }

    override fun initViews() {
        Glide.with(this).asBitmap().load("")
            .apply(RequestOptions().placeholder(R.drawable.shape))
            .into(title_iv)
        iv_back.visibility = View.INVISIBLE
        tv_title.text = resources.getString(R.string.main_home_page)
        name = ArrayList()
        name.add(resources.getString(R.string.home_svga))
        name.add(resources.getString(R.string.home_3d_ball))
        name.add(resources.getString(R.string.home_ios_switch))
        name.add(resources.getString(R.string.home_eventbus))
        name.add(resources.getString(R.string.home_pictures_pics))
        name.add(resources.getString(R.string.home_bank_card))
        name.add(resources.getString(R.string.home_permissions))
        name.add(resources.getString(R.string.home_regular))

        BannerContent()
        mAdapter = HomeFragmentAdapter(R.layout.item_home)
        mAdapter!!.animationEnable = true
        home_recyclerView.adapter = mAdapter
        home_recyclerView.layoutManager = GridLayoutManager(context, 2)
        HyAdapterTool.showList(isRefresh, name, home_srf, mAdapter)
        // 设置点击事件
        mAdapter!!.addChildClickViewIds(R.id.tv_name)
        mAdapter!!.setOnItemChildClickListener { _, view, position ->
            if(view.id == R.id.tv_name) {
                when (position) {
                    0 -> {
                        SkipActivity.startFragmentActivity(SvgaActivity::class.java, false, activity)
                    }
                    1 -> {
                        SkipActivity.startFragmentActivity(ThreeDActivity::class.java, false, activity)
                    }
                    2 -> {
                        SkipActivity.startFragmentActivity(IosButtonActivity::class.java, false, activity)
                    }
                    3 -> {
                        val eventBusBean = EventBusBean()
                        eventBusBean.name = "我是发送的事件"
                        EventBus.getDefault().postSticky(eventBusBean)
                        HyToast.success("已发送聊天界面接收")
                    }
                    4 -> {
                        SkipActivity.startFragmentActivity(PhotographActivity::class.java, false, activity)
                    }
                    5 -> {
                        SkipActivity.startFragmentActivity(BankCardActivity::class.java, false, activity)
                    }
                    6 -> {
                        XXPermissions.startPermissionActivity(activity)
                    }
                    7 -> {//正则判断
                        SkipActivity.startFragmentActivity(RegularJudgmentActivity::class.java, false, activity)
                    }
                }
            }
        }
    }

    private fun BannerContent() {
        val localImageSize = BGALocalImageSize(720, 1280, 320F, 640F)
        banner_content!!.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
            R.mipmap.f_1,
            R.mipmap.f_2,
            R.mipmap.f_3,
            R.mipmap.f_4,
            R.mipmap.f_5,
            R.mipmap.f_6)
    }

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this)
            .navigationBarColor(R.color.colorPrimary)
            .init()
    }
}