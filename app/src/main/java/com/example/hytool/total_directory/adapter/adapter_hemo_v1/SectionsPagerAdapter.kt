package com.example.hytool.total_directory.adapter.adapter_hemo_v1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author Administrator : HYY
 * TIME    2019/11/4
 * 备注 :  首页Adapter
 */
class SectionsPagerAdapter(fm: FragmentManager?, var fragment: List<Fragment>) : FragmentPagerAdapter(
    fm!!
) {
    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }
}