package com.example.toolgather.Adapter.adapter_hemo_v1;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Administrator : HYY
 * TIME    2019/11/4
 * 备注 :  首页Adapter
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragment;

    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragment = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }
}
