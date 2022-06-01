package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.text.TextUtils
import android.view.View
import com.example.hytool.R
import com.example.hytool.total_directory.base.BaseActivity
import com.example.hytool.databinding.ActivitySigninBinding
import com.example.hytool.total_directory.mvp.module.LoginBean
import com.example.hytool.total_directory.util.LogInGreenDao
import com.stefan.hyyTool.Utils.HyJudgeTool.isMobile
import com.stefan.hyyTool.Utils.HyToast

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/23
 * 备注 : 注册
 */
class SignInActivity : BaseActivity(), View.OnClickListener {
    private var mName: String? = null
    lateinit var binding: ActivitySigninBinding

    override fun initViews() {
        binding.tvLogin.setOnClickListener(this)
    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> finish()
            R.id.ll_login -> finish()
            R.id.tv_login -> {
                val name = binding.etName.text.toString().trim { it <= ' ' }
                val pass_word = binding.passWord.text.toString().trim { it <= ' ' }
                val again_pass_word = binding.againPassWord.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(name)) {
                    HyToast.error("请输入手机号")
                    return
                }
                val mobile = isMobile(name)
                if (!mobile) {
                    HyToast.error("请输入正确手机号")
                    return
                }
                if (TextUtils.isEmpty(pass_word)) {
                    HyToast.error("请输入密码")
                    return
                }
                if (TextUtils.isEmpty(again_pass_word)) {
                    HyToast.error("请再次输入密码")
                    return
                }
                if (pass_word != again_pass_word) {
                    HyToast.error("两次密码不一致")
                    return
                }
                val listName = LogInGreenDao.inquireLogInName(name) //按名字查询数据库
                if (listName.size != 0) {
                    var i = 0
                    while (i < listName.size) {
                        mName = listName[i].name
                        i++
                    }
                    if (mName == name) {
                        HyToast.error("账户已存在")
                        return
                    }
                }
                val bean = LoginBean()
                bean.type = LoginBean.TYPE_STORAGE
                bean.name = name
                bean.password = pass_word
                LogInGreenDao.insertAddLogin(bean)
                HyToast.success("注册成功")
                finish()
            }
            else -> {
            }
        }
    }

    override fun getLayoutId(): View {
        binding = ActivitySigninBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun initDatas() {

    }
}