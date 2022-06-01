package com.hyy.htool.view

import android.content.Context
import android.view.LayoutInflater
import com.hyy.htool.utils.HyCardStackView
import java.util.ArrayList

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/06/2022/6/1
 * 软件包名:   com.hyy.htool.view
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