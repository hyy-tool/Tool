package com.hyy.hytool.adapter.adapter_home_v1

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hyy.hytool.R

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.adapter
 */
class HomeFragmentAdapter(layoutResId: Int) : BaseQuickAdapter<String?, BaseViewHolder>(layoutResId) {


    override fun convert(holder: BaseViewHolder, item: String?) {
        holder.setText(R.id.tv_name, item)
    }
}