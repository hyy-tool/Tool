package com.hyy.hytool.mvp

import android.Manifest
import android.view.View
import androidx.fragment.app.Fragment
import com.hyy.htool.HyTool
import com.hyy.htool.utils.HyLocationTool.isLocationEnabled
import com.hyy.htool.utils.HyNetTool.isGpsEnabled
import com.hyy.htool.utils.HyNetTool.isNetworkAvailable
import com.hyy.htool.utils.HyPermissionsTool
import com.hyy.htool.utils.HySpTool.putContent
import com.hyy.hytool.adapter.adapter_home_v1.SectionsPagerAdapter
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivityMainBinding
import com.hyy.hytool.mvp.view.fragment.fragment_home_v1.HomeFragment_v1
import com.hyy.hytool.mvp.view.fragment.fragment_home_v2.HomeFragment_v2
import com.hyy.hytool.mvp.view.fragment.fragment_home_v3.HomeFragment_v3
import com.hyy.hytool.mvp.view.fragment.fragment_home_v4.HomeFragment_v4
import com.hyy.hytool.util.XPopupUtils
import com.lxj.xpopup.XPopup

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mFragmentList: MutableList<Fragment> = ArrayList()

    override fun getLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
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
        putContent(HyTool.getContext(), "头像", "http://sxliveimage.jk-kj.com/Fr90ZzPvU1NRkh6xnd2ooewqVLhb?imageView2/1/w/200")

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

    override fun success(vararg o: Any?) {

    }

    override fun error(vararg o: Any?) {
    }

    override fun onBackPressed() {
        showdialog()

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