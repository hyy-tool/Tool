package com.example.toolgather.Adapter.adapter_hemo_v2

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.hytool.R
import com.example.hytool.total_directory.mvp.module.AreaSelectBean


/**
 * @author Administrator : HYY
 * 日期 :  2020/7/13
 * 备注 :
 */
class ListAdapter(layoutResId: Int) : BaseQuickAdapter<AreaSelectBean.DataBean?, BaseViewHolder>(layoutResId) {


    override fun convert(holder: BaseViewHolder, item: AreaSelectBean.DataBean?) {
        holder.setText(R.id.tv_name,item?.name)
    }
}