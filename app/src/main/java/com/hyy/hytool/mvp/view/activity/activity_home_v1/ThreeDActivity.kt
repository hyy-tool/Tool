package com.hyy.hytool.mvp.view.activity.activity_home_v1

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hyy.hytool.adapter.adapter_home_v1.TagCloudViewAdapter
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivityThreeDBinding
import com.moxun.tagcloudlib.view.TagCloudView.OnTagClickListener
import java.util.*

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/13
 * 备注 : 3D球形特效
 */
class ThreeDActivity : BaseActivity(), OnTagClickListener {
    var list: MutableList<String> = ArrayList() //标签云数据的集合
    var listClick: MutableList<String> = ArrayList() //点击过的标签云的数据的集合
    lateinit var binding: ActivityThreeDBinding

    override fun getLayoutId(): View {
        binding = ActivityThreeDBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        binding.ivBack.setOnClickListener { finish() }

        //给集合添加数据
        for (i in 0..29) {
            list.add("标签$i")
        }

        //设置标签云的点击事件
        binding.tagCloud.setOnTagClickListener(this)
        //给标签云设置适配器
        val adapter = TagCloudViewAdapter(list)
        binding.tagCloud.setAdapter(adapter)
    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    /**
     * 点击标签的回调方法
     *
     */
    override fun onItemClick(parent: ViewGroup, view: View, position: Int) {
        view.isSelected = !view.isSelected //设置标签的选择状态
        if (view.isSelected) {
            //加入集合
            listClick.add(list[position])
        } else {
            //移除集合
            listClick.remove(list[position])
        }
        Toast.makeText(this, "点击过的标签：$listClick", Toast.LENGTH_SHORT).show()
    }

}