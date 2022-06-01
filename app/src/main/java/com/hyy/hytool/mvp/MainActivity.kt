package com.hyy.hytool.mvp

import android.view.View
import androidx.fragment.app.Fragment
import com.hyy.hytool.adapter.SectionsPagerAdapter
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivityMainBinding
import com.hyy.hytool.mvp.view.fragment.HomeFragment_v1
import com.hyy.hytool.mvp.view.fragment.HomeFragment_v2
import com.hyy.hytool.mvp.view.fragment.HomeFragment_v3
import com.hyy.hytool.mvp.view.fragment.HomeFragment_v4

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


    }
}