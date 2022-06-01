package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hytool.R
import com.example.hytool.total_directory.base.BaseActivity
import com.example.hytool.databinding.ActivityBankCarBinding
import com.example.hytool.databinding.ActivitySplashBinding
import com.example.toolgather.Adapter.adapter_hemo_v1.AdapterStackTest
import com.stefan.hyyTool.HyTool
import com.stefan.hyyTool.Utils.HyCardStackView
import com.stefan.hyyTool.Utils.SkipActivity
import com.stefan.hyyTool.interfaces.OnSimpleListener

/**
 * @Author : HYY
 * 描述:       银行卡选择
 * 时间:      2022/05/2022/5/10
 * 软件包名:   com.example.toolgather.Mvp.view.activity.activity_home_v1
 */
class BankCardActivity : BaseActivity(), View.OnClickListener, HyCardStackView.ItemExpendListener {

    private var mTestStackAdapter: AdapterStackTest? = null
    lateinit var binding: ActivityBankCarBinding

    override fun getLayoutId(): View {
        binding = ActivityBankCarBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }


    override fun initViews() {
        binding.titleBase.tvTitle.text = "银行卡"
        binding.titleBase.ivBack.setOnClickListener(this)
        binding.onpreclick.setOnClickListener(this)
        binding.onnextclick.setOnClickListener(this)
        binding.stackviewMain.itemExpendListener = this
        mTestStackAdapter = AdapterStackTest(this)
        binding.stackviewMain.setAdapter(mTestStackAdapter)
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
                binding.stackviewMain.pre()
            }
            R.id.onnextclick -> {
                binding.stackviewMain.next()
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
            .into(binding.titleBase.titleIv)
    }

    override fun onItemExpend(expend: Boolean) {
        binding.buttonContainer.visibility = if (expend) View.VISIBLE else View.GONE
    }

    override fun initDatas() {

    }
}