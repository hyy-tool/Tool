package com.example.toolgather.mvp.view.fragment.fragment_home_v4

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.example.hytool.R
import com.example.hytool.total_directory.base.BaseFragment
import com.example.hytool.databinding.HomeFragmentV4Binding
import com.example.toolgather.Utils.XPopupUtils
import com.example.toolgather.mvp.view.MainActivity
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener
import com.stefan.hyyTool.HyTool
import com.stefan.hyyTool.Utils.HyDeviceTool
import com.stefan.hyyTool.Utils.HyLoading
import com.example.hyytool.language.HyLanguageService.changeLanguage
import com.stefan.hyyTool.language.LanguageType
import java.util.*


/**
 * @author Administrator : HYY
 * TIME    2020/6/9
 * 备注 : 首页
 */
class HomeFragment_v4 : BaseFragment(), View.OnClickListener {

    lateinit var binding: HomeFragmentV4Binding


    override fun getLayoutId(): View {
        binding= HomeFragmentV4Binding.inflate(layoutInflater)
        return binding.root
    }
    override fun initViews() {
        binding.titleBase.tvTitle.text = "动画"
        binding.titleBase.tvAdd.text = "添加"
        binding.titleBase.ivBack.visibility = View.GONE
        binding.titleBase.tvAdd.visibility = View.VISIBLE
        binding.titleBase.tvTitle.setOnClickListener(this@HomeFragment_v4)
        binding.titleBase.tvAdd.setOnClickListener(this@HomeFragment_v4)
        binding.tvTime.setOnClickListener(this)
        binding.tvPick.setOnClickListener(this)
        binding.tvLanguage.setOnClickListener(this)


    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }


    override fun loadData() {
        super.loadData()
        var screenHeight = HyDeviceTool.getScreenHeight(HyTool.getContext())
        var screenWidth = HyDeviceTool.getScreenWidth(HyTool.getContext())
        var date = Calendar.getInstance()
        date.set(2000, 5, 1)//设置开始结束时间
        var date1 = Calendar.getInstance()
        date1.set(2020, 5, 1)
    }




    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_language -> {
                XPopup.Builder(context).asCenterList("请选择", arrayOf("中文", "English"), OnSelectListener { _, text ->
                    if (text.equals("中文")) {
                        language("chinese")
                    } else if (text.equals("English")) {
                        language("english")
                    }
                }).show()


            }
            R.id.tv_pick -> {

            }
            R.id.tv_time -> {
                //时间选择器
                var pvTime = TimePickerBuilder(activity, object : OnTimeSelectListener {
                    override fun onTimeSelect(date: Date?, v: View?) {
                        binding.tvTime.text = date.toString()
                    }
                })
                        .setType(booleanArrayOf(true, true, true, false, false, false))//分别对应年月日时分秒，默认全部显示
                        .setTitleText("选择时间")//标题文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .build()
                pvTime.show()

            }
            R.id.tv_title -> {
             HyLoading.loding(activity as Activity,true)
            }
            R.id.tv_add -> {

            }
            else -> {
            }
        }
    }

    private fun language(lange: String) {
        if (lange == "chinese") {
            changeLanguage(HyTool.getContext(), LanguageType.LANGUAGE_ZH_CN)
        } else if (lange == "english") {
            changeLanguage(HyTool.getContext(), LanguageType.LANGUAGE_EN)
        } else if (lange == "ARAB") {
            changeLanguage(HyTool.getContext(), LanguageType.LANGUAGE_AR)
        }
        val intent = Intent(activity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun showdialog() {
        val utils = XPopupUtils(activity as Context, "收入金额", "", "2")
        XPopup.Builder(activity as Context).asCustom(utils).show()
        utils.setOnSelectListener_v1(object : XPopupUtils.OnSelectListener_v1 {
            override fun onSelect_v1(v: View?, name: String, nian: String, yue: String, money: String) {
                utils.dismiss()
            }
        })
    }
}