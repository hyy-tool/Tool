package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.toolgather.Adapter.adapter_hemo_v1.TagCloudViewAdapter
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.R
import com.moxun.tagcloudlib.view.TagCloudView.OnTagClickListener
import kotlinx.android.synthetic.main.activity_svga.iv_back
import kotlinx.android.synthetic.main.activity_three_d.*
import java.util.*

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/13
 * 备注 : 3D球形特效
 */
class ThreeDActivity : BaseActivity(), OnTagClickListener {
    var list: MutableList<String> = ArrayList() //标签云数据的集合
    var listClick: MutableList<String> = ArrayList() //点击过的标签云的数据的集合


    override fun getLayoutId(): Int {
        return R.layout.activity_three_d
    }

    override fun initViews() {
       iv_back?.setOnClickListener { finish() }

        //给集合添加数据
        for (i in 0..29) {
            list.add("标签$i")
        }

        //设置标签云的点击事件
        tag_cloud?.setOnTagClickListener(this)
        //给标签云设置适配器
        val adapter = TagCloudViewAdapter(list)
        tag_cloud?.setAdapter(adapter)
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

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
}