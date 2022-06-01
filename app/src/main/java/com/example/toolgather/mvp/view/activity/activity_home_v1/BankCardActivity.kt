package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.toolgather.Adapter.adapter_hemo_v1.AdapterStackTest
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.R
import com.stefan.hyyTool.HyTool
import com.stefan.hyyTool.Utils.HyCardStackView
import com.stefan.hyyTool.Utils.SkipActivity
import com.stefan.hyyTool.interfaces.OnSimpleListener
import kotlinx.android.synthetic.main.activity_bank_car.*
import kotlinx.android.synthetic.main.iten_base.*

/**
 * @Author : HYY
 * 描述:       银行卡选择
 * 时间:      2022/05/2022/5/10
 * 软件包名:   com.example.toolgather.Mvp.view.activity.activity_home_v1
 */
class BankCardActivity : BaseActivity(), View.OnClickListener, HyCardStackView.ItemExpendListener {
    private var mTestStackAdapter: AdapterStackTest? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_bank_car

    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }


    override fun initViews() {
        tv_title.text = "银行卡"
        iv_back.setOnClickListener(this)
        onpreclick.setOnClickListener(this)
        onnextclick.setOnClickListener(this)
        stackview_main.itemExpendListener = this
        mTestStackAdapter = AdapterStackTest(this)
        stackview_main.setAdapter(mTestStackAdapter)
        HyTool.delayToDo(200, object : OnSimpleListener {
            override fun doSomething() {
                mTestStackAdapter!!.updateData(listOf(*TEST_DATAS))
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                finish()
                SkipActivity.finishActivity(this)
            }
            R.id.onpreclick -> {
                stackview_main.pre()
            }
            R.id.onnextclick -> {
                stackview_main.next()
            }
        }
    }

    companion object {
        var TEST_DATAS = arrayOf(
            R.color.custom_progress_green_header,
            R.color.custom_progress_green_progress,
            R.color.background_content,
            R.color.custom_progress_orange_header,
            R.color.custom_progress_orange_progress,
            R.color.darkslategrey,
            R.color.forestgreen,
            R.color.custom_progress_blue_header,
            R.color.cadetblue,
            R.color.custom_progress_purple_header,
            R.color.mediumaquamarine,
            R.color.mediumseagreen,
            R.color.custom_progress_red_header,
            R.color.custom_progress_red_progress,
            R.color.coral,
            R.color.WARNING_COLOR,
            R.color.aqua,
            R.color.blue_shadow_50,
            R.color.cadetblue,
            R.color.custom_progress_red_progress_half,
            R.color.brown,
            R.color.brown1,
            R.color.brown2,
            R.color.brown3,
            R.color.orange,
            R.color.custom_progress_orange_progress_half
        )
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        Glide.with(this).asBitmap().load("")
            .apply(RequestOptions().placeholder(R.drawable.shape_gradient_bank_car))
            .into(title_iv!!)
    }

    override fun onItemExpend(expend: Boolean) {
        button_container.visibility = if(expend) View.VISIBLE else View.GONE
    }
}