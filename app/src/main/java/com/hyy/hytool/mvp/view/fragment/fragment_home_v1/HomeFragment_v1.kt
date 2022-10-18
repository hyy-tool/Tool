package com.hyy.hytool.mvp.view.fragment.fragment_home_v1

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gyf.immersionbar.ImmersionBar
import com.hjq.permissions.XXPermissions
import com.hyy.htool.utils.HyAdapterTool
import com.hyy.htool.utils.HyPhotoTool
import com.hyy.htool.utils.HyToast
import com.hyy.htool.utils.SingleClick
import com.hyy.htool.utils.SkipActivity
import com.hyy.hytool.R
import com.hyy.hytool.adapter.adapter_home_v1.HomeFragmentAdapter
import com.hyy.hytool.base.BaseFragment
import com.hyy.hytool.databinding.HomeFragmentV1Binding
import com.hyy.hytool.mvp.modile.EventBusBean
import com.hyy.hytool.mvp.view.activity.activity_home_v1.*
import org.greenrobot.eventbus.EventBus

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view.fragment
 */
class HomeFragment_v1 : BaseFragment() {

    private var name = ArrayList<String?>()
    private val isRefresh = true
    private var mAdapter: HomeFragmentAdapter? = null
    lateinit var binding: HomeFragmentV1Binding

    override fun getLayoutId(): View {
        binding=HomeFragmentV1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        Glide.with(this).asBitmap().load("")
            .apply(RequestOptions().placeholder(R.drawable.shape))
            .into(binding.titleBase.titleIv)
        binding.titleBase.ivBack.visibility = View.INVISIBLE
        binding.titleBase.tvTitle.text = resources.getString(R.string.main_home_page)
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
        binding.homeRecyclerView.adapter = mAdapter
        binding.homeRecyclerView.layoutManager = GridLayoutManager(context, 2)
        HyAdapterTool.showList(isRefresh, name, binding.homeSrf, mAdapter)
        // 设置点击事件
        mAdapter!!.addChildClickViewIds(R.id.tv_name)
        mAdapter!!.setOnItemChildClickListener { _, view, position ->

            if (view.id == R.id.tv_name) {
                when (position) {
                    0 -> {
                        SkipActivity.startActivity(SvgaActivity::class.java, false, activity!!)
                    }
                    1 -> {
                        SkipActivity.startActivity(ThreeDActivity::class.java, false, activity!!)
                    }
                    2 -> {
                        SkipActivity.startActivity(IoswitchActivity::class.java, false, activity!!)
                    }
                    3 -> {
                        val eventBusBean = EventBusBean()
                        eventBusBean.name = "我是发送的事件"
                        EventBus.getDefault().postSticky(eventBusBean)
                        HyToast.success("已发送常用界面接收")
                    }
                    4 -> {
                        SkipActivity.startActivity(PhotographActivity::class.java, false, activity!!)
                    }
                    5 -> {
                        SkipActivity.startActivity(BankCardActivity::class.java, false, activity!!)
                    }
                    6 -> {
                        XXPermissions.startPermissionActivity(activity)
                    }
                    7 -> {//正则判断
                        SkipActivity.startActivity(RegularJudgmentActivity::class.java, false, activity!!)
                    }
                }
            }
        }
    }

    private fun BannerContent() {
        val localImageSize = BGALocalImageSize(720, 1280, 320F, 640F)
        binding.bannerContent!!.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
            R.mipmap.f_1,
            R.mipmap.f_2,
            R.mipmap.f_3,
            R.mipmap.f_4,
            R.mipmap.f_5,
            R.mipmap.f_6
        )
    }

    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this)
            .navigationBarColor(R.color.colorPrimary)
            .init()
    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }
}