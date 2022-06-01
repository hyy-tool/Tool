package com.hyy.hytool.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @Author : HYY
 * 描述:      首页Adapter
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.adapter
 */
class SectionsPagerAdapter (fm: FragmentManager?, var fragment: List<Fragment>) : FragmentPagerAdapter(
    fm!!
) {
    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }
}