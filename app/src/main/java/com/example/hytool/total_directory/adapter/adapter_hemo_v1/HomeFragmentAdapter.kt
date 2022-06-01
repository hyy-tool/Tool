package com.example.toolgather.Adapter.adapter_hemo_v1

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.hytool.R

class HomeFragmentAdapter(layoutResId: Int) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId) {


    override fun convert(holder: BaseViewHolder, item: String?) {
        holder.setText(R.id.tv_name, item)
    }
}