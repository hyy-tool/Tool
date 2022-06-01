package com.example.toolgather.mvp.view

import android.Manifest
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hytool.total_directory.adapter.adapter_hemo_v1.SectionsPagerAdapter
import com.example.hytool.total_directory.base.BaseActivity
import com.example.hytool.databinding.ActivityMainBinding
import com.example.toolgather.Utils.XPopupUtils
import com.example.toolgather.mvp.view.fragment.fragment_home_v1.HomeFragment_v1
import com.example.toolgather.mvp.view.fragment.fragment_home_v2.HomeFragment_v2
import com.example.toolgather.mvp.view.fragment.fragment_home_v3.HomeFragment_v3
import com.example.toolgather.mvp.view.fragment.fragment_home_v4.HomeFragment_v4
import com.lxj.xpopup.XPopup
import com.stefan.hyyTool.HyTool.getContext
import com.stefan.hyyTool.Utils.HyLocationTool.isLocationEnabled
import com.stefan.hyyTool.Utils.HyNetTool.isGpsEnabled
import com.stefan.hyyTool.Utils.HyNetTool.isNetworkAvailable
import com.stefan.hyyTool.Utils.HyPermissionsTool
import com.stefan.hyyTool.Utils.HySpTool.putContent


class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    private val mFragmentList: MutableList<Fragment> = ArrayList()


    override fun initViews() {

    }

    override fun getLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initDatas() {
        var asd = listOf("悦悦", "小可爱", "糖心", "喵喵", "乐乐", "叶子", "紫琪", "安琪")
        asd.forEach {
            println(it)
        }


        //权限管理
        val isNetworkAvailable = isNetworkAvailable(this) //判断网络连接是否可用
        val isGpsEnabled = isGpsEnabled(this) //GPS是否打开
        val isLocationEnabled = isLocationEnabled(this) //判断定位是否可用

        //TODO 权限获取
        HyPermissionsTool.with(this)
            .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) //相机权限
            .addPermission(Manifest.permission.ACCESS_COARSE_LOCATION) //网络定位权限
            .addPermission(Manifest.permission.ACCESS_FINE_LOCATION) //GPS定位权限
            .initPermission()


        putContent(getContext(), "头像", "http://sxliveimage.jk-kj.com/Fr90ZzPvU1NRkh6xnd2ooewqVLhb?imageView2/1/w/200")

        val v1 = HomeFragment_v1()
        val v2 = HomeFragment_v2()
        val v3 = HomeFragment_v3()
        val v4 = HomeFragment_v4()
        mFragmentList.add(v1)
        mFragmentList.add(v2)
        mFragmentList.add(v3)
        mFragmentList.add(v4)
        binding.vpContent.adapter = SectionsPagerAdapter(supportFragmentManager, mFragmentList)
        binding.bbNavigation.setUnread(0, 20)//设置第一个页签的未读数为20
        binding.bbNavigation.setUnread(1, 101)//设置第二个页签的未读数
        binding.bbNavigation.showNotify(2)//设置第三个页签显示提示的小红点
        binding.bbNavigation.setMsg(3, "NEW")//设置第四个页签显示NEW提示文字
        binding.bbNavigation!!.setViewPager(binding.vpContent)
    }




    override fun onBackPressed() {
        showdialog()


    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    private fun showdialog() {
        val utils = XPopupUtils(this, "提示", "确认退出 ?", "1")
        XPopup.Builder(this).asCustom(utils).show()
        utils.setOnSelectListener(object : XPopupUtils.OnSelectListener {
            override fun onSelect(v: View?) {
                utils.dismiss()
                finish()

            }
        })
    }
}