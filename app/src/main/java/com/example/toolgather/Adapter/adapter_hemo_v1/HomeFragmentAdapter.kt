package com.example.toolgather.Adapter.adapter_hemo_v1

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.toolgather.R

class HomeFragmentAdapter(layoutResId: Int) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId) {


    override fun convert(baseViewHolder: BaseViewHolder, s: String?) {
        baseViewHolder.setText(R.id.tv_name, s)
    }
}