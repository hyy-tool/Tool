package com.stefan.hyyTool.view

import android.content.Context
import android.view.LayoutInflater
import com.stefan.hyyTool.Utils.HyCardStackView
import java.util.*

/**
 * @Author : HYY
 * 描述:      整合修改
 * 时间:      2022/05/2022/5/9
 * 软件包名:   com.stefan.hyyTool.view
 */
abstract class HyAdapterStack<T>(val context: Context) : HyCardStackView.Adapter<HyCardStackView.ViewHolder>() {
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val mData: MutableList<T>

    fun updateData(data: List<T>?) {
        setData(data)
        notifyDataSetChanged()
    }

    fun setData(data: List<T>?) {
        mData.clear()
        if (data != null) {
            mData.addAll(data)
        }
    }

    override fun onBindViewHolder(holder: HyCardStackView.ViewHolder, position: Int) {
        val data = getItem(position)
        bindView(data, position, holder)
    }

    abstract fun bindView(data: T, position: Int, holder: HyCardStackView.ViewHolder)

    fun getItem(position: Int): T {
        return mData[position]
    }

    init {
        mData = ArrayList()
    }

    override val itemCount: Int
        get() = mData.size


}