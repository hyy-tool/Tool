package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.text.TextUtils
import android.view.View
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.mvp.module.LoginBean
import com.example.toolgather.R
import com.example.toolgather.Utils.LogInGreenDao
import com.stefan.hyyTool.Utils.HyJudgeTool.isMobile
import com.stefan.hyyTool.Utils.HyToast
import kotlinx.android.synthetic.main.activity_login.et_name
import kotlinx.android.synthetic.main.activity_login.pass_word
import kotlinx.android.synthetic.main.activity_login.tv_login
import kotlinx.android.synthetic.main.activity_signin.*

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/23
 * 备注 : 注册
 */
class SignInActivity : BaseActivity(), View.OnClickListener {
    private var mName: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_signin
    }

    override fun initViews() {
        tv_login.setOnClickListener(this)
    }

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> finish()
            R.id.ll_login -> finish()
            R.id.tv_login -> {
                val loginBeans = LogInGreenDao.queryLogIn()
                val name = et_name!!.text.toString().trim { it <= ' ' }
                val pass_word = pass_word!!.text.toString().trim { it <= ' ' }
                val again_pass_word = again_pass_word!!.text.toString().trim { it <= ' ' }
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
}